package com.danjoh2019.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PopupController {

    @FXML
    private Button okButton;

    @FXML
    private TextField nameField;

    @FXML
    private void closePopup() {
        Highscore highscore = new Highscore();

        Stage stage = (Stage) okButton.getScene().getWindow();
        Player player = (Player) stage.getUserData();

        StringBuilder name = new StringBuilder();

        for (int i = 0; i < nameField.getText().length() && i < 10; i++) {
            if (Character.isAlphabetic(nameField.getText().charAt(i))) {
                name.append(nameField.getText().charAt(i));
            }
        }

        player.setName(name.toString());

        highscore.saveHighscore(player);

        stage.close();
    }
}
