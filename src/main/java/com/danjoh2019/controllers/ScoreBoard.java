package com.danjoh2019.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javafx.fxml.FXML;
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

    public static String bonus(String total) {
        if (Integer.parseInt(total) >= 63) {
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
}