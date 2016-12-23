package com.example.basmamohamed.agileproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Movie;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.description;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.example.basmamohamed.agileproject.R.id.engscore;
import static com.example.basmamohamed.agileproject.R.id.username;

/**
 * Created by Basma Mohamed on 12/12/2016.
 */

public class MyBD  extends SQLiteOpenHelper {

    public static String DB_NAME = "GamesDB";
    public static int version = 2;

    public static String TableUsers = "Users";
    public static String TableUserID = "ID";
    public static String TableUserName = "Name";
    public static String TableUserPassword = "Password";
    public static String TableUserEmail = "Email";
    public static String TableUserImage = "Image";
    public static String TableUserAge = "age";
    public static String TableUserScore = "TotalScore";
    private static Context context1;
    public static final String KEY_IMAGE = "image_data";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " +TableUsers;

    public static final String tableleaderboard="Ranktable";
    public static final String tablerankname="Playername";
    public static final String tablerank="Rank";
    public static final String tablemathscore="Mathscore";
    public static final String tableenglishscore="Engscore";
    public static final String tabletotalscore="Totalscore";



    private static final String TABLE_IMAGES = "images";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_IMAGE = "imageResource";
    private static final String COLUMN_ANSWER1 = "ANSWER1";
    private static final String COLUMN_ANSWER2 = "ANSWER2";
    private static final String COLUMN_ANSWER3 = "ANSWER3";
    private static final String COLUMN_ANSWER4 = "ANSWER4";
    private static final String COLUMN_RIGHTANSWER = "rightanswer";

    String createTableEnglishGame = "CREATE TABLE IF NOT EXISTS "+TABLE_IMAGES+" ( "+COLUMN_ID+" INTEGER primary key AUTOINCREMENT, "
            +COLUMN_IMAGE+" INTEGER , "
            +COLUMN_ANSWER1+" TEXT ,"
            +COLUMN_ANSWER2+" TEXT ,"
            +COLUMN_ANSWER3+" TEXT ,"
            +COLUMN_ANSWER4+" TEXT ,"
            +COLUMN_RIGHTANSWER+" TEXT);";



    //KEY_IMAGE + " BLOB);"
    String createTableleaderboard = "CREATE TABLE IF NOT EXISTS "+tableleaderboard+" ( "+tablerankname+" TEXT primary key , "
            +tablemathscore+" INTEGER , "
            +tableenglishscore+" INTEGER ,"
            +tabletotalscore+" INTEGER);";

    //String createTableUser = "CREATE TABLE "+TableUsers+" ( "+TableUserName+" TEXT primary key, "+TableUserPassword+" TEXT , "+TableUserEmail+" TEXT , "+TableUserAge+" INTEGER),"+KEY_IMAGE + " BLOB);";
    String createTableUser = "CREATE TABLE "+TableUsers+" ( "
            +TableUserName+" TEXT primary key, "
            +TableUserPassword+" TEXT , "
            +TableUserEmail+" TEXT ,"
            +TableUserAge+" INTEGER ,"
            +TableUserImage+" TEXT,"
            +tablemathscore+" INTEGER , "
            +tableenglishscore+" INTEGER ,"
            +tabletotalscore+" INTEGER);";

    public MyBD(Context context) {

        super(context, DB_NAME, null, version);
        context1 = context;

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableUser);
        db.execSQL(createTableleaderboard);
        db.execSQL(createTableEnglishGame);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public boolean validage(int age){

        if(age>=7 &&  age<=13)
            return true;

        else return false;
    }

    public boolean validemail(String email){
        if(email.equals(""))
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }
    public boolean validateUSer(MyBD db,String username, String password){
        SQLiteDatabase sdb = db.getReadableDatabase();
        String query = "SELECT * from "+TableUsers+" where "+TableUserName + " = '" + username+"' and "+TableUserPassword+" = '"+password+"'";

        Cursor cr = sdb.rawQuery(query,null);

        if (cr.getCount()>0) {
            cr.close();
            return true;
        }
        else
        {
            cr.close();
            return false;
        }


    }

    public boolean validateUSername(MyBD db,String username){
        SQLiteDatabase sdb = db.getReadableDatabase();
        String query = "SELECT * from "+TableUsers+" where "+TableUserName + " = '" + username+"'";

        Cursor cr = sdb.rawQuery(query,null);

        if (cr.getCount()>0) {
            cr.close();
            return true;
        }
        else
        {
            cr.close();
            return false;
        }


    }


