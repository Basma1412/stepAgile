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

public class Math_games extends Activity {
    private Button btn1;
    private TextView txt;
    //Math_Game g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.games_window);




        btn1=(Button) findViewById(R.id.mathbutton1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Math_games.this, Math_Game.class);
                startActivity(intent);
                finish();
            }
        });


    }

}
