package com.danjoh2019.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
        player = new Player();

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
        // if (!player.isSaved() && player.getTries() < 3) {
        //     for (Die die : dice) {
        //         // if (player.getTries() < 3) {
        //             if (!die.isSelected()) {
        //                 getRandomDie(die);
        //             // }
        //         } else {
        //             getRandomDie(die);
        //             score.setDisable(true);
        //             clearDieEffects();
        //             // rollButton.setDisable(true);
        //             player.toggleSave();
        //         }
        //     }
        // }

        // System.out.println(actionEvent.toString());

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
        // if (player.getScoreMap().containsKey(1)) {
        //     aces.setText(Integer.toString(player.getScoreMap().get(1)));
        //     System.out.println("Aces already saved. Displaying saved state instead");
        // } else {
        //     aces.setText(ScoreBoard.sumSingleNumberDies(1, dice));
        // }

        // if (player.getScoreMap().containsKey(2)) {
        //     twos.setText(Integer.toString(player.getScoreMap().get(2)));
        //     System.out.println("Twos already saved. Displaying saved state instead");
        // } else {
        //     twos.setText(ScoreBoard.sumSingleNumberDies(2, dice));
        // }
        // threes.setText(ScoreBoard.sumSingleNumberDies(3, dice));
        // fours.setText(ScoreBoard.sumSingleNumberDies(4, dice));
        // fives.setText(ScoreBoard.sumSingleNumberDies(5, dice));
        // sixes.setText(ScoreBoard.sumSingleNumberDies(6, dice));
        // aces.setText(player.getScoreFromScoreMap(1, dice));
        // twos.setText(player.getScoreFromScoreMap(2, dice));
        // threes.setText(player.getScoreFromScoreMap(3, dice));
        // fours.setText(player.getScoreFromScoreMap(4, dice));
        // fives.setText(player.getScoreFromScoreMap(5, dice));
        // sixes.setText(player.getScoreFromScoreMap(6, dice));

        // total.setText(player.getScoreFromScoreMap(7, dice));
        // bonus.setText(player.getScoreFromScoreMap(8, dice));

        // threeOfAKind.setText(player.getScoreFromScoreMap(9, dice));
        // fourOfAKind.setText(player.getScoreFromScoreMap(10, dice));

        // fullHouse.setText(player.getScoreFromScoreMap(11, dice));

        // small.setText(player.getScoreFromScoreMap(12, dice));
        // large.setText(player.getScoreFromScoreMap(13, dice));

        // chance.setText(player.getScoreFromScoreMap(14, dice));
        // yahtzee.setText(player.getScoreFromScoreMap(15, dice));

        // grand.setText(player.getScoreFromScoreMap(16, dice));

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

        // total.setText(ScoreBoard.calculateTotal(aces, twos, threes, fours, fives, sixes));
        // bonus.setText(ScoreBoard.bonus(total.getText()));

        // threeOfAKind.setText(ScoreBoard.xOfAKind(3, dice));
        // fourOfAKind.setText(ScoreBoard.xOfAKind(4, dice));

        // fullHouse.setText(ScoreBoard.fullHouse(dice));

        // small.setText(ScoreBoard.smallStraight(dice));
        // large.setText(ScoreBoard.largeStraight(dice));

        // chance.setText(ScoreBoard.chance(dice));
        // yahtzee.setText(ScoreBoard.xOfAKind(5, dice));

        // grand.setText(ScoreBoard.grand(aces, twos, threes, fours, fives, sixes, total, bonus, threeOfAKind, fourOfAKind,
        //         fullHouse, small, large, chance, yahtzee));
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

            Label score = scoreLabels.get(position);

            if (!player.alreadySaved(position)) {
                System.out.println(label.getText());
                player.save(position, Integer.parseInt(label.getText()));
                // label.setEffect(null);
                label.setTextFill(Color.HOTPINK);
                System.out.println(label.getText());
                System.out.println(label.getId());
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
