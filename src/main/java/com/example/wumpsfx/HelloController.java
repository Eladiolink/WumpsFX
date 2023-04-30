package com.example.wumpsfx;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HelloController {
    @FXML
    private GridPane wumps;
    @FXML
    private ImageView position00;
    @FXML
    protected void onHelloButtonClick() {

        //welcomeText.setText("Welcome to JavaFX Application!");

    }

    @FXML
    public void Wumps(int value) throws URISyntaxException, FileNotFoundException {
        URL resourceUrl = getClass().getClassLoader().getResource("link.png");
        Path path = Paths.get(resourceUrl.toURI());
        String caminho = path.toString();

        System.out.println(caminho);
        FileInputStream input = new FileInputStream(caminho);
        Image hero = new Image(input);

        ObservableList<Node> c = wumps.getChildren();

        ImageView[][] matriz = new ImageView[4][4];
        int x = 0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                matriz[i][j] = (ImageView) c.get(x);
                x++;
            }
        }

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                matriz[i][j].setImage(null);
            }
        }

            if(value==1){
            matriz[3][0].setImage(hero);
            matriz[3][0].setFitHeight(125);
            matriz[3][0].setFitWidth(125);
        }else{
                matriz[1][2].setImage(hero);
                matriz[1][2].setFitHeight(125);
                matriz[1][2].setFitWidth(125);
            }

    }

    @FXML
    public void initialize() throws URISyntaxException, FileNotFoundException {
//        URL resourceUrl = getClass().getClassLoader().getResource("link.png");
//        Path path = Paths.get(resourceUrl.toURI());
//        String caminho = path.toString();
//
//        System.out.println(caminho);
//        FileInputStream input = new FileInputStream(caminho);
//        Image img = new Image(input);
//        position00.setImage(img);
    }
}