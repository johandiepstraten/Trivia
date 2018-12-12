package com.example.johan.trivia;

import java.io.Serializable;

public class Score implements Serializable {
    private String name;
    private int score, ranking;
    private long time;

    public Score(String name, int score, long time, int ranking) {
        this.name = name;
        this.score = score;
        this.time = time;
        this.ranking = ranking;
    }
//    Initiate getters and setters
    public String getName() {return name;}
    public int getScore()   {return score;}
    public long getTime()   {return time;}
    public int getRanking() {return ranking;}

    public void setName(String name)    {
        this.name = name;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setTime(long time)  {
        this.time = time;
    }
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

}
