package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Strategy;

import java.awt.*;

public class Hud {
    public Stage stage;
    private Viewport viewport;
    private Integer worldTimer;
    Label worldLabel;

    public Hud(SpriteBatch sb){
        worldTimer = 300;
        viewport = new FitViewport(Strategy.V_WIDTH, Strategy.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        worldLabel = new Label(String.format("%03d", worldTimer), Label.LabelStyle(new BitmapFont(), Color.WHITE));
    }
}
