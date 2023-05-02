package com.example.wumpsfx;

import com.example.wumpsfx.game.Board;
import com.example.wumpsfx.game.character.Award;
import com.example.wumpsfx.game.character.BoardItem;
import com.example.wumpsfx.game.character.Hero.Hero;
import com.example.wumpsfx.game.character.Hole.Hole;
import com.example.wumpsfx.game.character.Monster.Monster;
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
    public void Wumps(BoardItem board[][]) throws URISyntaxException, FileNotFoundException {
        ObservableList<Node> c = wumps.getChildren();

        ImageView[][] matriz = new ImageView[4][4];
        int x = 0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                matriz[i][j] = (ImageView) c.get(x);
                x++;
            }
        }

        for(int i = 0; i< Board.houses; i++){
            for(int j=0;j<Board.houses;j++){
                if(!(board[i][j] instanceof Monster) && !(board[i][j] instanceof Hole) && !(board[i][j] instanceof Award))
                    matriz[i][j].setImage(null);
            }
        }

        for(int i=0;i<Board.houses;i++){
            for(int j=0;j<Board.houses;j++){
               if(board[i][j] instanceof Hero){
                   matriz[i][j].setImage(image("link"));
               }

                if(board[i][j] instanceof Award){
                    matriz[i][j].setImage(image("award"));
                }

                if(board[i][j] instanceof Hole){
                    matriz[i][j].setImage(image("hole"));
                }

                if(board[i][j] instanceof Monster){
                    matriz[i][j].setImage(image("treeMonster"));
                }

                matriz[i][j].setFitHeight(130);
                matriz[i][j].setFitWidth(130);
            }
        }
    }

    private Image image(String name) throws URISyntaxException, FileNotFoundException {
        URL resourceUrl = getClass().getClassLoader().getResource(name+".png");
        Path path = Paths.get(resourceUrl.toURI());

        String caminho = path.toString();

        FileInputStream input = new FileInputStream(caminho);
        return new Image(input);
    }
}