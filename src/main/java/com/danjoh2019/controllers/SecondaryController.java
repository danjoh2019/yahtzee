package com.danjoh2019.controllers;

import java.io.IOException;
import java.io.UncheckedIOException;

import com.danjoh2019.App;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SecondaryController {
    @FXML
    private Button newGameButton;

    // @FXML
    // public void initialize() {

    // }

    @FXML
    private void newGame(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
            Parent root = fxmlLoader.load();
            var scene = new Scene(root, 640, 480);
            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
