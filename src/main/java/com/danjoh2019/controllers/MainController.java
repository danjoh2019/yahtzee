package com.danjoh2019.controllers;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.danjoh2019.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private Button finishedButton;

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
    private Label onePair;

    @FXML
    private Label twoPairs;

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

    private Highscore highscore;

    private Die dieNumber1 = new Die();
    private Die dieNumber2 = new Die();
    private Die dieNumber3 = new Die();
    private Die dieNumber4 = new Die();
    private Die dieNumber5 = new Die();

    private List<Die> dice = new ArrayList<>();
    private List<Label> scoreLabels = new ArrayList<>();

    public MainController() {
        player = new Player("Player 1");

        dice.add(dieNumber1);
        dice.add(dieNumber2);
        dice.add(dieNumber3);
        dice.add(dieNumber4);
        dice.add(dieNumber5);

        reRollAllDice();

        for (int i = 0; i < 16; i++) {
            scoreLabels.add(new Label());
        }

        highscore = new Highscore();

        // finishedButton.setVisible(false);
    }

    private void reRollAllDice() {
        getRandomDie(dieNumber1);
        getRandomDie(dieNumber2);
        getRandomDie(dieNumber3);
        getRandomDie(dieNumber4);
        getRandomDie(dieNumber5);
    }

    private void setDiceImages() {
        die1.setImage(dieNumber1.getImage());
        die2.setImage(dieNumber2.getImage());
        die3.setImage(dieNumber3.getImage());
        die4.setImage(dieNumber4.getImage());
        die5.setImage(dieNumber5.getImage());
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
            player.setTries();
        }

        if (!player.isSaved() && player.getTries() == 3) {
            rollButton.setDisable(true);
        }

        numberOfTries.setText("Number of tries: " + player.getTries());

        setDiceImages();

        
        playerName.setText(player.getName());
        updateScores();
        //score.setText(Integer.toString(player.getScore()));
    }

    @FXML
    private void showScores(ActionEvent actionEvent) {

        try {
            // Stage stage = new Stage();
            // Parent root = FXMLLoader.load(App.class.getResource("popup.fxml"));

            // stage.setScene(new Scene(root));
            // stage.initModality(Modality.APPLICATION_MODAL);
            // stage.initStyle(StageStyle.TRANSPARENT);
            // stage.setUserData(player);
            // stage.showAndWait();

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("secondary.fxml"));
            Parent root2 = fxmlLoader.load();
            var scene = new Scene(root2, 640, 480);
            Node node = (Node) actionEvent.getSource();
            Stage stage2 = (Stage) node.getScene().getWindow();
            stage2.setScene(scene);
            stage2.show();
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }

        // rollButton.setVisible(false);
        // finishedButton.setVisible(true);
    }

    private void newHighscore() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(App.class.getResource("popup.fxml"));

            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setUserData(player);
            stage.showAndWait();

        
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
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

        onePair.setText(ScoreBoard.updateScores(player.getScoreMap(), 17, dice));
        twoPairs.setText(ScoreBoard.updateScores(player.getScoreMap(), 18, dice));
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

            if (!player.alreadySaved(position)) {
                // save score on specified label
                player.save(position, Integer.parseInt(label.getText()));

                // change text colour on label
                label.setTextFill(Color.HOTPINK);

                // enable the roll button after save
                rollButton.setDisable(false);

                // unselect all dice
                clearDieEffects();

                player.toggleSave();
                player.resetTries();

                reRollAllDice();
                updateScores();
                setDiceImages();

                if (player.checkIfPlayerWon()) {
                    player.save(8, Integer.parseInt(ScoreBoard.updateScores(player.getScoreMap(), 8, dice)));
                    rollButton.setVisible(false);
                    finishedButton.setVisible(true);
                    if (highscore.checkIfNewHighscore(player)) {
                        // highscore.saveHighscore(player);
                        newHighscore();
                    }
                }
            } else {
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
