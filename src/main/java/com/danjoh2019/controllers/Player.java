package com.danjoh2019.controllers;

public class Player {
    private String name;
    private int tries;

    public Player() {
        this.name = "player";
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
            this.tries = 0;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}