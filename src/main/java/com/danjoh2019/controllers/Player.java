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

    public String getScoreFromScoreMap(int position, List<Die> dice) {
        String result = Integer.toString(0);

        if (scores.containsKey(position)) {
            return Integer.toString(scores.get(position));
        }

        switch (position) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                result = ScoreBoard.sumSingleNumberDies(position, dice);
                break;

            case 7:
                int sumOneToSix = 0;

                for (int i = 1; i <= 6; i++) {
                    if (scores.get(i) != null) {
                        sumOneToSix += scores.get(i);
                    }
                    sumOneToSix += 0;
                }

                result = Integer.toString(sumOneToSix);
                break;

            case 8:
                int sumBonus = 0;
                
                for (int i = 1; i <= 6; i++) {
                    if (scores.get(i) != null) {
                        sumBonus += scores.get(i);
                    }
                    sumBonus += 0;
                }

                result = ScoreBoard.bonus(sumBonus);
                break;

            case 9:
                result = ScoreBoard.xOfAKind(3, dice);
                break;

            case 10:
                result = ScoreBoard.xOfAKind(4, dice);
                break;

            case 11:
                result = ScoreBoard.fullHouse(dice);
                break;

            case 12:
                result = ScoreBoard.smallStraight(dice);
                break;

            case 13:
                result = ScoreBoard.largeStraight(dice);
                break;

            case 14:
                result = ScoreBoard.chance(dice);
                break;

            case 15:
                result = ScoreBoard.xOfAKind(5, dice);
                break;

            case 16:
                int sum = 0;

                if (!scores.isEmpty()) {
                    for (int val : scores.values()) {
                        sum += val;
                    }
                }

                result = Integer.toString(sum);
                break;
        }

        return result;
    }
}