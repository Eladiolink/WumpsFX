package com.example.wumpsfx.game.character.Hero;

import com.example.wumpsfx.game.character.BoardItem;
import com.example.wumpsfx.game.character.Hole.Hole;
import com.example.wumpsfx.game.character.Monster.Monster;
import com.example.wumpsfx.game.entitie.Directions;
import com.example.wumpsfx.game.entitie.Move;
import com.example.wumpsfx.game.entitie.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hero extends BoardItem {
    private List<Position> knownWay = new ArrayList<>();
    public Position atualPossition = new Position();
    public  BoardItem lastItem = null;
    private int knowPossitionNumber;
    public Hero() {
        knownWay.add(new Position(0,3));
        atualPossition.x = 0;
        atualPossition.y = 3;
    }

    public void goBack(Hero hero, BoardItem board[][]){
        List<String> moves = neighbors();

        if(moves.size() == 0){
            return;
        }

        System.out.println("OK");
        Random gerador = new Random();
        int numeroAleatorio = gerador.nextInt(moves.size());
        Move.moveHero(hero,moves.get(numeroAleatorio),board);
    }

    private List<String> neighbors(){
        List<String> moves = new ArrayList<>();
        for (int i = 0; i < knownWay.size(); i++) {
            Position item = knownWay.get(i);

            if(item.x == atualPossition.x && item.y == atualPossition.y-1 ){
                moves.add(Directions.UP);
            }

            if(item.x == atualPossition.x && item.y == atualPossition.y+1 ){
                moves.add(Directions.DOWN);
            }

            if(item.x == atualPossition.x-1 && item.y == atualPossition.y ){
                moves.add(Directions.LEFT);
            }

            if(item.x == atualPossition.x+1 && item.y == atualPossition.y ){
                moves.add(Directions.RIGHT);
            }

        }

        return moves;
    }

    public void addPosition(Position position){
        if(exist(position)) return;
        knownWay.add(position);
    }

    private boolean exist(Position position){
        for (int i = 0; i < knownWay.size(); i++) {
            Position item = knownWay.get(i);
            if(item.x == position.x && item.y == position.y ){
                return true;
            }
        }
        return false;
    }

    public void up(BoardItem board[][]){
        if(atualPossition.y == 0) return;
        Hero hero = (Hero) board[atualPossition.y][atualPossition.x];

        board[atualPossition.y][atualPossition.x] = (lastItem == null) ? null : lastItem;

        Position newPosition = new Position(atualPossition.x,atualPossition.y-1);

        lastItem(newPosition,board);
        validPosition(newPosition,board);

        board[newPosition.y][newPosition.x] = hero;

        atualPossition.y--;
    }

    public void down(BoardItem board[][]){
        if(atualPossition.y == 3) return;
        Hero hero = (Hero) board[atualPossition.y][atualPossition.x];

        board[atualPossition.y][atualPossition.x] = (lastItem == null) ? null : lastItem;

        Position newPosition = new Position(atualPossition.x,atualPossition.y+1);

        lastItem(newPosition,board);
        validPosition(newPosition,board);

        board[newPosition.y][newPosition.x] = hero;

        atualPossition.y++;
    }

    public void left(BoardItem board[][]){
        if(atualPossition.x == 0) return;
        Hero hero = (Hero) board[atualPossition.y][atualPossition.x];

        board[atualPossition.y][atualPossition.x] = (lastItem == null) ? null : lastItem;

        Position newPosition = new Position(atualPossition.x-1,atualPossition.y);

        lastItem(newPosition,board);
        validPosition(newPosition,board);

        board[newPosition.y][newPosition.x] = hero;

        atualPossition.x--;
    }

    public void right(BoardItem board[][]){
        if(atualPossition.x == 3) return;
        Hero hero = (Hero) board[atualPossition.y][atualPossition.x];

        board[atualPossition.y][atualPossition.x] = (lastItem == null) ? null : lastItem;

        Position newPosition = new Position(atualPossition.x+1,atualPossition.y);

        lastItem(newPosition,board);
        validPosition(newPosition,board);

        board[newPosition.y][newPosition.x] = hero;

        atualPossition.x++;
    }

    private boolean lastItem(Position position, BoardItem boardItem[][]){
        if(boardItem[position.y][position.x] == null) {
            lastItem = null;
            return true;
        }
        lastItem = boardItem[position.y][position.x];
        return false;
    }
    public void validPosition(Position position, BoardItem boardItem[][]){
        if(boardItem[position.y][position.x] instanceof Monster || boardItem[position.y][position.x] instanceof Hole) return;
        addPosition(position);
    }

    public boolean inBoss(){
        if(lastItem instanceof Monster || lastItem instanceof Hole) return true;
        return false;
    }
    public void printKnowWay(){
        for(Position way: knownWay){
            System.out.println(way);
        }
    }
}
