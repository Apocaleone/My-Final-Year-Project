package com.themetanoia.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.themetanoia.game.Characters.Warrior;
import com.themetanoia.game.Load_Screens.LoadState;
import com.themetanoia.game.Screen_Elements.Buttons;
import com.themetanoia.game.Screen_Elements.Hud;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Worlds.NightWorld;

/**
 * Created by MITHUN on 02-10-2016.
 */
public class Play_State implements Screen {

    private Lone_Warrior1 game;
    private Hud hud;
    private TextureAtlas atlas;

    private OrthographicCamera gamecam;
    private OrthographicCamera bgcam;
    private Viewport gamePort,bgPort;

      private World world;
    private Box2DDebugRenderer b2dr;

    public ShapeRenderer sR;
    Warrior warrior;
    Buttons buttons;

    private float time=0;






    public Play_State(Lone_Warrior1 game){
        atlas=new TextureAtlas("Warrior.pack");
        this.game=game;


        sR=new ShapeRenderer();


        gamecam=new OrthographicCamera();
        bgcam=new OrthographicCamera();
        gamePort=new StretchViewport(Lone_Warrior1.V_Width/Lone_Warrior1.PPM,Lone_Warrior1.V_Height/Lone_Warrior1.PPM,gamecam);
        bgPort=new StretchViewport(Lone_Warrior1.V_Width/Lone_Warrior1.PPM,Lone_Warrior1.V_Height/Lone_Warrior1.PPM,bgcam);

        hud=new Hud(game.batch);
        buttons=new Buttons();

        gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);
        bgcam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);

        world=new World(new Vector2(0,-9.81f),true);
        b2dr=new Box2DDebugRenderer();
        new NightWorld(world);

        warrior=new Warrior(world,this);
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {

        buttons.button1.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                System.out.println("Star Clicked");
                warrior.hero.applyForceToCenter(-100,300,true);
                return true;
            }
        });
        buttons.button2.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                System.out.println("Clicked");
                warrior.hero.applyForceToCenter(100,300,true);
                return true;
            }
        });
        buttons.button3.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                System.out.println("button3 Clicked");
                warrior.hero.applyForceToCenter(-100,0,true);
                return true;
            }
        });
        buttons.button4.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                System.out.println("button4 Clicked");
                warrior.hero.applyForceToCenter(100,0,true);
                return true;
            }
        });


    }
    public void update(float dt){

        world.step(1/45f,6,2);
        warrior.update(dt);
        NightWorld.renderer.setView(gamecam);
        NightWorld.renderer2.setView(bgcam);
        warrior.hero.applyForceToCenter(2,0,true);

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        time+=delta;

        gamecam.position.set(warrior.hero.getPosition().x+gamePort.getWorldWidth()/3,gamePort.getWorldHeight()/2,0);
        gamecam.update();

        NightWorld.renderer2.render();
        NightWorld.renderer.render();


        b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        warrior.draw(game.batch);
        game.batch.end();




        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        hud.stage.draw();
        buttons.stage.act();
        buttons.stage.draw();



    }

    @Override
    public void resize(int width, int height) {
        bgPort.update(width, height);
        gamePort.update(width,height);


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
        NightWorld.bg.dispose();
        NightWorld.renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
        buttons.dispose();

    }
}