    public void insertUser(MyBD db,String name, int age ,String password,String email,String img)
    {

        SQLiteDatabase sdb = db.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(TableUserName,name);
        cv.put(TableUserAge,age);
        cv.put(TableUserPassword,password);
        cv.put(TableUserEmail,email);
        cv.put(TableUserImage,img);
        sdb.insert(TableUsers,null,cv);

        Toast.makeText(context1,name + " is inserted",Toast.LENGTH_SHORT).show();
    }


    public void insertmathscore(MyBD db,String name, int mathscore ,int engscore)
    {

        SQLiteDatabase sdb = db.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(tableenglishscore,engscore);
        cv.put(tablemathscore,mathscore);
        int s=mathscore+engscore;
        cv.put(tabletotalscore,s);
        //sdb.insert(tableleaderboard,null,cv);
        sdb.update(TableUsers,cv,TableUserName+" = '" +name+"'",null);
        Toast.makeText(context1,name + " is inserted",Toast.LENGTH_SHORT).show();
    }


    public void updatepassword(MyBD db,String name, String password)
    {

        SQLiteDatabase sdb = db.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(TableUserPassword,password);
        cv.put(TableUserName,name);

        sdb.update(TableUsers,cv,TableUserName+" = '" +name+"'",null);
        Toast.makeText(context1, " your password is inserted",Toast.LENGTH_SHORT).show();
    }



    public String getURI(MyBD db,String username){
        SQLiteDatabase sdb = db.getReadableDatabase();
        String query = "SELECT "+TableUserImage+" from "+TableUsers+" where "+TableUserName + " = '" + username+"'";
        Cursor res = sdb.rawQuery(query,null);
        String urI="";
        if (res .moveToFirst()) {

                urI=res.getString(res.getColumnIndex(TableUserImage));


        }

        return urI;


    }

