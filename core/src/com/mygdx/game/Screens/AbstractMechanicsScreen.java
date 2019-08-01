package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Strategy;

public class AbstractMechanicsScreen implements Screen {
    Strategy strategy;
    protected MechanicsMenu mechanicsMenu;
    protected Stage stage;
    protected Table container;
    protected Skin skin;

    public AbstractMechanicsScreen(final Strategy strategy, final MechanicsMenu mechanicsMenu) {
        this.strategy = strategy;
        this.mechanicsMenu = mechanicsMenu;
    }

    @Override
    public void show() {
        stage = new Stage();
        container = new Table();
        stage.addActor(container);
        Gdx.input.setInputProcessor(stage);
        container.setFillParent(true);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        Button backButton = new TextButton("back", skin);
        container.add(backButton).bottom().left().expandX();
        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                strategy.setScreen(mechanicsMenu);
            }
        });
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
