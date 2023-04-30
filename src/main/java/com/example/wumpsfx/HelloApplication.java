package com.example.wumpsfx;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class HelloApplication extends Application {
    static HelloController controller;
    @Override
    public void start(Stage stage) throws IOException, URISyntaxException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("wumpsfx.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Hello!");
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);

        controller = fxmlLoader.getController();

        stage.show();

        Thread loop = new Thread(()->{
            try {
                in();
                System.out.println("OK");
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

    private void in() throws FileNotFoundException, URISyntaxException, InterruptedException {
        controller.Wumps(1);
        Thread.sleep(2500);
        controller.Wumps(0);
    }
    public static void main(String[] args){
        launch();
    }
}