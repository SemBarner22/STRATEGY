package com.mygdx.game.Entities;

public class Position {
    public int x;
    public int y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int GetX(){
        return x;
    }
    public int GetY(){
        return y;
    }
}
