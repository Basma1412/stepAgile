package com.example.basmamohamed.agileproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Basma Mohamed on 12/12/2016.
 */

public class leaderboard extends Activity {
    ListView listView;
    TextView txt;
    ArrayList<userInfo> arrayList = new ArrayList<userInfo>();
    ArrayAdapter arrayAdapter;
    EditText eTDialog,etDialogSalary;
    userInfo t;
    CustomListAdapter customListAdapter;
    private static final int RESULT_LOAD_IMG=1;
    ImageView profileimg;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.leaderboard);


        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list1);
        profileimg=(ImageView)findViewById(R.id.imagelogo);

       /* TableLayout tl = (TableLayout) findViewById(R.id.myTableLayout);
        TableRow tr1 = new TableRow(this);
        tr1.setPadding(0,20,0,20);
        tr1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));*/
        MyBD db2 = new MyBD(leaderboard.this);
        Cursor cr = db2.getAllUsers(db2);


        //  TextView item1 = new TextView(this);
        // TextView item2 = new TextView(this);
        //TextView item3 = new TextView(this);
        //TextView item4 = new TextView(this);
        //TextView item5 = new TextView(this);

        int rank=1;
        if (cr.getCount() > 0)
        {
            cr.moveToFirst();
            do{


                t=new userInfo();

                String UserName = cr.getString(cr.getColumnIndex(MyBD.TableUserName));

                t.setusername(UserName);
                int mathscore = cr.getInt(cr.getColumnIndex(MyBD.tablemathscore));
                t.setmathscore(mathscore);
                int engscore = cr.getInt(cr.getColumnIndex(MyBD.tableenglishscore));
                t.setengscore(engscore);
                int totalscore = cr.getInt(cr.getColumnIndex(MyBD.tabletotalscore));
                String image=cr.getString(cr.getColumnIndex(MyBD.TableUserImage));
                t.settotalcore(totalscore);
                t.setmathscore(mathscore);
                t.setrank(rank);
                t.setuserimage(image);
              /* item2.setText(""+UserName);
                item3.setText(""+mathscore);
                item4.setText(""+engcore);
                item5.setText(""+totalscore);               // int id = cr.getInt(cr.getColumnIndex(MyBD.TableUserID));
                item1.setText(""+rank);
*/
                //  String preview= "Username: "+UserName+" "+"Math Game score: "+mathscore+" "+"English Game Score: "+engscore+" "+"Total Score: "+totalscore;


                //arrayList.add(preview);

                arrayList.add(t);


                rank++;
                //String Email = cr.getString(cr.getColumnIndex(MyBD.TableUserEmail));
                //String preview=UserName+" - "+Email+" - "+age;
                // arrayList.add(preview);
/*
                tr1.addView(item1);
                tr1.addView(item2);
                tr1.addView(item3);
                tr1.addView(item4);
                tr1.addView(item5);
                tl.addView(tr1);
                item2.setText("");
                item3.setText("");
                item4.setText("");
                item5.setText("");               // int id = cr.getInt(cr.getColumnIndex(MyBD.TableUserID));
                item1.setText("");
              // tl.addView(tr1, new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
*/

            }while (cr.moveToNext());

            cr.close();
            db2.close();
            customListAdapter = new CustomListAdapter(arrayList,this);

            listView.setAdapter(customListAdapter);
        }



        //arrayAdapter = new ArrayAdapter(ShowData.this,android.R.layout.simple_list_item_1,arrayList);
        //
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_LOAD_IMG&&resultCode==RESULT_OK&&data!=null)
        {
            Uri selectedImage=data.getData();
            profileimg.setImageURI(selectedImage);

        }
    }

}
