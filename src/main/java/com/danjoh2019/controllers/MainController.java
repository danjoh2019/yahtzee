package com.danjoh2019.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javafx.application.ConditionalFeature;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class MainController {
    private Image image1;
    private Image image2;
    private Image image3;
    private Image image4;
    private Image image5;
    private Image image6;

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
    private HBox hBox;

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

    Die dieNumber1 = new Die();
    Die dieNumber2 = new Die();
    Die dieNumber3 = new Die();
    Die dieNumber4 = new Die();
    Die dieNumber5 = new Die();

    List<Die> dies = new ArrayList<>();

    public MainController() {
        player = new Player();

        getRandomDie(dieNumber1);
        getRandomDie(dieNumber2);
        getRandomDie(dieNumber3);
        getRandomDie(dieNumber4);
        getRandomDie(dieNumber5);

        dies.add(dieNumber1);
        dies.add(dieNumber2);
        dies.add(dieNumber3);
        dies.add(dieNumber4);
        dies.add(dieNumber5);
    }

    @FXML
    private void refreshClick(ActionEvent actionEvent) {
        for (Die die : dies) {
            if (player.getTries() < 3) {
                if (!die.isSelected()) {
                    getRandomDie(die);
                }
            } else {
                getRandomDie(die);
                score.setDisable(true);
                clearDieEffects();
                rollButton.setVisible(false);
            }
        }
        player.setTries(1);

        numberOfTries.setText("Number of tries: " + player.getTries());

        die1.setImage(dieNumber1.getImage());
        die2.setImage(dieNumber2.getImage());
        die3.setImage(dieNumber3.getImage());
        die4.setImage(dieNumber4.getImage());
        die5.setImage(dieNumber5.getImage());

        playerName.setText(player.getName());

        score.setText(ScoreBoard.chance(dies));
        aces.setText(ScoreBoard.sumSingleNumberDies(1, dies));
        twos.setText(ScoreBoard.sumSingleNumberDies(2, dies));
        threes.setText(ScoreBoard.sumSingleNumberDies(3, dies));
        fours.setText(ScoreBoard.sumSingleNumberDies(4, dies));
        fives.setText(ScoreBoard.sumSingleNumberDies(5, dies));
        sixes.setText(ScoreBoard.sumSingleNumberDies(6, dies));

        total.setText(ScoreBoard.calculateTotal(aces, twos, threes, fours, fives, sixes));
        bonus.setText(ScoreBoard.bonus(total.getText()));

        threeOfAKind.setText(ScoreBoard.xOfAKind(3, dies));
        fourOfAKind.setText(ScoreBoard.xOfAKind(4, dies));


        chance.setText(ScoreBoard.chance(dies));
        yahtzee.setText(ScoreBoard.xOfAKind(5, dies));

        grand.setText(ScoreBoard.grand(aces, twos, threes, fours, fives, sixes, total, bonus, threeOfAKind, fourOfAKind, fullHouse, small, large, chance, yahtzee));
    }

    private void getRandomDie(Die die) {
        int randomDie = ThreadLocalRandom.current().nextInt(1, 6);
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

            Die shadowDie = dies.get(position);

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

    private void clearDieEffects() {
        for (Die die : dies) {
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
}
