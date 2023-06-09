package com.danjoh2019.controllers;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ScoreBoard {
    @FXML
    private String playerName;

    // @FXML
    // private int aces;

    @FXML
    private int twos;

    @FXML
    private int threes;

    @FXML
    private int fours;

    @FXML
    private int fives;

    @FXML
    private int sixes;

    @FXML
    private int total;

    @FXML
    private int bonus;

    @FXML
    private int threeOfAKind;

    @FXML
    private int fourOfAKind;

    @FXML
    private int fullHouse;

    @FXML
    private int small;

    @FXML
    private int large;

    @FXML
    private int chance;

    @FXML
    private int yahtzee;

    @FXML
    private int grand;

    static String sumSingleNumberDies(int number, List<Die> dies) {
        int sum = 0;
        for (Die die : dies) {
            if (die.getValue() == number) {
                sum += die.getValue();
            }
        }
        return Integer.toString(sum);
    }

    static String calculateTotal(Label ones, Label twos, Label threes, Label fours, Label fives, Label sixes) {
        int sum = 0;

        sum += Integer.valueOf(ones.getText());
        sum += Integer.valueOf(twos.getText());
        sum += Integer.valueOf(threes.getText());
        sum += Integer.valueOf(fours.getText());
        sum += Integer.valueOf(fives.getText());
        sum += Integer.valueOf(sixes.getText());

        return Integer.toString(sum);
    }

    static String bonus(String total) {
        if (Integer.parseInt(total) > 20) {
            return Integer.toString(50);
        }
        return Integer.toString(0);
    }
}
