package com.mygdx.game.Entities.Adv;

import com.mygdx.game.Entities.Adv.Advisor;

public class Financier extends Advisor {
    private int baseNumberOfAdvisorChar = 1;

    public Financier() {
        name = "Ildar Zagretdinov";
        ability = (int) (Math.random() * baseNumberOfAdvisorChar);
        if (ability == 0){
            modBuildingCost = 90;
        }

    }
}
