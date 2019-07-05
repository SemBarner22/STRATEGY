package com.mygdx.game.Scenes;

public class Region {
    private int squareOfGround = 10;
    private City[] cities;
    private int population;
    private int prosperity;
    public Region(int squareOfGround, City[] cities, int population, int prosperity, int squareOfGround){
        this.cities = cities;
        this.population =population;
        this.prosperity = prosperity;
        this.squareOfGround = squareOfGround;
    }
    public int GetSquare(){
        return(squareOfGround);
    }
    public int GetPopulation(){
        return(population);
    }
    public int GetProsperity(){
        return(prosperity);
    }
}
