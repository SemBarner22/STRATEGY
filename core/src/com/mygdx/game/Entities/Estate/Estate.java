package com.mygdx.game.Entities.Estate;

import com.mygdx.game.Entities.Estate.Ability;

public class Estate {
    protected int isInLobby = 0; // показывает является ли в частью лобби 1/0
    protected int power = 100;
    protected int powerIncrease = 0;
    protected int loyality = 500;
    protected int loyalityIncrease = 1;
    protected int maxLoyality = 500;
    protected Ability[] ability;
    protected int partOfPover;
    protected int profit = 10;

    // дальше идут бафы
    // генералы
    protected int tactic;
    protected int rebelLevel;

    // промышленники
    protected int profitFromProduction;
    protected int plusMoney = 0;

    // кто-нибудь
    protected int modBuildingCost;
    protected int profitFromRegion;
    protected int profitFromMineral;
    protected int profitFromCity;

    public void setPartOfPover(int partOfPover) {
        this.partOfPover = partOfPover;
    }

    public int getProfit() {
        return profit;
    }

    public void UpdateBonus() {
        tactic = 0;
        rebelLevel = 0;
        profit = 1;
    }

    public void UpdateLP() {
        for (int i = 0; i < ability.length; i++) {
            ability[i].Time();
        }
        if (loyality > maxLoyality) {
            loyality -= loyalityIncrease;
        }
        if (loyality < maxLoyality) {
            loyality += loyalityIncrease;
        }
        power += powerIncrease;
    }

    public int getIsInLobby() {
        return isInLobby;
    }

    public int getPower() {
        return power;
    }

    public int getPowerIncrease() {
        return powerIncrease;
    }

    public int getLoyality() {
        return loyality;
    }

    public int getLoyalityIncrease() {
        return loyalityIncrease;
    }

    public int getMaxLoyality() {
        return maxLoyality;
    }

    public Ability[] getAbility() {
        return ability;
    }

    public int getPartOfPover() {
        return partOfPover;
    }

    public int getTactic() {
        return tactic;
    }

    public int getRebelLevel() {
        return rebelLevel;
    }

    public void setPowerIncrease(int poverIncrease) {
        this.powerIncrease = poverIncrease;
    }

    public void setLoyalityIncrease(int loyalityIncrease) {
        this.loyalityIncrease = loyalityIncrease;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getModBuildingCost() {
        return modBuildingCost;
    }

    public int getProfitFromProduction() {
        return profitFromProduction;
    }

    public int getPlusMoney() {
        return plusMoney;
    }

    public void setPlusMoney(int plusMoney) {
        this.plusMoney = plusMoney;
    }

    public int getProfitFromRegion() {
        return profitFromRegion;
    }

    public int getProfitFromMineral() {
        return profitFromMineral;
    }

    public int getProfitFromCity() {
        return profitFromCity;
    }
}