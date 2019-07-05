package com.mygdx.game.Scenes;

public class Gov {
    private int money;
    private int profitFromProduction;
    private int profitFromRegions;
    private int profitFromResourses;
    private int profitFromCities;
    private Region[] regions;
    public Gov(Region[] regions) {
        this.regions = regions;
    }
    public void updateProfit(){

    }
    public void updateProfitFromRegions(){
        profitFromRegions =
    }
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    
}
