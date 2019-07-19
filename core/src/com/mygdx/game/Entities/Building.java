package com.mygdx.game.Entities;

public class Building {
    // тут идут вообще все возможные модификаторы, которые могут получить здания. Тогда здание это набор цифр, обозначающий характеристимки в заданной последовательности


    // все остальное
    private int level = 0;
    private int classOf;

    public void Upgrage(){
        level++;
    }

    public Building(int classOf) {
        this.classOf = classOf;
    }

    public int getLevel() {
        return level;
    }

    public int getClassOf() {
        return classOf;
    }
}