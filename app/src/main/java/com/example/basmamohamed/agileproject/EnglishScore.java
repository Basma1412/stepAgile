package com.example.basmamohamed.agileproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class EnglishScore extends Activity {
    private Button btn1;
    private TextView txt;
    String name;
    MyBD db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.englishscore);
        txt = (TextView) findViewById(R.id.eng_score_txt);
        db=new MyBD(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int s = bundle.getInt("key");
        name = bundle.getString("name");
        String text = "" + s;
        txt.setText(text);
        db.insertEnglishScore(db , name,s);
        Toast.makeText(EnglishScore.this, "Inserted", Toast.LENGTH_LONG).show();



    }


}