    public void editImage(MyBD db , String userN, String imageUri)
    {

        SQLiteDatabase sdb = db.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableUserImage,imageUri);
        sdb.update(TableUsers,cv,TableUserName+" = '" +userN+"'",null);


    }



    public void insertenglishscore(MyBD db,String name, int engscore,int mathscore)
    {

        SQLiteDatabase sdb = db.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(tableenglishscore,engscore);
        cv.put(tablemathscore,mathscore);

        cv.put(tabletotalscore,mathscore+engscore);
        sdb.update(TableUsers,cv,TableUserName+"="+name,null);
        Toast.makeText(context1,name + " is inserted",Toast.LENGTH_SHORT).show();
    }


    public Cursor getAllUsers(MyBD db)
    {
        SQLiteDatabase sdb = db.getReadableDatabase();

        Cursor cr = sdb.rawQuery("select * from '"+TableUsers +"' ORDER BY '" + tabletotalscore+"' DESC ", null);

        return cr;


    }



    public Cursor getEnglishData(MyBD db,int rand)
    {
        SQLiteDatabase sdb = db.getReadableDatabase();

        Cursor cr = sdb.rawQuery("select * from '"+TABLE_IMAGES +" where "+COLUMN_ID + " = '" + rand+"'", null);

        return cr;


    }




    public Cursor getAllPlayersMyBD( MyBD db)
    {
        SQLiteDatabase sdb = db.getReadableDatabase();

        Cursor cr = sdb.rawQuery("select "+TableUserName+","+tableenglishscore+","+tablemathscore+","+tabletotalscore +" from "+TableUsers+ " ORDER BY " + tabletotalscore+" DESC",null);

        return cr;


    }





    public Cursor getmathscore( MyBD db ,String name)
    {
        SQLiteDatabase sdb = db.getReadableDatabase();

        Cursor cr = sdb.rawQuery("select "+tabletotalscore+" from '"+TableUsers+ "' where "+TableUserName + " = '" + name+"'",null);

        return cr;


    }

    public Cursor getTotalscore( MyBD db ,String name)
    {
        SQLiteDatabase sdb = db.getReadableDatabase();

        Cursor cr = sdb.rawQuery("select "+tabletotalscore+" from '"+TableUsers+ "' where "+TableUserName + " = '" + name+"'",null);

        return cr;


    }


    public Cursor Age_Email( MyBD db ,String name)
    {
        SQLiteDatabase sdb = db.getReadableDatabase();

        Cursor cr = sdb.rawQuery("select "+TableUserEmail+","+TableUserAge+" from '"+TableUsers+ "' where "+TableUserName + " = '" + name+"'",null);

        return cr;


    }


    public void createRow (){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE, R.drawable.apple);
        values.put(COLUMN_ANSWER1, "tree");
        values.put(COLUMN_ANSWER2, "cat");
        values.put(COLUMN_ANSWER3, "pen");
        values.put(COLUMN_ANSWER4, "apple");
        values.put(COLUMN_RIGHTANSWER, "apple");
        db.insert(TABLE_IMAGES, null, values);

        ContentValues values2 = new ContentValues();
        values2.put(COLUMN_IMAGE, R.drawable.cat);
        values2.put(COLUMN_ANSWER1, "tree");
        values2.put(COLUMN_ANSWER2, "cat");
        values2.put(COLUMN_ANSWER3, "pen");
        values2.put(COLUMN_ANSWER4, "apple");
        values2.put(COLUMN_RIGHTANSWER, "cat");
        db.insert(TABLE_IMAGES, null, values2);


        ContentValues values3 = new ContentValues();
        values3.put(COLUMN_IMAGE, R.drawable.pen);
        values3.put(COLUMN_ANSWER1, "tree");
        values3.put(COLUMN_ANSWER2, "cat");
        values3.put(COLUMN_ANSWER3, "pen");
        values3.put(COLUMN_ANSWER4, "apple");
        values3.put(COLUMN_RIGHTANSWER, "pen");
        db.insert(TABLE_IMAGES, null, values3);


        ContentValues values4 = new ContentValues();
        values4.put(COLUMN_IMAGE, R.drawable.table);
        values4.put(COLUMN_ANSWER1, "tree");
        values4.put(COLUMN_ANSWER2, "cat");
        values4.put(COLUMN_ANSWER3, "pen");
        values4.put(COLUMN_ANSWER4, "table");
        values4.put(COLUMN_RIGHTANSWER, "table");
        db.insert(TABLE_IMAGES, null, values4);


        ContentValues values5 = new ContentValues();
        values5.put(COLUMN_IMAGE, R.drawable.tree);
        values5.put(COLUMN_ANSWER1, "tree");
        values5.put(COLUMN_ANSWER2, "cat");
        values5.put(COLUMN_ANSWER3, "pen");
        values5.put(COLUMN_ANSWER4, "apple");
        values5.put(COLUMN_RIGHTANSWER, "tree");
        db.insert(TABLE_IMAGES, null, values5);

        db.close();
    }


    public ArrayList<EnglishQuestion> getAllQuestions() {
        String query = "SELECT * FROM " + TABLE_IMAGES;
        ArrayList<EnglishQuestion> movies = new ArrayList<EnglishQuestion>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor c = database.rawQuery(query, null);
        if (c != null) {
            while (c.moveToNext()) {
                int mId = c.getInt(c.getColumnIndex(COLUMN_ID));
                int imgR = c.getInt(c.getColumnIndex(COLUMN_IMAGE));
                String choice1 = c.getString(c.getColumnIndex(COLUMN_ANSWER1));
                String choice2 = c.getString(c.getColumnIndex(COLUMN_ANSWER2));
                String choice3 = c.getString(c.getColumnIndex(COLUMN_ANSWER3));
                String choice4 = c.getString(c.getColumnIndex(COLUMN_ANSWER4));
                String rightA = c.getString(c.getColumnIndex(COLUMN_RIGHTANSWER));

                EnglishQuestion eng = new EnglishQuestion(mId, imgR, choice1, choice2, choice3, choice4,rightA);

                movies.add(eng);
            }
        }
        return movies;
    }

    public EnglishQuestion getSingleQuestion(int idR) {
        String query = "SELECT * FROM " + TABLE_IMAGES+" where "+COLUMN_ID + " = " + idR;
        SQLiteDatabase database = getReadableDatabase();
        EnglishQuestion eng=null;
        Cursor c = database.rawQuery(query, null);
        if (c != null) {
            while (c.moveToNext())
            {
                int mId = c.getInt(c.getColumnIndex(COLUMN_ID));
            int imgR = c.getInt(c.getColumnIndex(COLUMN_IMAGE));
            String choice1 = c.getString(c.getColumnIndex(COLUMN_ANSWER1));
            String choice2 = c.getString(c.getColumnIndex(COLUMN_ANSWER2));
            String choice3 = c.getString(c.getColumnIndex(COLUMN_ANSWER3));
            String choice4 = c.getString(c.getColumnIndex(COLUMN_ANSWER4));
            String rightA = c.getString(c.getColumnIndex(COLUMN_RIGHTANSWER));

            eng = new EnglishQuestion(mId, imgR, choice1, choice2, choice3, choice4, rightA);
        }


        }
        return eng;
    }



    public void deleteteEnglishTable()
    {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGES);
    }


    public void createEnglishTable()
    {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(createTableEnglishGame);
    }

}
