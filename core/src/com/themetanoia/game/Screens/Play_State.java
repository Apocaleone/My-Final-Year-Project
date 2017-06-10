package com.themetanoia.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.FloatCounter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.PerformanceCounter;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.themetanoia.game.Characters.Enemies;
import com.themetanoia.game.Characters.Warrior;
import com.themetanoia.game.Screen_Elements.*;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Tools.AudioManager;
import com.themetanoia.game.Tools.BloodSplatter;
import com.themetanoia.game.Tools.MyContactListener;
import com.themetanoia.game.Tools.Spawner;
import com.themetanoia.game.Worlds.NightWorld;

/**
 * Created by MITHUN on 02-10-2016.
 */
public class Play_State implements Screen {

    public Lone_Warrior1 game;
    public Hud hud;//for heads up display
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
    public float velocity=-0;

    public int level=0, act =0,beatscore=0;

    public static Array<Body> bodiesToRemove;//an array that takes care of removing the bodies in the array after each update
    public boolean ongoingmove=false, retreat=false,flag=false,flag2=false;//variables that determine the state of warrior
    public static boolean halt =false;
    public static int enemycounter=0;
    public float stepchange=1/45f;
    public com.themetanoia.game.Screen_Elements.PauseScreen pauseScreen;

    public AudioManager audio;

    public PerformanceCounter performanceCounter;
    public FloatCounter testing;

    NightWorld night;

    BloodSplatter blood;
    boolean check=false;





