package com.example.basmamohamed.agileproject;

/**
 * Created by Basma Mohamed on 12/12/2016.
 */

public class userInfo {
    private String username;
    private int mathscore=0;
    private int engscore=0;
    private int totalscore;
    private int age;
    private int rank;
    private String userimage;
    private String email;
    public userInfo(String username, int mathscore ,int engscore,int totalscore ,int rank,int age,String email ) {
        this.mathscore=mathscore;
        this.username=username;
        this.engscore=engscore;
        this.rank=rank;
        this.totalscore=totalscore;
        this.age=age;
        this.email=email;
    }

    public userInfo() {
    }

    public String getUsername() {
        return username;
    }

    public void setusername(String name) {
        this.username = name;
    }

    public int getmathscore() {
        return mathscore;
    }

    public void setmathscore(int math) {
        this.mathscore = math;
    }

    public void setuserimage(String userimage){this.userimage=userimage;}
    public String getuserimage(){return this.userimage;}

    public int getengscore() {
        return engscore;
    }

    public void setengscore(int eng) {
        this.engscore = eng;
    }

    public int getage() {
        return age;
    }
    public void setage(int age){
        this.age=age;

    }

    public void setEmail(String email){

        this.email=email;
    }

    public String getEmail(){

        return email;
    }
    public int gettotalscore() {
        return totalscore;
    }

    public void settotalcore(int total) {
        this.totalscore = total;
    }



    public int getrank() {
        return rank;
    }

    public void setrank(int rank) {
        this.rank = rank;
    }

}
