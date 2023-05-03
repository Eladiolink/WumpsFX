package com.example.wumpsfx.game;

import com.example.wumpsfx.HelloController;
import com.example.wumpsfx.game.character.Award;
import com.example.wumpsfx.game.character.BoardItem;
import com.example.wumpsfx.game.character.BreezeOrStink.BreezeAndStink;
import com.example.wumpsfx.game.character.Hero.Hero;
import com.example.wumpsfx.game.character.Hole.Breeze;
import com.example.wumpsfx.game.character.Hole.Hole;
import com.example.wumpsfx.game.character.Monster.Monster;
import com.example.wumpsfx.game.character.Monster.Stink;
import com.example.wumpsfx.game.entitie.Move;
import com.example.wumpsfx.game.entitie.Position;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Random;

public class Board {

    public static int houses = 4;
    private BoardItem[][] board = new BoardItem[houses][houses];
    public Board() {
        System.out.println("Hello!");

        addMoster();
        addHole();
        addAward();
        board[houses-1][0] = new Hero();

        printBoard();
    }

    public void game(HelloController controller) throws FileNotFoundException, URISyntaxException, InterruptedException {
        Hero hero = (Hero) board[houses-1][0];

        controller.Wumps(board);
        Thread.sleep(500);
        while(!hero.inBoss()){

            if(hero.lastItem instanceof Breeze || hero.lastItem instanceof Stink || hero.lastItem instanceof BreezeAndStink ){
                if(Move.percenteToExplore() > 90){
                    Move.move(hero,Move.probableToMove(hero),board);
                }else{
                    hero.goBack(hero,board);
                }
            }else {
                Move.move(hero,Move.probableToMove(hero),board);
            }

            printBoard();
            controller.Wumps(board);

            if(hero.inBoss()) {
                System.out.println("Você Perdeu !!!");
                break;
            }

            if(hero.lastItem instanceof  Award) {
                System.out.println("PARABÉNS!!!");
                break;
            }
            Thread.sleep(500);
        }

    }

    private void printBoard(){
        System.out.println();

        for(int i=0;i<houses;i++){
            for(int j=0;j<houses;j++){
                if(board[i][j]==null){
                    System.out.print(" O ");
                }else{
                    System.out.print(instanceOfForPrint(board[i][j]));
                }
            }
            System.out.println();
        }
    }

    private String instanceOfForPrint(BoardItem item){
        if(item instanceof Hero) return " X ";

        if(item instanceof Monster) return " M ";

        if(item instanceof Stink) return " s ";

        if(item instanceof Hole) return " H ";

        if(item instanceof Breeze) return " b ";

        if(item instanceof Award) return " A ";

        if(item instanceof BreezeAndStink) return " BS";

        return " = ";
    }

    private Position randomPosition(){
        Random random = new Random();
        Position position = new Position();
        position.x = random.nextInt(houses);
        position.y = random.nextInt(houses);

        while(board[position.y][position.x] != null){
            position.x = random.nextInt(houses);
            position.y = random.nextInt(houses);
        }
        return  position;
    }

    private void addMoster(){
        Position p = randomPosition();

        board[p.y][p.x] = new Monster();
        // top
        if(!(p.y-1 < 0)){
            if(instanceBreeOrStink(board[p.y-1][p.x])) board[p.y-1][p.x] = new BreezeAndStink();
            else board[p.y-1][p.x] = new Stink();
        }
        //down
        if(!(p.y+1 > houses-1)){
            if(instanceBreeOrStink(board[p.y+1][p.x])) board[p.y+1][p.x] = new BreezeAndStink();
            else board[p.y+1][p.x] = new Stink();
        }

        // left
        if(!(p.x-1 < 0)){
            if(instanceBreeOrStink(board[p.y][p.x-1])) board[p.y][p.x-1] = new BreezeAndStink();
            else board[p.y][p.x-1] = new Stink();
        }
        //right
        if(!(p.x+1 > houses-1)){
            if(instanceBreeOrStink(board[p.y][p.x+1])) board[p.y][p.x+1] = new BreezeAndStink();
            else board[p.y][p.x+1] = new Stink();
        }
    }

    private void addHole(){
        Position p = randomPosition();

        board[p.y][p.x] = new Hole();

        //top
        if(!(p.y-1 < 0)){
            if(instanceBreeOrStink(board[p.y-1][p.x])) board[p.y-1][p.x] = new BreezeAndStink();
            else board[p.y-1][p.x] = new Breeze();
        }
        //down
        if(!(p.y+1 > houses-1)){
            if(instanceBreeOrStink(board[p.y+1][p.x])) board[p.y+1][p.x] = new BreezeAndStink();
            else board[p.y+1][p.x] = new Breeze();
        }

        // left
        if(!(p.x-1 < 0)){
            if(instanceBreeOrStink(board[p.y][p.x-1])) board[p.y][p.x-1] = new BreezeAndStink();
            else board[p.y][p.x-1] = new Breeze();
        }
        //right
        if(!(p.x+1 > houses-1)){
            if(instanceBreeOrStink(board[p.y][p.x+1])) board[p.y][p.x+1] = new BreezeAndStink();
            else board[p.y][p.x+1] = new Breeze();
        }
    }

    private void addAward(){
        Position p = randomPosition();

        board[p.y][p.x] = new Award();
    }

    private boolean instanceBreeOrStink(BoardItem item){
        if(item instanceof Breeze || item instanceof Stink) return true;
        return false;
    }

}
