package com.example.basmamohamed.agileproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Basma Mohamed on 12/12/2016.
 */

public class changepassword extends ActionBarActivity {
    Button btn1;
    Button btn2;
    TextView txt1;
    TextView txt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword);
        btn1=(Button) findViewById(R.id.save);
        btn2=(Button) findViewById(R.id.cancel);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1=(TextView) findViewById(R.id.ps1);
                txt2=(TextView) findViewById(R.id.ps2);
                SharedPreferences myprefs= getSharedPreferences("user", MODE_WORLD_READABLE);
                String s3= myprefs.getString("Username", null);

                if(txt1.getText().toString().equals("")){

                    txt1.setError("Enter new password");
                }
                if(txt2.getText().toString().equals("")){

                    txt2.setError("Conform new password");
                }
                else {
                    if (txt1.getText().toString().equals(txt2.getText().toString())) {

                        MyBD db2 = new MyBD(changepassword.this);

                        db2.updatepassword(db2, s3, txt1.getText().toString());

                        Intent intent = new Intent(changepassword.this, Account.class);

                        startActivity(intent);
                        finish();

                    } else {

                        txt2.setError("the password doesn't match please insert again the correct one ");

                    }
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(changepassword.this, Account.class);

                startActivity(intent);
                finish();
            }
        });

        // Intent intent = new Intent(MainActivity.this, Login.class);
        //  startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.log_out) {

            finish();
            // session.isUserLoggedIn()== false;
            Intent in = new Intent(getApplicationContext(), Login.class);
            startActivity(in);
            return true;
        }


        if (id == R.id.change_pass) {

            finish();
            // session.isUserLoggedIn()== false;
            Intent in = new Intent(getApplicationContext(), changepassword.class);
            startActivity(in);
            return true;
        }



        return super.onOptionsItemSelected(item);
    }
}