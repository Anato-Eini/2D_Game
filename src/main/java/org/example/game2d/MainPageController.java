package org.example.game2d;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class MainPageController {
    @FXML
    AnchorPane mainPage;
    @FXML
    Button startButton;

    @FXML
    public void startGame() throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("gameView.fxml")));
        mainPage.getChildren().clear();
        mainPage.getChildren().add(loader.load());
        GameController gameController = loader.getController();
        gameController.start();

    }
}