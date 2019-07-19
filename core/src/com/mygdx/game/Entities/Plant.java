package com.mygdx.game.Entities;

import com.mygdx.game.Entities.World;

public class Plant {
    private int resourceOfPlant;
    private int fR;
    private int sR;
    private String fClass; //RR CR Mineral
    private String sClass;
    private int levelOfPlant = 1;
    private int profit;
    private int upgradeTime;

    // сделан для вывода дохода от одного предприятия
    public void UpdateProfit(){
        int costs = 0;
        if (upgradeTime == 0) {
            if (fClass.equals("RR")) {
                costs += World.valueRR[fR];
            }
            if (fClass.equals("CR")) {
                costs += World.valueCR[fR];
            }
            if (fClass.equals("Mineral")) {
                costs += World.valueMineral[fR];
            }
            if (sClass.equals("Mineral")) {
                costs += World.valueMineral[sR];
            }
            if (sClass.equals("RR")) {
                costs += World.valueRR[sR];
            }
            if (sClass.equals("CR")) {
                costs += World.valueCR[sR];
            }
            profit = levelOfPlant * (World.valueCR[resourceOfPlant] - costs);
        }
    }

    public int getDemand(String res, int number){
        if (upgradeTime == 0) {
            if (res.equals(fClass)) {
                if (number == fR) {
                    return levelOfPlant;
                }
            }
            if (res.equals(sClass)) {
                if (number == sR) {
                    return levelOfPlant;
                }
            }
        }
        if (upgradeTime != 0){
            upgradeTime--;
        }
        return 0;
    }

    public Plant(int resourceOfPlant) {
        this.resourceOfPlant = resourceOfPlant;
        upgradeTime = 2;
        // надо переделат это все с ифами ибо вручную все это забивать жесть
        this.fR = fR;
        this.sR = sR;
        this.fClass = fClass;
        this.sClass = sClass;
    }

    public int getResourceOfPlant() {
        return resourceOfPlant;
    }

    public int getfR() {
        return fR;
    }

    public int getsR() {
        return sR;
    }

    public String getfClass() {
        return fClass;
    }

    public String getsClass() {
        return sClass;
    }

    public int getLevelOfPlant() {
        return levelOfPlant;
    }

    public void Upgrade(){
        levelOfPlant++;
        upgradeTime = levelOfPlant + 1;
    }

    public int getProfit() {
        return profit;
    }

    public int getUpgradeTime() {
        return upgradeTime;
    }
}
