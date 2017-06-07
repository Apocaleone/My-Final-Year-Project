package com.themetanoia.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Tools.AudioManager;

/**
 * Created by MITHOON on 03-04-2017.
 */
public class GameOver extends Game implements Screen {
    private Stage stage;
    private Viewport viewport;
    private BitmapFont font,font1;
    private TextButton.TextButtonStyle startbuttonStyle;
    public TextButton startbutton;
    public Image image;
    private Skin skin;
    private TextureAtlas atlas;

    private Lone_Warrior1 game;
    private Play_State state;
    private Texture texture;
    Label highScore,currentscore;
    Label highScoreString,currentscorestring;
    int score=0,level,act,beatscore;
    private FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    public AudioManager audio;


    public GameOver(Lone_Warrior1 game,Play_State state,int score,int level, int act,int beatscore) {
        this.game = game;
        this.state=state;
        this.score=score;
        this.level=level;
        this.act=act;
        this.beatscore=beatscore;
        viewport=new StretchViewport(Lone_Warrior1.V_Width,Lone_Warrior1.V_Height,new OrthographicCamera());
        texture=new Texture("gameover.png");
        image=new Image(texture);
        audio=new AudioManager(game);

        atlas=new TextureAtlas();
        atlas=game.getAtlas(0);
        stage=new Stage(viewport,game.batch);
        generator= new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Variane Script.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=50;
        font=new BitmapFont();
        font1=new BitmapFont();
        font=generator.generateFont(parameter);
        parameter.size=50;
        font1=generator.generateFont(parameter);
        generator.dispose();
        skin=new Skin();
        skin.addRegions(atlas);

        Table table=new Table();
        table.center();
        table.setFillParent(true);

        Gdx.input.setInputProcessor(stage);

        currentscorestring=new Label("Current Score",new Label.LabelStyle(font1, Color.WHITE));
        currentscore=new Label(String.format("%03d",score),new Label.LabelStyle(font1,Color.WHITE));

        if(score>game.prefs.getInteger("HighScore"))
            game.prefs.putInteger("HighScore",score);
        else{
            score=game.prefs.getInteger("HighScore");
        }

        highScoreString=new Label("High Score",new Label.LabelStyle(font1, Color.WHITE));
        highScore=new Label(String.format("%03d",score),new Label.LabelStyle(font1,Color.WHITE));


        startbuttonStyle=new TextButton.TextButtonStyle();            //button 1 properties
        startbuttonStyle.up= skin.getDrawable("startbutton");
        startbuttonStyle.down= skin.getDrawable("startbuttondown");
        startbuttonStyle.font=font;
        startbutton= new TextButton("Menu Screen ",startbuttonStyle);
       table.add(image).expandX().center().padLeft(300);
        table.row();
        table.add(highScoreString).padTop(40).left().padLeft(100);
        table.add(currentscorestring).padTop(40).right().padRight(100);
        table.row();
        table.add(highScore).padTop(5).left().padLeft(100);
        table.add(currentscore).padTop(5).right().padRight(100);
        table.row();
        table.add(startbutton).expandX().padTop(100).width(400).height(100).center().padLeft(300);
        stage.addActor(table);
        if(game.getPrefs().getInteger("score"+this.level+this.act)>=beatscore){
            changer();
            game.getPrefs().putBoolean("unlock"+this.level+(++this.act),true);}
        game.prefs.flush();
    }

    public void changer(){
        switch (level){
            case 1:if(act==3){
                level += 1;
                 act *= 0;}
                  break;
            case 2:if(act==4){
                level += 1;
                act *= 0;}
                break;
            case 3:if(act==6){
                level += 1;
                act *= 0;}
                break;
        }


    }

    public void init(){

    }

    @Override
    public void create() {
    }

    @Override
    public void show() {
        startbutton.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                audio.playbSound(2);
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                System.out.println("Start Clicked");
                game.setScreen(new MenuScreen(game));
                dispose();
            }
        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

    }

    @Override
    public void hide() {

    }
    public void dispose(){
        stage.dispose();
        texture.dispose();
    }
}
