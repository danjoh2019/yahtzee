package com.danjoh2019.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class MainController {
    private Player player;

    @FXML
    private ImageView die1;

    @FXML
    private ImageView die2;

    @FXML
    private ImageView die3;

    @FXML
    private ImageView die4;

    @FXML
    private ImageView die5;

    @FXML
    private Label score;

    @FXML
    private Label labelTitle;

    @FXML
    private HBox hBox;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button rollButton;

    @FXML
    private Label playerName;

    @FXML
    private Label aces;

    @FXML
    private Label twos;

    @FXML
    private Label threes;

    @FXML
    private Label fours;

    @FXML
    private Label fives;

    @FXML
    private Label sixes;

    @FXML
    private Label total;

    @FXML
    private Label bonus;

    @FXML
    private Label threeOfAKind;

    @FXML
    private Label fourOfAKind;

    @FXML
    private Label fullHouse;

    @FXML
    private Label small;

    @FXML
    private Label large;

    @FXML
    private Label chance;

    @FXML
    private Label yahtzee;

    @FXML
    private Label numberOfTries;

    @FXML
    private Label grand;

    private Die dieNumber1 = new Die();
    private Die dieNumber2 = new Die();
    private Die dieNumber3 = new Die();
    private Die dieNumber4 = new Die();
    private Die dieNumber5 = new Die();

    private List<Die> dice = new ArrayList<>();
    private List<Label> scoreLabels = new ArrayList<>();

    public MainController() {
        player = new Player("Player 1");

        getRandomDie(dieNumber1);
        getRandomDie(dieNumber2);
        getRandomDie(dieNumber3);
        getRandomDie(dieNumber4);
        getRandomDie(dieNumber5);

        dice.add(dieNumber1);
        dice.add(dieNumber2);
        dice.add(dieNumber3);
        dice.add(dieNumber4);
        dice.add(dieNumber5);

        for (int i = 0; i < 16; i++) {
            scoreLabels.add(new Label());
        }
    }

    @FXML
    private void refreshClick(ActionEvent actionEvent) {
        if (!player.isSaved() && player.getTries() < 3) {
            for (Die die : dice) {
                if (!die.isSelected()) {
                    getRandomDie(die);
                }
                // rollButton.setDisable(false);
            }
            player.setTries(1);
        }

        if (!player.isSaved() && player.getTries() == 3) {
            rollButton.setDisable(true);
        }

        // if (player.isSaved())

        numberOfTries.setText("Number of tries: " + player.getTries());

        die1.setImage(dieNumber1.getImage());
        die2.setImage(dieNumber2.getImage());
        die3.setImage(dieNumber3.getImage());
        die4.setImage(dieNumber4.getImage());
        die5.setImage(dieNumber5.getImage());

        score.setText(Integer.toString(player.getScore()));

        playerName.setText(player.getName());
        updateScores();
    }

    private void updateScores() {
        aces.setText(ScoreBoard.updateScores(player.getScoreMap(), 1, dice));
        twos.setText(ScoreBoard.updateScores(player.getScoreMap(), 2, dice));
        threes.setText(ScoreBoard.updateScores(player.getScoreMap(), 3, dice));
        fours.setText(ScoreBoard.updateScores(player.getScoreMap(), 4, dice));
        fives.setText(ScoreBoard.updateScores(player.getScoreMap(), 5, dice));
        sixes.setText(ScoreBoard.updateScores(player.getScoreMap(), 6, dice));

        total.setText(ScoreBoard.updateScores(player.getScoreMap(), 7, dice));
        bonus.setText(ScoreBoard.updateScores(player.getScoreMap(), 8, dice));

        threeOfAKind.setText(ScoreBoard.updateScores(player.getScoreMap(), 9, dice));
        fourOfAKind.setText(ScoreBoard.updateScores(player.getScoreMap(), 10, dice));

        fullHouse.setText(ScoreBoard.updateScores(player.getScoreMap(), 11, dice));

        small.setText(ScoreBoard.updateScores(player.getScoreMap(), 12, dice));
        large.setText(ScoreBoard.updateScores(player.getScoreMap(), 13, dice));

        chance.setText(ScoreBoard.updateScores(player.getScoreMap(), 14, dice));
        yahtzee.setText(ScoreBoard.updateScores(player.getScoreMap(), 15, dice));

        grand.setText(ScoreBoard.updateScores(player.getScoreMap(), 16, dice));
    }

    private void getRandomDie(Die die) {
        int randomDie = ThreadLocalRandom.current().nextInt(1, 7);
        die.setImage(new Image(getClass().getResourceAsStream("gfx/die" + randomDie + ".png"), 64, 64, true, true));
        die.setValue(randomDie);
    }

    @FXML
    private void dieClicked(MouseEvent mouseEvent) {
        // which control did we click on? it is called the source.
        Object source = mouseEvent.getSource();
        if (source instanceof ImageView) {
            ImageView image = (ImageView) source;
            int position = getPosition(image);

            Die shadowDie = dice.get(position);

            if (shadowDie.isSelected()) {
                image.setEffect(null);
                shadowDie.setSelected(false);
            } else {
                image.setEffect(new ColorAdjust(0, 0.4, 0, 0));
                // image.setEffect(new DropShadow());
                shadowDie.setSelected(true);
            }
        }
    }

    @FXML
    private void scoreClicked(MouseEvent mouseEvent) {
        // which control did we click on? it is called the source.
        Object source = mouseEvent.getSource();
        if (source instanceof Label) {
            Label label = (Label) source;

            int position = getLabelPosition(label);

            // Label score = scoreLabels.get(position);

            if (!player.alreadySaved(position)) {
                player.save(position, Integer.parseInt(label.getText()));
                label.setTextFill(Color.HOTPINK);
                rollButton.setDisable(false);
                clearDieEffects();
                player.toggleSave();
                player.setTries(3);
            } else {
                // label.setEffect(new ColorAdjust(0, 0.4, 0, 0));
                // image.setEffect(new DropShadow());
                // label.setTextFill(Color.WHITE);
                System.out.println("already saved");
            }
        }
    }

    private void clearDieEffects() {
        for (Die die : dice) {
            die.setSelected(false);
        }

        die1.setEffect(null);
        die2.setEffect(null);
        die3.setEffect(null);
        die4.setEffect(null);
        die5.setEffect(null);
    }

    /**
     * Get the position within the HBox for the given ImageView.
     * Left die will be 0, right will be 4.
     */
    private int getPosition(ImageView image) {
        int count = 0;
        for (var child : hBox.getChildrenUnmodifiable()) {
            if (child == image) {
                break;
            }
            count++;
        }
        return count;
    }

    /**
     * Get the position within the GridPane for the given Label.
     * First score label will be 1, last will be 16.
     */
    private int getLabelPosition(Label label) {
        int count = 0;

        for (var child : gridPane.getChildrenUnmodifiable()) {
            if (!child.getId().equals("labelTitle")) {
                if (child == label) {
                    break;
                }
                count++;
            }
        }

        return count;
    }
}
