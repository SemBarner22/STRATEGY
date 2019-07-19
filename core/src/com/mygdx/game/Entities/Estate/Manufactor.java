package com.mygdx.game.Entities.Estate;

import com.mygdx.game.Entities.Estate.Ability;
import com.mygdx.game.Entities.Estate.Estate;

public class Manufactor extends Estate {
    private int modProfit = 0;
    private int modProfitFromProduction = 0;
    private int numAbility = 2;

    public Manufactor() {
        ability = new Ability[numAbility];
        for (int i = 0; i < numAbility; i++){
            ability[i] = new Ability();
        }
    }
    public void ActivateAbility(int num){
        if (num == 0 && ability[0].getTime() == 0){
            ability[num].Activate(10);
            modProfit = 3;
            power += 100;
        }
        if (num == 1 && ability[1].getTime() == 0){
            ability[num].Activate(15);
            modProfitFromProduction = 10;
            power += 150;
            loyality -= 50;
        }
        if (num == 2 && ability[2].getTime() == 0){
            ability[num].Activate(10);
            loyality -=150;
            plusMoney = partOfPover;
        }
    }
    @Override
    public void UpdateBonus(){
        if (ability[0].getTime() == 7){
            modProfit = 0;
        }
        if (ability[2].getTime() == 5){
            modProfit = 0;
        }

        if (partOfPover >= 500 && loyality >= 300){
            profit = 7;
            profitFromProduction = modProfitFromProduction + 50;
        } else if (partOfPover >= 400 && loyality >= 300){
            profit = 8;
            profitFromProduction = modProfitFromProduction + 40;
        } else if (partOfPover >= 300 && loyality >= 300){
            profit = 9;
            profitFromProduction = modProfitFromProduction + 25;
        } else if (partOfPover >= 100 && loyality >= 300){
            profit = 10;
            profitFromProduction = modProfitFromProduction +15;
        } else if (partOfPover < 100 && loyality >= 300){
            profit = 10;
            profitFromProduction = modProfitFromProduction + 10;
        } else if (loyality < 100){
            profitFromProduction = -20;
            profit = 7;
        } else if (loyality < 200){
            profitFromProduction = -10;
            profit = 9;
        } else {
            profit = 10;
            profitFromProduction = 0;
        }
    }
}
/*
        if (partOfPover >= 500 && loyality >= 300){
        } else if (partOfPover >= 400 && loyality >= 300){
        } else if (partOfPover >= 300 && loyality >= 300){
        } else if (partOfPover >= 100 && loyality >= 300){
        } else if (partOfPover < 100 && loyality >= 300){
        } else if (loyality < 100){
        } else if (loyality < 200){
        } else {
        }
        */