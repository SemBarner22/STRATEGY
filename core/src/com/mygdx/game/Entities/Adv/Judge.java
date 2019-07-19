package com.mygdx.game.Entities.Adv;

import com.mygdx.game.Entities.Adv.Advisor;

public class Judge extends Advisor  {
    private int baseNumberOfAdvisorChar = 1;

    public Judge() {
        name = "Ildar Zagretdinov";
        ability = (int) (Math.random() * baseNumberOfAdvisorChar);

    }
}

