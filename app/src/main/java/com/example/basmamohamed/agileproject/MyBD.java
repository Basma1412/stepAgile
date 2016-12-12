package com.example.basmamohamed.agileproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.example.basmamohamed.agileproject.R.id.username;

/**
 * Created by Basma Mohamed on 12/12/2016.
 */

public class MyBD  extends SQLiteOpenHelper {

    public static String DB_NAME = "GamesDB";
    public static int version = 1;

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



    //KEY_IMAGE + " BLOB);"
    String createTableleaderboard = "CREATE TABLE "+tableleaderboard+" ( "+tablerankname+" TEXT primary key , "
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

}
