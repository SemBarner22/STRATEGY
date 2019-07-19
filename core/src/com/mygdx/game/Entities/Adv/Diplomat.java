package com.mygdx.game.Entities.Adv;

import com.mygdx.game.Entities.Adv.Advisor;

public class Diplomat extends Advisor {
    private int baseNumberOfAdvisorChar = 1;

    public Diplomat() {
        name = "Ildar Zagretdinov";
        ability = (int) (Math.random() * baseNumberOfAdvisorChar);

    }
}
