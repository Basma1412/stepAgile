package com.example.basmamohamed.agileproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


public class English_Game extends AppCompatActivity {


    Button c1;
    Button c2;
    Button c3;
    Button c4;
    Button submit;
    MyBD bd;
    ImageView pic;
    String rightAns;
    String correct = "Great,Correct Answer :D";
    String wrong = "Try again :( ";
    boolean created = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.english_game);
        pic = (ImageView) findViewById(R.id.picQuestion);
        c1 = (Button) findViewById(R.id.choice1);
        c2 = (Button) findViewById(R.id.choice2);
        c3 = (Button) findViewById(R.id.choice3);
        c4 = (Button) findViewById(R.id.choice4);
        submit = (Button) findViewById(R.id.next);
        bd = new MyBD(English_Game.this);


//            created = true;
        bd.deleteteEnglishTable();
       bd.createEnglishTable();
        bd.createRow();
       setGame();


//        play();


    }

    private void play() {
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = c1.getText().toString();
                if (text == rightAns) {

                    Toast.makeText(English_Game.this, correct, Toast.LENGTH_LONG).show();
                } else {


                    Toast.makeText(English_Game.this, wrong + "" + rightAns, Toast.LENGTH_LONG).show();

                }
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = c2.getText().toString();
                if (text == rightAns) {


                    Toast.makeText(English_Game.this, correct, Toast.LENGTH_LONG).show();
                } else {


                    Toast.makeText(English_Game.this, wrong, Toast.LENGTH_LONG).show();
                }
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = c3.getText().toString();
                if (text == rightAns) {


                    Toast.makeText(English_Game.this, correct, Toast.LENGTH_LONG).show();
                } else {


                    Toast.makeText(English_Game.this, wrong, Toast.LENGTH_LONG).show();
                }
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = c4.getText().toString();
                if (text == rightAns) {


                    Toast.makeText(English_Game.this, correct, Toast.LENGTH_LONG).show();
                } else {


                    Toast.makeText(English_Game.this, wrong + "" + rightAns + "," + text, Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    String choiceA;
    String choiceB;
    String choiceC;
    String choiceD;

    public void setGame() {
        EnglishQuestion e = bd.getSingleQuestion(3);

        choiceA = e.choice1;
        choiceB = e.choice2;
        choiceC = e.choice3;
        choiceD = e.choice4;


        c1.setText(choiceA);
        c2.setText(choiceB);
        c3.setText(choiceC);
        c4.setText(choiceD);
        pic.setImageResource(e.getImageRes());



    }


}