    public Play_State(Lone_Warrior1 game,float velocity,int level,int act,int beatscore){
        this.game=game;
        this.velocity=velocity;
        this.level=level;
        this.act =act;
        this.beatscore=beatscore;
        pauseScreen=new com.themetanoia.game.Screen_Elements.PauseScreen(this);
        audio=new AudioManager(game);
        blood=new BloodSplatter();


        sR=new ShapeRenderer();
        //camera initialization
        gamecam=new OrthographicCamera();
        bgcam=new OrthographicCamera();
        gamePort=new StretchViewport(Lone_Warrior1.V_Width/Lone_Warrior1.PPM,Lone_Warrior1.V_Height/Lone_Warrior1.PPM,gamecam);//making the view constant for all devices
        bgPort=new StretchViewport(Lone_Warrior1.V_Width/Lone_Warrior1.PPM,Lone_Warrior1.V_Height/Lone_Warrior1.PPM,bgcam);
        //screen elements initialization
        hud=new Hud(game.batch);
        buttons=new Buttons(game.batch,this);
        //camera position setting
        gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);
        bgcam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);
        //world initialization
        world=new World(new Vector2(0,-9.81f),true);
        world.setContactListener(new MyContactListener());
        b2dr=new Box2DDebugRenderer();
        night=new NightWorld(world,this);
        //character initialization
        warrior=new Warrior(world,this);
        spawn=new Spawner(this);
        //berserker =new Berserker(this,(Lone_Warrior1.V_Width/Lone_Warrior1.PPM)*3f,100/Lone_Warrior1.PPM );
        //spearman=new Spearman(this);
        //character body creation
        //spearman.defineEnemy();

        spawn.spawn();
        blood.splatter();
       audio.playMusic(0);
        audio.playMusic(2);
        audio.music.get(2).setVolume(0.2f);


        bodiesToRemove=new Array<Body>();
    }
    public float getVelocity() {
        return velocity;
    }





    @Override
    public void show() {buttons.create();
    pauseScreen.create();}
    public boolean pausescreen(){
        if(halt==true)
            return true;
        else
            return false;
    }


    public void update(float dt){
        world.step(stepchange,6,2);
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            halt=true;
        }
        hud.update(dt);
        counters();
        updatingCharacterClasses(dt);//calls update method of individual character classes
        checkForMoves();//checks the state of each body
        bodyDestructor();//destroys the bodies from the list
        retreatFunction();//retreats the warrior to its attack initation position!
        night.renderer.setView(gamecam);
        night.renderer2.setView(bgcam);
        if(enemycounter>=10){
            spawn.spawn();
            System.out.println("spawning enemies");

        }
            forceApplicationFunction();//applies force to each individual character in the game

    }

    @Override
    public void render(float delta) {
        if(halt)
        {
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                halt=false;
            }
        }
        else{
            time+=delta;
        update(delta);}                      //purple     //Don't know
        Gdx.gl.glClearColor(1,0.95f,0.95f,1);//(1,0,1,0);//(0.329412f, 0.329412f, 0.329412f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(warrior.posture==-1)
        gamecam.position.set(warrior.herodefeated.getPosition().x+gamePort.getWorldWidth()/3,gamePort.getWorldHeight()/2,0);
        else
            gamecam.position.set(Lone_Warrior1.x+gamePort.getWorldWidth()/3,gamePort.getWorldHeight()/2,0);


       night.renderer2.render();
        night.renderer.render();

        gamecam.update();


        //b2dr.render(world, gamecam.combined);

        drawCharacters();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        hud.stage.draw();
        buttons.stage.act();
        buttons.stage.draw();

        if(pausescreen()){
            //pauseScreen.sprite.setPosition(Gdx.graphics.getWidth()/2-pauseScreen.texture.getWidth()/2,Gdx.graphics.getHeight()/2-pauseScreen.texture.getHeight()/2);
            pauseScreen.stage.act();
            pauseScreen.stage.draw();

            buttons.resumestage.act();
            buttons.resumestage.draw();
        }

        if(isGameover()||hud.worldTimer==0){
            if(hud.worldTimer==0)
                check=true;
            audio.stopAll();
            bodiesToRemove.add(warrior.herodefeated);
            Lone_Warrior1.x=100/Lone_Warrior1.PPM;
            Lone_Warrior1.y=50/Lone_Warrior1.PPM;
            warrior.posture=0;
            game.getPrefs().putInteger("score"+level+act,hud.score);
            game.setScreen(new GameOver(game,this,hud.score,level,act,beatscore,check));
            dispose();
        }
        /*if(time>3.1){
            halt=true;
            *//*game.setScreen(new GameOver(game));
            dispose();
            Lone_Warrior1.x=100/Lone_Warrior1.PPM;
            Lone_Warrior1.y=50/Lone_Warrior1.PPM;
            warrior.posture=0;*//*
        }*/
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
        night.bg.dispose();
        night.renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
        buttons.dispose();
    }

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
        if(warrior.posture==3&&ongoingmove==true){
            if(warrior.multikickattack.getLinearVelocity().x==0) {//checks for the end of an attack, removes the attacking body
                bodiesToRemove.add(warrior.multikickattack);
                retreat=true;
                flag=true;
            }
        }
        if(warrior.posture==4&&ongoingmove==true){
            if(warrior.groundpunchattack.getLinearVelocity().x==0) {//checks for the end of an attack, removes the attacking body
                bodiesToRemove.add(warrior.groundpunchattack);
                retreat=true;
                flag=true;
            }
        }
        if(warrior.posture==5&&ongoingmove==true){
            if(warrior.spinpunchattack.getLinearVelocity().x==0) {//checks for the end of an attack, removes the attacking body
                bodiesToRemove.add(warrior.spinpunchattack);
                retreat=true;
                flag=true;
            }
        }
        if(warrior.posture==6&&ongoingmove==true){
            if(warrior.megapunchattack.getLinearVelocity().x==0) {//checks for the end of an attack, removes the attacking body
                bodiesToRemove.add(warrior.megapunchattack);
                retreat=true;
                flag=true;
            }
        }
        if(warrior.posture==7&&ongoingmove==true){
            if(warrior.hurricanebreathattack.getLinearVelocity().x==0) {//checks for the end of an attack, removes the attacking body
                bodiesToRemove.add(warrior.hurricanebreathattack);
                retreat=true;
                flag=true;
            }
        }
        if(warrior.posture==8&&ongoingmove==true){
            if(warrior.exorcizeattack.getLinearVelocity().x==0) {//checks for the end of an attack, removes the attacking body
                bodiesToRemove.add(warrior.exorcizeattack);
                retreat=true;
                flag=true;
            }
        }


    }

    public void bodyDestructor(){
        for(int i=0;i<bodiesToRemove.size;i++)//list that destroys the body
        {
           /* if(retreat==false) { //save the state of warrior running state position after body being removed.
                Lone_Warrior1.x = warrior.hero.getPosition().x;
                Lone_Warrior1.y = warrior.hero.getPosition().y;}*/
            if(retreat==true&&warrior.posture==1)//only checks for the position of the body if warrior is in attack position
            {Lone_Warrior1.x1=warrior.highkickattack.getPosition().x;
                Lone_Warrior1.y1=warrior.highkickattack.getPosition().y;}
            if(retreat==true&&warrior.posture==2)//only checks for the position of the body if warrior is in attack position
            {Lone_Warrior1.x1=warrior.lowkickattack.getPosition().x;
                Lone_Warrior1.y1=warrior.lowkickattack.getPosition().y;}
            if(retreat==true&&warrior.posture==3)//only checks for the position of the body if warrior is in attack position
            {Lone_Warrior1.x1=warrior.multikickattack.getPosition().x;
                Lone_Warrior1.y1=warrior.multikickattack.getPosition().y;}
            if(retreat==true&&warrior.posture==4)//only checks for the position of the body if warrior is in attack position
            {Lone_Warrior1.x1=warrior.groundpunchattack.getPosition().x;
                Lone_Warrior1.y1=warrior.groundpunchattack.getPosition().y;}
            if(retreat==true&&warrior.posture==5)//only checks for the position of the body if warrior is in attack position
            {Lone_Warrior1.x1=warrior.spinpunchattack.getPosition().x;
                Lone_Warrior1.y1=warrior.spinpunchattack.getPosition().y;}
            if(retreat==true&&warrior.posture==6)//only checks for the position of the body if warrior is in attack position
            {Lone_Warrior1.x1=warrior.megapunchattack.getPosition().x;
                Lone_Warrior1.y1=warrior.megapunchattack.getPosition().y;}
            if(retreat==true&&warrior.posture==7)//only checks for the position of the body if warrior is in attack position
            {Lone_Warrior1.x1=warrior.hurricanebreathattack.getPosition().x;
                Lone_Warrior1.y1=warrior.hurricanebreathattack.getPosition().y;}
            if(retreat==true&&warrior.posture==8)//only checks for the position of the body if warrior is in attack position
            {Lone_Warrior1.x1=warrior.exorcizeattack.getPosition().x;
                Lone_Warrior1.y1=warrior.exorcizeattack.getPosition().y;}
            Body b=bodiesToRemove.get(i);
            System.out.println("destroying body");
            world.destroyBody(b);
        }
        bodiesToRemove.clear();

    }

    public void retreatFunction(){
        if(retreat==true){//defines the retreating body right after an attack. Phew!
            if(flag==true){//defines the retreating body of the hero just once.
                warrior.defineHeroRetreating();
                flag=false;}
            warrior.heroretreating.setLinearVelocity(velocity-1.5f,0);
            if (warrior.heroretreating.getPosition().x < Lone_Warrior1.x&&warrior.posture!=-1) {
                bodiesToRemove.add(warrior.heroretreating);
                ongoingmove=false;
                warrior.posture=0;
                warrior.defineWarrior();
                retreat=false;
            }
        }
    }

    public void forceApplicationFunction(){
        /*if(warrior.posture!=-1)
        warrior.hero.applyForceToCenter(2.2f,0,true);*/
        //if(spearman.spearmanstate==0)
           // spearman.spearman1.applyForceToCenter(-2.3f,0,true);

    }

    public void counters(){
        if(retreat==true){
            Lone_Warrior1.x1=warrior.heroretreating.getPosition().x;
            Lone_Warrior1.y1=warrior.heroretreating.getPosition().y;
        }
        if(warrior.posture==-1){
            if(flag2==false){
            warrior.defineHeroDefeated();
                warrior.herodefeated.applyForceToCenter(-300,0,true);
            flag2=true;}
        }
    }

    public boolean isGameover(){
        if(warrior.posture==-1&&warrior.getTime()>3){
            return true;
        }
        else
            return false;
    }
public boolean flagchecker(int i){
    switch (i){
        case 1: if(ongoingmove)
                    return true;
            else
            return false;
        case 2: if(retreat)
            return true;
        else
            return false;
        default:
            return false;
    }
}
    public void switchstate(){
        ongoingmove=true;
    }


}
