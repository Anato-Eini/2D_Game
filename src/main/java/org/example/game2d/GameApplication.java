package org.example.game2d;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class GameApplication extends Application {
    static final int tileSize = 16;
    static final int scale = 3;
    static final int finalTileSize = tileSize * scale;
    static final int screenCol = 28;
    static final int screenRow = 15;
    static final int screenWidth = finalTileSize * screenCol;
    static final int screenHeight = finalTileSize * screenRow;
    public static SocketClient socketClient;
    @Override
    public void start(Stage stage) throws IOException {
        socketClient = new SocketClient();
        Thread thread = new Thread(socketClient);
        thread.start();
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("mainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), screenWidth, screenHeight);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("2DGame");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}