package com.mygdx.game.Entities.Estate;

import com.mygdx.game.Entities.Estate.Estate;

public class Generals extends Estate{
    private int modTactic = 0;
    private int numAbility = 1;

    public Generals() {
        ability = new Ability[numAbility];
        for (int i = 0; i < numAbility; i++){
            ability[i] = new Ability();
        }
    }
    public void ActivateAbility(int num){
        if (num == 0 && ability[0].getTime() == 0) {
            ability[num].Activate(10);
            modTactic = 3;
            power += 100;
        }
    }
    // тут одновляем бонусы от сословия и убираем бонусы от абилок
    @Override
    public void UpdateBonus(){
        if (ability[0].getTime() == 5){
            modTactic = 0;
            power -= 50;
        }

        if (partOfPover >= 500 && loyality >= 300){
            tactic = 3 + modTactic;
            rebelLevel = 3;
            profit = 8;
        } else if (partOfPover >= 400 && loyality >= 300) {
            tactic = 3 + modTactic;
            rebelLevel = -1;
            profit = 9;
        } else if (partOfPover >= 300 && loyality >= 300){
            tactic = 3 + modTactic;
            rebelLevel = - 3;
            profit = 10;
        }  else if (partOfPover >= 100 && loyality >= 300){
            tactic = 2 + modTactic;
            rebelLevel = - 2;
            profit = 10;
        } else if (partOfPover < 100 && loyality >= 300){
            tactic = 1 + modTactic;
            rebelLevel = -1;
            profit = 10;
        } else if (loyality < 100){
            tactic = -5;
            rebelLevel = 10;
            profit = 8;
        } else if (loyality < 200){
            tactic = -2;
            rebelLevel = 5;
            profit = 9;
        } else {
            tactic = 0;
            rebelLevel = 0;
            profit =10;
        }

    }
}
