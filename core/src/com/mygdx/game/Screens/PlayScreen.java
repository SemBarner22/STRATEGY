package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.*;
import com.mygdx.game.Entities.Player;
import com.mygdx.game.Strategy;

public class PlayScreen implements Screen {

    private Player player;
    private TiledMap map;
    private OrthogonalTiledMapRendererWithSprites renderer;
    private Batch batch;
    private ShapeRenderer sr;
    private Strategy strategy;
    public Texture texture;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    public int yy;

    public PlayScreen(Strategy strategy) {
        this.strategy = strategy;
        texture = new Texture("badlogic.jpg");
        gameCam = new OrthographicCamera();
        gamePort = new FillViewport(Strategy.V_WIDTH, Strategy.V_HEIGHT, gameCam);
    }

    @Override
    public void show() {
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("res\\map\\grass_tileset_map1.tmx");
        renderer = new OrthogonalTiledMapRendererWithSprites(map);
        renderer.setView(gameCam);
        Gdx.input.setInputProcessor(player);
        sr = new ShapeRenderer();
        sr.setColor(Color.CYAN);
        Gdx.gl.glLineWidth(3);
        MapObject object = map.getLayers().get("RegionsNew").getObjects().get("Player");
        Polygon poly = ((PolygonMapObject) object).getPolygon();
        //player = new Player((int) poly.getX(), (int)poly.getY());
        player = new Player(200, -10);
        //layer = (TiledMapTileLayer) map.getLayers().get("Regions");
        Gdx.input.setInputProcessor(player);
        gameCam.translate(300, 300);
        //ImageButton imageButton = new ImageButton((Drawable) new Texture("badlogic.jpg"));
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
