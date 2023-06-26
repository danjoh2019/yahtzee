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
    Map<Integer, Integer> scoreMap;

    public Player(String name) {
        this.name = name;
        this.scorePosition = new ArrayList<>();
        this.scoreMap = new HashMap<>();
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
        this.scoreMap.put(position, score);
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

    public boolean checkIfPlayerWon() {
        int arr[] = { 1, 2, 3, 4, 5, 6, 9, 10, 11, 12, 13, 14, 15 };
        
        for (int num : arr) {
            if (!scoreMap.containsKey(num)) {
                System.out.println("not finished yet: " + score);
                return false;
            }
        }
        return true;
    }

    public Map<Integer, Integer> getScoreMap() {
        return scoreMap;
    }
}