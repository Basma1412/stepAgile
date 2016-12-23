package com.example.basmamohamed.agileproject;

import android.content.Intent;
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
    Button sub;
    MyBD bd;
    ImageView pic;
    String rightAns;
    String correct = "Great,Correct Answer :D";
    String wrong = "Try again :( ";
    boolean created = false;
    int eScore = 0;
    int turn = 1;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        name = bundle.getString("name");

        setContentView(R.layout.english_game);
        pic = (ImageView) findViewById(R.id.picQuestion);
        c1 = (Button) findViewById(R.id.choice1);
        c2 = (Button) findViewById(R.id.choice2);
        c3 = (Button) findViewById(R.id.choice3);
        c4 = (Button) findViewById(R.id.choice4);
        sub = (Button) findViewById(R.id.Skip);
        bd = new MyBD(English_Game.this);


        bd.deleteteEnglishTable();
        bd.createEnglishTable();
        bd.createRow();
        setGame(turn);
        play();



    }




    private void play() {

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (turn >= 4) {
                    finishGame();
                } else {
                    turn++;
                    setGame(turn);
                }
            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = c1.getText().toString();
                if (text.equalsIgnoreCase(rightAns)) {
                    eScore++;
                    Toast.makeText(English_Game.this, correct, Toast.LENGTH_LONG).show();
                    if (turn >= 4) {
                        finishGame();
                        ;
                    } else {
                        turn++;
                        setGame(turn);
                    }
                } else {


                    Toast.makeText(English_Game.this, wrong + "" + rightAns, Toast.LENGTH_LONG).show();

                }
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = c2.getText().toString();
                if (text.equalsIgnoreCase(rightAns)) {
                    eScore++;

                    Toast.makeText(English_Game.this, correct, Toast.LENGTH_LONG).show();
                    if (turn >= 4) {
                        finishGame();
                    } else {
                        turn++;
                        setGame(turn);
                    }
                } else {


                    Toast.makeText(English_Game.this, wrong, Toast.LENGTH_LONG).show();
                }
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = c3.getText().toString();
                if (text.equalsIgnoreCase(rightAns)) {
                    eScore++;

                    Toast.makeText(English_Game.this, correct, Toast.LENGTH_LONG).show();
                    if (turn >= 4) {
                        finishGame();
                    } else {
                        turn++;
                        setGame(turn);
                    }
                } else {


                    Toast.makeText(English_Game.this, wrong, Toast.LENGTH_LONG).show();
                }
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = c4.getText().toString();
                if (text.equalsIgnoreCase(rightAns)) {
                    eScore++;

                    Toast.makeText(English_Game.this, correct, Toast.LENGTH_LONG).show();
                    if (turn >= 4) {
                        finishGame();
                    } else {
                        turn++;
                        setGame(turn);
                    }
                } else {


                    Toast.makeText(English_Game.this, wrong + text, Toast.LENGTH_LONG).show();
                }
            }
        });





    }

    String choiceA;
    String choiceB;
    String choiceC;
    String choiceD;

    public void finishGame() {
        Intent intent = new Intent(English_Game.this, EnglishScore.class);
        Bundle b = new Bundle();
        b.putInt("key", eScore);
        b.putString("name", name);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void setGame(int turn) {
        EnglishQuestion e = bd.getSingleQuestion(turn);

        choiceA = e.choice1;
        choiceB = e.choice2;
        choiceC = e.choice3;
        choiceD = e.choice4;


        rightAns = e.getRight();

        c1.setText(choiceA);
        c2.setText(choiceB);
        c3.setText(choiceC);
        c4.setText(choiceD);
        pic.setImageResource(e.getImageRes());




    }


}
