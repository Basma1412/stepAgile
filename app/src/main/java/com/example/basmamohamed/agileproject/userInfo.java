package com.example.basmamohamed.agileproject;

/**
 * Created by Basma Mohamed on 12/12/2016.
 */

public class userInfo {
    private String username;
    private int mathscore;
    private int engscore;
    private int totalscore;
    private int rank;

    public userInfo(String username, int mathscore ,int engscore,int totalscore ,int rank ) {
        this.mathscore=mathscore;
        this.username=username;
        this.engscore=engscore;
        this.rank=rank;
        this.totalscore=totalscore;
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



    public int getengscore() {
        return engscore;
    }

    public void setengscore(int eng) {
        this.engscore = eng;
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
