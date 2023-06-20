package com.danjoh2019.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javafx.scene.control.Label;

public class ScoreBoard {

    public static String sumSingleNumberDies(int number, List<Die> dies) {
        int sum = 0;
        for (Die die : dies) {
            if (die.getValue() == number) {
                sum += die.getValue();
            }
        }
        return Integer.toString(sum);
    }

    public static String calculateTotal(Label ones, Label twos, Label threes, Label fours, Label fives, Label sixes) {
        int sum = 0;

        sum += Integer.valueOf(ones.getText());
        sum += Integer.valueOf(twos.getText());
        sum += Integer.valueOf(threes.getText());
        sum += Integer.valueOf(fours.getText());
        sum += Integer.valueOf(fives.getText());
        sum += Integer.valueOf(sixes.getText());

        return Integer.toString(sum);
    }

    public static String bonus(int total) {
        if (total >= 63) {
            return Integer.toString(50);
        }
        return Integer.toString(0);
    }

    public static String xOfAKind(int number, List<Die> dies) {
        Map<Integer, Integer> counter = new HashMap<>();

        for (Die die : dies) {
            if (!counter.containsKey(die.getValue())) {
                counter.put(die.getValue(), 1);
            } else {
                int count = counter.get(die.getValue());
                counter.put(die.getValue(), count + 1);
            }
        }

        Iterator<Entry<Integer, Integer>> it = counter.entrySet().iterator();

        while (it.hasNext()) {
            Entry<Integer, Integer> entry = it.next();

            if (entry.getValue() >= number) {
                if (number == 5) {
                    return Integer.toString(50);
                }
                return Integer.toString(entry.getKey() * number);
            }
        }
        return Integer.toString(0);
    }

    public static String chance(List<Die> dies) {
        int scoreTotal = 0;

        for (Die die : dies) {
            scoreTotal += die.getValue();
        }

        return Integer.toString(scoreTotal);
    }

    public static String grand(Label aces, Label twos, Label threes, Label fours, Label fives, Label sixes, Label total,
            Label bonus, Label threeOfAKind, Label fourOfAKind, Label fullHouse, Label small, Label large,
            Label chance, Label yahtzee) {

        int sum = 0;

        List<Label> labels = new ArrayList<>();

        labels.add(aces);
        labels.add(twos);
        labels.add(threes);
        labels.add(fours);
        labels.add(fives);
        labels.add(sixes);
        labels.add(total);
        labels.add(bonus);
        labels.add(threeOfAKind);
        labels.add(fourOfAKind);
        labels.add(fullHouse);
        labels.add(small);
        labels.add(large);
        labels.add(chance);
        labels.add(yahtzee);

        for (Label label : labels) {
            if (!label.getText().isEmpty()) {
                sum += Integer.parseInt(label.getText());
            }
        }

        return Integer.toString(sum);
    }

    public static String fullHouse(List<Die> dies) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int sum = 0;

        for (Die die : dies) {
            countMap.put(die.getValue(), countMap.getOrDefault(die.getValue(), 0) + 1);
            sum += die.getValue();
        }

        boolean hasThreeOfAKind = false;
        boolean hasTwoOfAKind = false;

        for (int count : countMap.values()) {
            if (count == 3) {
                hasThreeOfAKind = true;
            } else if (count == 2) {
                hasTwoOfAKind = true;
            }
        }

        if (hasThreeOfAKind && hasTwoOfAKind) {
            return Integer.toString(sum);
        }

        return Integer.toString(0);
    }
    public static String smallStraight(List<Die> dice) {
        Set<Integer> uniqueDice = new HashSet<>();

        for (Die die : dice) {
            uniqueDice.add(die.getValue());
        }

        if (uniqueDice.containsAll(Arrays.asList(1, 2, 3, 4, 5))) {
            return Integer.toString(15);
        }

        return Integer.toString(0);
    }

    public static String largeStraight(List<Die> dice) {
        Set<Integer> uniqueDice = new HashSet<>();

        for (Die die : dice) {
            uniqueDice.add(die.getValue());
        }

        if (uniqueDice.containsAll(Arrays.asList(2, 3, 4, 5, 6))) {
            return Integer.toString(20);
        }

        return Integer.toString(0);
    }

    public static String updateScores(Map<Integer, Integer> scores, int position, List<Die> dice) {
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

    // private boolean checkSize(List<Die> dies) {
    // if (dies.size() != 5) {
    // return false;
    // }
    // return true;
    // }
}
