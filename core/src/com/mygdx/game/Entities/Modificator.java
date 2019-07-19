package com.mygdx.game.Entities;

public class Modificator {
    // тут перечисленны все, на что могут влиять ивенты
    private int modBuildingCost;
    private int modTactic;
    private int modShock;
    private int modFire;

    private int modExchangeReligion;
    private int rebelLevel;
    private int modExchangeCulture;
    // показатель активности модификатора
    private int is = 0;
    private int time;
    private int maxTime;

    public void Activate(){
        time = maxTime;
        is = 1;
    }
    public void Deactivate(){
        time = 0;
        is = 0;
    }

    public void Turn(){
        if (time == 0) {
            time--;
        } else {
            Deactivate();
        }
    }

    public int getIs() {
        return is;
    }

    public int getTime() {
        return time;
    }

    public int getModBuildingCost() {
        return modBuildingCost;
    }

    public int getModTactic() {
        return modTactic;
    }

    public int getModShock() {
        return modShock;
    }

    public int getModFire() {
        return modFire;
    }

    public int getModExchangeReligion() {
        return modExchangeReligion;
    }

    public int getRebelLevel() {
        return rebelLevel;
    }

    public int getModExchangeCulture() {
        return modExchangeCulture;
    }
}
