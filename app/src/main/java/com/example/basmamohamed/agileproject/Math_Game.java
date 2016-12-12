package com.example.basmamohamed.agileproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Basma Mohamed on 12/12/2016.
 */

public class Math_Game extends Activity {

    private String operators[]=new String[4];
    private Random r = new Random();
    static int counter=0;
    private  double first_operand =0;
    private double second_operand=0;
    private String operator="";
    private  double result=0;
    public  static  int MathScore=0;
    private Button btn1;
    private  Button btn2;
    private  Button btn3;
    private   Button next;

    private TextView quest;
    private  TextView result_txt;
    private   String btnStr1;
    private String btnStr2;
    private   String btnStr3;
    private   String nextStr;
    private   String txtQuest;

    static int Gamecount=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_game);
        filling_list();
        getID();
        Gamecount=0;
        Game();
        validate_result();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gamecount++;
                if( Gamecount<3){
                    Game();
                    validate_result();}

                else{

                    Intent intent = new Intent(Math_Game.this, MathScore.class);
                    Bundle b = new Bundle();
                    b.putInt("key", MathScore);
                    intent.putExtras(b);
                    //intent.putExtra("MathScore",MathScore);
                    startActivity(intent);

                    finish();
                }
            }
        });

    }

    void getID(){
        btn1=(Button) findViewById(R.id.btn3);
        btn2=(Button)   findViewById(R.id.btn4);
        btn3=(Button)   findViewById(R.id.btn5);
        next=(Button)   findViewById(R.id.btn6);
        result_txt=(TextView)   findViewById(R.id.result_txt);
        quest=(TextView)   findViewById(R.id.game_txt);


    }



    void filling_list(){

        operators[0]="+";
        operators[1]="-";
        operators[2]="*";
        operators[3]="/";
    }

    void Game() {



        btn1.setTextColor(getApplication().getResources().getColor(R.color.black));
        btn2.setTextColor(getApplication().getResources().getColor(R.color.black));
        btn3.setTextColor(getApplication().getResources().getColor(R.color.black));
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        result_txt.setText("");
        result_txt.setTextSize(25);
        int l2 = 0, l3 = 0;

        first_operand = getFirst_operand();
        second_operand = getSecond_operand();
        operator = getOperator();

        txtQuest = " " + first_operand + " " + operator + "" + second_operand + " ? ";
        quest.setText(txtQuest);

        get_result();

        btnStr1 = "" + result + "";

        l2 = (r.nextInt(30) + 1);
        while (l2 == result || l2 == l3)
            l2 = (r.nextInt(30) + 1);

        btnStr2 = "" + l2 + "";

        l3 = (r.nextInt(30) + 1);
        while (l3 == result || l2 == l3)
            l3 = (r.nextInt(30) + 1);
        btnStr3 = "" + l3 + "";

        assign_result();





    }
    void  assign_result(){
        counter=r.nextInt(3)+1;
        if(counter==1) {
            btn1.setText(btnStr1);
            btn2.setText(btnStr3);
            btn3.setText(btnStr2);
        }
        else if (counter==2){

            btn1.setText(btnStr3);
            btn2.setText(btnStr1);
            btn3.setText(btnStr2);

        }

        else if (counter==3){

            btn1.setText(btnStr3);
            btn2.setText(btnStr2);
            btn3.setText(btnStr1);

        }

    }

    void validate_result(){
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double c=Double.parseDouble(btn1.getText().toString());
                if(c==result){
                    result_txt.setTextColor(0xff2aff5a );
                    btn1.setTextColor(0xff2aff5a);

                    result_txt.setText("The answer is correct");
                    MathScore++;
                    btn2.setEnabled(false);
                    btn3.setEnabled(false);
                    // Game();
                }
                else{
                    result_txt.setTextColor(0xffff451a );
                    btn1.setTextColor(0xffff451a);
                    result_txt.setText("The answer is wrong");
                    // Game();
                    btn2.setEnabled(false);
                    btn3.setEnabled(false);
                }

            }
        });



        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double c=Double.parseDouble(btn2.getText().toString());
                if(c==result){
                    result_txt.setTextColor(0xff2aff5a);
                    result_txt.setText("The answer is correct");
                    btn2.setTextColor(0xff2aff5a);
                    MathScore++;
                    // Game();
                    btn1.setEnabled(false);
                    btn3.setEnabled(false);
                }
                else{
                    result_txt.setTextColor(0xffff451a );
                    btn2.setTextColor(0xffff451a);
                    result_txt.setText("The answer is wrong");
                    btn1.setEnabled(false);
                    btn3.setEnabled(false);
                    //  Game();
                }

            }
        });




        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double c=Double.parseDouble(btn3.getText().toString());
                if(c==result){
                    result_txt.setTextColor(0xff2aff5a );
                    result_txt.setText("The answer is correct");
                    btn3.setTextColor(0xff2aff5a);
                    MathScore++;
                    //Game();
                    btn2.setEnabled(false);
                    btn1.setEnabled(false);
                }
                else{
                    result_txt.setTextColor(0xffff451a );
                    result_txt.setText("The answer is wrong");
                    btn3.setTextColor(0xffff451a);
                    //Game();
                    btn2.setEnabled(false);
                    btn1.setEnabled(false);
                }

            }
        });


    }

    int generate_random_operant(){

        int i1 = (r.nextInt(30) + 1);


        return i1;

    }


    String getOperator(){
        int i1 = (r.nextInt(3) + 0);
        return operators[i1];

    }


    double getFirst_operand(){
        return generate_random_operant();


    }

    double getSecond_operand(){
        return generate_random_operant();
    }

    void get_result(){

        if(operator.equals("+")){
            result=first_operand+second_operand;


        }
        else  if(operator.equals("-")){
            result=first_operand-second_operand;

        }

        else  if(operator.equals("*")){
            result=first_operand*second_operand;

        }

        else  if(operator.equals("/")){
            if(second_operand!=0)
                result=first_operand/second_operand;
        }



    }


}

