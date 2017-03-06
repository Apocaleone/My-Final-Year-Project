package com.themetanoia.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.themetanoia.game.Characters.Berserker;
import com.themetanoia.game.Characters.Enemies;
import com.themetanoia.game.Characters.Spearman;
import com.themetanoia.game.Characters.Warrior;
import com.themetanoia.game.Screen_Elements.Buttons;
import com.themetanoia.game.Screen_Elements.Hud;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Tools.MyContactListener;
import com.themetanoia.game.Tools.Spawner;
import com.themetanoia.game.Worlds.NightWorld;

/**
 * Created by MITHUN on 02-10-2016.
 */
public class Play_State implements Screen {

    private Lone_Warrior1 game;
    private Hud hud;//for heads up display
    private TextureAtlas atlas;

    private OrthographicCamera gamecam;//for the camera that follows the warrior
    private OrthographicCamera bgcam;//for the camera that follows the background, which is mostly stationary
    private Viewport gamePort,bgPort;//to determine the viewports that make the view uniform across all devices

      public static World world;//to create a box2d world
    private Box2DDebugRenderer b2dr;//to render the debug lines for box2d

    public ShapeRenderer sR;//to render the shapes
    private Spawner spawn;
    Warrior warrior;//object for the warrior class
    //Berserker berserker;
    //Spearman spearman;
    Buttons buttons;

    private float time=0;
    int j=0;
    public static Array<Body> bodiesToRemove;//an array that takes care of removing the bodies in the array after each update
    public static boolean ongoingmove=false, retreat=false,flag=false,flag2=false;//variables that determine the state of warrior






    public Play_State(Lone_Warrior1 game){
        atlas=new TextureAtlas("Warrior.pack");
        this.game=game;


        sR=new ShapeRenderer();

        //camera initialization
        gamecam=new OrthographicCamera();
        bgcam=new OrthographicCamera();
        gamePort=new StretchViewport(Lone_Warrior1.V_Width/Lone_Warrior1.PPM,Lone_Warrior1.V_Height/Lone_Warrior1.PPM,gamecam);//making the view constant for all devices
        bgPort=new StretchViewport(Lone_Warrior1.V_Width/Lone_Warrior1.PPM,Lone_Warrior1.V_Height/Lone_Warrior1.PPM,bgcam);
        //screen elements initialization
        hud=new Hud(game.batch);
        buttons=new Buttons(game.batch);
        //camera position setting
        gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);
        bgcam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);
        //world initialization
        world=new World(new Vector2(0,-9.81f),true);
        world.setContactListener(new MyContactListener());
        b2dr=new Box2DDebugRenderer();
        new NightWorld(world);
        //character initialization
        warrior=new Warrior(world,this);
        spawn=new Spawner(this);
        //berserker =new Berserker(this,(Lone_Warrior1.V_Width/Lone_Warrior1.PPM)*3f,100/Lone_Warrior1.PPM );
        //spearman=new Spearman(this);
        //character body creation
        //spearman.defineEnemy();

        bodiesToRemove=new Array<Body>();
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }




    @Override
    public void show() {buttons.create();}


    public void update(float dt){
        world.step(1/45f,6,2);
        updatingCharacterClasses(dt);//calls update method of individual character classes
        checkForMoves();//checks the state of each body
        bodyDestructor();//destroys the bodies from the list
        retreatFunction();//retreats the warrior to its attack initation position
        NightWorld.renderer.setView(gamecam);
        NightWorld.renderer2.setView(bgcam);
        forceApplicationFunction();//applies force to each individual character in the game
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

        drawCharacters();

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
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        NightWorld.bg.dispose();
        NightWorld.renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
        buttons.dispose();}

    public void drawCharacters(){
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        warrior.draw(game.batch);
        for(Enemies enemy: spawn.getEnemies()) {
            if(enemy.check())
                enemy.draw(game.batch);

        }
        //if(berserker.berserkerstate!=-1)
           // berserker.draw(game.batch);
        //if(spearman.spearmanstate!=-1)
            //spearman.draw(game.batch);
        game.batch.end();

    }

    public void updatingCharacterClasses(float dt){//update method for individual character classes
        warrior.update(dt);
        for(Enemies enemy: spawn.getEnemies())
            enemy.update(dt);
        //berserker.update(dt);
        //spearman.update(dt);
    }

    public void checkForMoves(){
        if(warrior.posture==1&&ongoingmove==true){
            if(warrior.highkickattack.getLinearVelocity().x==0) {//checks for the end of an attack, removes the attacking body
                bodiesToRemove.add(warrior.highkickattack);
                retreat=true;
                flag=true;
            }
        }

        if(warrior.posture==2&&ongoingmove==true){
            if(warrior.lowkickattack.getLinearVelocity().x==0) {//checks for the end of an attack, removes the attacking body
                bodiesToRemove.add(warrior.lowkickattack);
                retreat=true;
                flag=true;
            }
        }

    }

    public void bodyDestructor(){
        for(int i=0;i<bodiesToRemove.size;i++)//list that destroys the body
        {
            if(retreat==false) { //save the state of warrior running state position after body being removed.
                Lone_Warrior1.x = warrior.hero.getPosition().x;
                Lone_Warrior1.y = warrior.hero.getPosition().y;}
            if(retreat==true&&warrior.posture==1)//only checks for the position of the body if warrior is in attack position
            {Lone_Warrior1.x1=warrior.highkickattack.getPosition().x;
                Lone_Warrior1.y1=warrior.highkickattack.getPosition().y;}
            if(retreat==true&&warrior.posture==2)//only checks for the position of the body if warrior is in attack position
            {Lone_Warrior1.x1=warrior.lowkickattack.getPosition().x;
                Lone_Warrior1.y1=warrior.lowkickattack.getPosition().y;}
            Body b=bodiesToRemove.get(i);
            world.destroyBody(b);
        }
        bodiesToRemove.clear();

    }

    public void retreatFunction(){
        if(retreat==true){//defines the retreating body right after an attack. Phew!
            if(flag==true){//defines the retreating body of the hero just once.
                warrior.defineHeroRetreating();
                flag=false;}
            warrior.heroretreating.applyForceToCenter(-6,0,true);
            if (warrior.heroretreating.getPosition().x < Lone_Warrior1.x) {
                bodiesToRemove.add(warrior.heroretreating);
                ongoingmove=false;
                warrior.posture=0;
                warrior.defineWarrior();
                retreat=false;
            }
        }
    }

    public void forceApplicationFunction(){
        warrior.hero.applyForceToCenter(2.2f,0,true);
        //if(spearman.spearmanstate==0)
           // spearman.spearman1.applyForceToCenter(-2.3f,0,true);

    }


}
