package com.example.basmamohamed.agileproject;

import android.content.Intent;
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
    TextView txt1;
    TextView txt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword);
        btn1=(Button) findViewById(R.id.chps);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1=(TextView) findViewById(R.id.ps1);
                txt2=(TextView) findViewById(R.id.ps2);

                if(txt1.getText().toString().equals(txt2.getText().toString())){

                    Intent intent = new Intent(changepassword.this, Account.class);

                    startActivity(intent);
                    finish();

                }

                else{

                    txt2.setError("the password doesn't match please insert again the correct one ");

                }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
