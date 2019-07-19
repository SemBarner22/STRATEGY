package com.mygdx.game.Entities;

public class MapOfArmies {
    private int n;
    private int m;
    private int[][] map = new int[n][m];
    public MapOfArmies(int n, int m){
        this.n = n;
        this.m = m;
    }
    public void AddArmy(int countryTag, Position position){
        map[position.GetX()][position.GetY()] = countryTag;
    }
    public int CheckPosition(Position position){
        return map[position.GetX()][position.GetY()];
    }
    public void moveArmy(Position first, Position last){
        map[last.GetX()][first.GetY()] = map[first.GetX()][first.GetY()];
        map[first.GetX()][first.GetY()] = 0;
    }

}
