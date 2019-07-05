package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Strategy;

public class PlayScreen implements Screen {

    private Strategy strategy;
    public Texture texture;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    public PlayScreen(Strategy strategy) {
        this.strategy = strategy;
        texture = new Texture("badlogic.jpg");
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Strategy.V_WIDTH, Strategy.V_HEIGHT, gameCam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        strategy.batch.setProjectionMatrix(gameCam.combined);
        strategy.batch.begin();
        strategy.batch.draw(texture, 0, 0);
        strategy.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
        texture.dispose();
    }
}
