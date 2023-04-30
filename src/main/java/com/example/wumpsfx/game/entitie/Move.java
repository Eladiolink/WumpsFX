package com.example.wumpsfx.game.entitie;

import com.example.wumpsfx.game.character.BoardItem;
import com.example.wumpsfx.game.character.Hero.Hero;

import java.util.Random;

public class Move {
    public static String[] probableToMove(Hero hero){
        String[] directions;

        if((hero.atualPossition.x == 0 && hero.atualPossition.y == 3)){
            directions = new String[2];
            directions[0] = Directions.UP;
            directions[1] = Directions.RIGHT;
            return directions;
        }

        if(hero.atualPossition.x == 0 && (hero.atualPossition.y >=1 && hero.atualPossition.y <=2)){
            directions = new String[3];
            directions[0] = Directions.UP;
            directions[1] = Directions.DOWN;
            directions[2] = Directions.RIGHT;
            return directions;
        }

        if(hero.atualPossition.x == 0 && hero.atualPossition.y == 0){
            directions = new String[2];
            directions[0] = Directions.DOWN;
            directions[1] = Directions.RIGHT;
            return directions;
        }

        if(hero.atualPossition.y == 0 && (hero.atualPossition.x >=1 && hero.atualPossition.x <=2)){
            directions = new String[3];
            directions[0] = Directions.DOWN;
            directions[1] = Directions.RIGHT;
            directions[2] = Directions.LEFT;
            return directions;
        }

        if(hero.atualPossition.x == 3 && hero.atualPossition.y == 0){
            directions = new String[2];
            directions[0] = Directions.DOWN;
            directions[1] = Directions.LEFT;
            return directions;
        }

        if(hero.atualPossition.x == 3 && (hero.atualPossition.y >=1 && hero.atualPossition.y <=2)){
            directions = new String[3];

            directions[0] = Directions.UP;
            directions[1] = Directions.DOWN;
            directions[2] = Directions.LEFT;
            return directions;
        }

        if(hero.atualPossition.x == 3 && hero.atualPossition.y == 3){
            directions = new String[2];
            directions[0] = Directions.UP;
            directions[1] = Directions.LEFT;
            return directions;
        }

        if(hero.atualPossition.y == 3 && (hero.atualPossition.x >=1 && hero.atualPossition.x <=2)){
            directions = new String[3];
            directions[0] = Directions.UP;
            directions[1] = Directions.RIGHT;
            directions[2] = Directions.LEFT;
            return directions;
        }

        directions = new String[4];
        directions[0] = Directions.UP;
        directions[1] = Directions.DOWN;
        directions[2] = Directions.LEFT;
        directions[3] = Directions.RIGHT;

        return directions;
    }

    public static void move(Hero hero, String move[], BoardItem board[][]){
        Random gerador = new Random();
        int numeroAleatorio = gerador.nextInt(move.length);

        String newMove = move[numeroAleatorio];

        moveHero(hero,newMove, board);
    }

    public static void moveHero(Hero hero,String newMove, BoardItem board[][]){
        switch (newMove){
            case "UP":
                hero.up(board);
                break;
            case "DOWN":
                hero.down(board);
                break;
            case  "LEFT":
                hero.left(board);
                break;
            case "RIGHT":
                hero.right(board);
                break;
        }
    }
    public static int percenteToExplore(){
        Random gerador = new Random();
        int numeroAleatorio = gerador.nextInt(100);

        return numeroAleatorio;
    }

}
