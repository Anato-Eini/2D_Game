package org.example.game2d;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameController{
    public AnchorPane gameView;
    Player character;

    public void start(){
        character = new Player(gameView);
        gameView.setPrefWidth(GameApplication.screenWidth);
        gameView.setPrefHeight(GameApplication.screenHeight);
        gameView.getChildren().add(character.character);
        gameView.requestFocus();
    }
}
