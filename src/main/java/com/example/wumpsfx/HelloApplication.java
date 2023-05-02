package com.example.wumpsfx;

import com.example.wumpsfx.game.Board;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class HelloApplication extends Application {
    static HelloController controller;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("wumpsfx.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("WUMPS!");
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);

        controller = fxmlLoader.getController();

        stage.show();

        Thread loop = new Thread(()->{
            Board game = new Board();

            try {
                game.game(controller);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        loop.start();
    }

    public static void main(String[] args){
        launch();
    }
}