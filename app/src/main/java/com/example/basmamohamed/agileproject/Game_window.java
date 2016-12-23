package com.example.basmamohamed.agileproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Basma Mohamed on 12/12/2016.
 */

public class Game_window  extends Activity {

    private Button btn1;
    private Button btn2;

    private Button btn3;
    String name="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.game_window);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
         name = bundle.getString("name");
        //btn2=(Button) findViewById(R.id.englishbutton);
        btn1=(Button) findViewById(R.id.mathbutton1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Game_window.this, Math_games.class);
                startActivity(intent);
                finish();
            }
        });

        btn2=(Button)findViewById(R.id.englishbtn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Game_window.this, English_Game.class);
                Bundle b = new Bundle();
                b.putString("name", name);
                intent.putExtras(b);
                startActivity(intent);
                finish();
            }
        });

    }



}
