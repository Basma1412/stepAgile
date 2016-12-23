package com.example.basmamohamed.agileproject;

/**
 * Created by Basma Mohamed on 12/23/2016.
 */

public class EnglishQuestion {
    int imageRes;
    String choice1;
    String choice2;
    String choice3;
    String choice4;
    String right;
    int id;

    public EnglishQuestion(int id,int imageRes, String o,String t, String th,String f,String r)
    {
        this.id=id;
        this.imageRes=imageRes;
        this.choice1=o;
        this.choice2=t;
        this.choice3=th;
        this.choice4=f;
        this.right=r;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getChoice1() {
        return choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public String getRight() {
        return right;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public void setRight(String right) {
        this.right = right;
    }

}
