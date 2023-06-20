package com.danjoh2019.controllers;

public class Player {
    private String name;
    private int score;
    
    private boolean save = false;
    private int tries;

    public Player() {
        this.name = "player";
        this.score = 0;
        this.tries = 0;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        if (this.tries < 3) {
            this.tries += tries;
        }
        else {
            this.tries = 1;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void save(int score) {
        this.score += score;
        save = true;
    }

    public boolean isSaved() {
        return save;
    }

    public int getScore() {
        return score;
    }
}