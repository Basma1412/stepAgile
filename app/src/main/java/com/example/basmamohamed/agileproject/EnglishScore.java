package com.example.basmamohamed.agileproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Basma Mohamed on 12/12/2016.
 */

public class EnglishScore extends Activity {
    private Button btn1;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.englishscore);
        txt = (TextView) findViewById(R.id.eng_score_txt);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int s = bundle.getInt("key");
        txt.setText("" + s);




    }


}
