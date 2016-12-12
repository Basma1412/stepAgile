package com.example.basmamohamed.agileproject;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Basma Mohamed on 12/12/2016.
 */

public class ShowData  extends Activity {

    ListView listView;
    TextView txt;
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    EditText eTDialog,etDialogSalary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list1);



        MyBD db2 = new MyBD(ShowData.this);
        Cursor cr = db2.getAllUsers(db2);


        if (cr.getCount() > 0)
        {
            cr.moveToFirst();
            do{
                String UserName = cr.getString(cr.getColumnIndex(MyBD.TableUserName));
                int age = cr.getInt(cr.getColumnIndex(MyBD.TableUserAge));
                // int id = cr.getInt(cr.getColumnIndex(MyBD.TableUserID));
                String Email = cr.getString(cr.getColumnIndex(MyBD.TableUserEmail));
                String preview=UserName+" - "+Email+" - "+age;
                arrayList.add(preview);


            }while (cr.moveToNext());

            cr.close();
            db2.close();
        }



        arrayAdapter = new ArrayAdapter(ShowData.this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);





    }

}
