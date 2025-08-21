package com.example.aplikcjacrud.model;

import java.util.UUID;

public class Account {
    private int id;
    private String name;
    private int bestScore;

    public Account(int id, String name, int bestScore) {
        this.id = id;
        this.name = name;
        this.bestScore = bestScore;
    }

    public Account(String name) {
        this.id = -1;
        this.name = name;
        this.bestScore = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }
}
