package com.mygdx.game.Entities.Adv;

import com.mygdx.game.Entities.Adv.Advisor;

public class Scientist extends Advisor  {
    private int baseNumberOfAdvisorChar = 1;

    public Scientist() {
        name = "Ildar Zagretdinov";
        ability = (int) (Math.random() * baseNumberOfAdvisorChar);

    }
}

