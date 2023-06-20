package com.danjoh2019.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    private String name;
    private int score;

    private boolean save = false;
    private int tries;

    List<Integer> scorePosition;
    Map<Integer, Integer> scores;

    public Player() {
        this.name = "player";
        this.scorePosition = new ArrayList<>();
        this.scores = new HashMap<>();
        this.score = 0;
        this.tries = 0;
    }

    public int getTries() {
        return this.tries;
    }

    public void setTries(int tries) {
        if (this.tries < 3) {
            this.tries += tries;
        } else {
            this.tries = 0;
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void save(int position, int score) {
        scorePosition.add(position);
        this.scores.put(position, score);
        this.score += score;
        this.save = true;
    }

    public void toggleSave() {
        this.save = !save;
    }

    public boolean isSaved() {
        return save;
    }

    public boolean alreadySaved(int position) {
        return scorePosition.contains(position);
    }

    public int getScore() {
        return score;
    }

    public Map<Integer, Integer> getScoreMap() {
        return scores;
    }
}