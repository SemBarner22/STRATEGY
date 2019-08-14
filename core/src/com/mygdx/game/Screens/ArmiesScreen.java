package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Strategy;

public class ArmiesScreen extends AbstractMechanicsScreen {


    public ArmiesScreen(Strategy strategy, MechanicsMenu mechanicsMenu) {
        super(strategy, mechanicsMenu);
    }

    @Override
    public void show() {
        super.show();
        Label mobAmount = new Label("" + 1, skin);
        Label regAmount = new Label("" + 1, skin);
        Button mobilisation = new TextButton("Mobilisation", skin);
        Label getModTactic = new Label("" + PlayScreen.world.getPlayerGov().armyMod()[1], skin);
        Label getModShock = new Label("" + 1, skin);
        Label getModFire = new Label("" + 1, skin);
        Label getModMorale = new Label("" + PlayScreen.world.getPlayerGov().armyMod()[0], skin);
        Label getModOrganisation = new Label("" + PlayScreen.world.getPlayerGov().armyMod()[2], skin);
        Label getEquipment = new Label("" + 1, skin);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.add(mobAmount).top().center();
        table.row();
        table.add(regAmount);
        table.row();
        table.add(mobilisation);
        table.row();
        table.add(getModTactic);
        table.row();
        table.add(getModShock);
        table.row();
        table.add(getModFire);
        table.row();
        table.add(getModMorale);
        table.row();
        table.add(getModOrganisation);
        table.row();
        table.add(getEquipment);
        table.row();
        container.add(backButton).bottom().left().expandX();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
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
        super.dispose();
    }
}
