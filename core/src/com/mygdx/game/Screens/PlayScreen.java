package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.*;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.Entities.World;
import com.mygdx.game.Strategy;

public class PlayScreen implements Screen {

    public boolean isMoveEnded;
    public World world;
    private Player player;
    private TiledMap map;
    private OrthogonalTiledMapRendererWithSprites renderer;
    private Batch batch;
    private ShapeRenderer sr;
    private Strategy strategy;
    private Stage stage;
    private Table table;
    public Texture texture;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    public PlayScreen(Strategy strategy) {
        this.strategy = strategy;
        texture = new Texture("badlogic.jpg");
        gameCam = new OrthographicCamera();
        gamePort = new FillViewport(Strategy.V_WIDTH, Strategy.V_HEIGHT, gameCam);
        stage = new Stage();
        world = new World();
    }

    @Override
    public void show() {
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("res\\map\\grass_tileset_map1.tmx");
        renderer = new OrthogonalTiledMapRendererWithSprites(map);
        renderer.setView(gameCam);
        sr = new ShapeRenderer();
        sr.setColor(Color.CYAN);
        Gdx.gl.glLineWidth(3);
        MapObject object = map.getLayers().get("RegionsNew").getObjects().get("Player");
        Polygon poly = ((PolygonMapObject) object).getPolygon();
        player = new Player(200, -10);
        InputMultiplexer im = new InputMultiplexer(stage, player);
        Gdx.input.setInputProcessor(im);
        gameCam.translate(300, 300);
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        table = new Table();
        stage.addActor(table);
        table.setFillParent(true);
        table.top().pad(10).defaults().expandX().space(10);
        //table.pad(10).defaults().expandX().space(10);
        for (int i = 0; i < 2; i++) {
            table.row();
            for (int j = 0; j < 5; j++) {
                Label label = new Label(j + i + "dos", skin);
                table.add(label);
            }
        }
        Table bottomTable = new Table();
        stage.addActor(bottomTable);
        bottomTable.setFillParent(true);
        bottomTable.bottom().pad(10).defaults().expandX().space(100);
            TextButton mechanicsButton = new TextButton("Mechanics menu", skin);
            bottomTable.add(mechanicsButton);
            mechanicsButton.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    strategy.setScreen(new MechanicsMenu(strategy, PlayScreen.this));
                }
            });
        TextButton moveEndButton = new TextButton("End of the move", skin);
        bottomTable.add(moveEndButton);
        moveEndButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(gameCam);
        batch = strategy.batch;
        strategy.batch.setProjectionMatrix(gameCam.combined);
        sr.setProjectionMatrix(gameCam.combined);
        sr.setColor(Color.CYAN);
        strategy.batch.begin();
        renderer.render(new int[]{0, 1, 2, 3, 4});
        strategy.batch.end();

        MapObject playObject = map.getLayers().get("RegionsNew").getObjects().get("Player");
        Polygon regPlayer = ((PolygonMapObject) playObject).getPolygon();
        regPlayer.setPosition(player.getX(), player.getY());
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.polygon(regPlayer.getTransformedVertices());
        sr.end();

        MapObject provObject = map.getLayers().get("Provincions").getObjects().get("Player");
        Polygon provPlayer = ((PolygonMapObject) provObject).getPolygon();
        provPlayer.setPosition(player.getX(), player.getY());
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.polygon(provPlayer.getTransformedVertices());
        sr.end();

        for (MapObject object : map.getLayers().get("RegionsNew").getObjects()) {
            if (object instanceof PolygonMapObject && object.getProperties().containsKey("RegIndex")) {
                Polygon polygonMapObject = ((PolygonMapObject) object).getPolygon();
                sr.begin(ShapeRenderer.ShapeType.Line);
                sr.polygon(polygonMapObject.getTransformedVertices());
                sr.end();

                if (Intersector.overlapConvexPolygons(polygonMapObject, regPlayer)) {
                    strategy.batch.begin();
                    renderer.render(new int[]{map.getLayers().getIndex("Region" +
                            object.getProperties().get("RegIndex", Integer.class))});
                    strategy.batch.end();
                }
            }
        }

        for (MapObject object : map.getLayers().get("Provincions").getObjects()) {
            if (object instanceof PolygonMapObject && object.getProperties().containsKey("RegIndex")) {
                Polygon polygonMapObject = ((PolygonMapObject) object).getPolygon();
                //sr.begin(ShapeRenderer.ShapeType.Line);
                //sr.polygon(polygonMapObject.getTransformedVertices());
                //sr.end();

                if (Intersector.overlapConvexPolygons(polygonMapObject, provPlayer)) {
                    strategy.batch.begin();
                    renderer.render(new int[]{map.getLayers().getIndex("ProvReg" +
                            object.getProperties().get("RegIndex", Integer.class))});
                    strategy.batch.end();
                }
            }
        }
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameCam.viewportWidth = width;
        gameCam.viewportHeight = height;
        gameCam.update();
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
        map.dispose();
        batch.dispose();
    }
}
