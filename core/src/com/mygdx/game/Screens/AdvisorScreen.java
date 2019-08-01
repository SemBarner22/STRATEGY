package com.mygdx.game.Screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Strategy;

public class AdvisorScreen extends AbstractMechanicsScreen {

    public AdvisorScreen(Strategy strategy, MechanicsMenu mechanicsMenu) {
        super(strategy, mechanicsMenu);
    }

    @Override
    public void show() {
        super.show();
        Table table = new Table();
        final ScrollPane scroll = new ScrollPane(table, skin);
        table.pad(10).defaults().expandX().space(4);
        //ArrayList<Label> labels = new ArrayList<Label>();
        for (int i = 0; i < 100; i++) {
            table.row();
            table.add(new Label("name + bonus", skin)).expandX().fillX();
            TextButton button = new TextButton("Change", skin);
            table.add(button);
            button.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {

                }
            });
        }
        container.add(scroll).expand().fill().colspan(4);
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
