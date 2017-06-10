package com.themetanoia.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Tools.AudioManager;

/**
 * Created by MITHOON on 01-06-2017.
 */
public class InGameTutorials extends Game implements Screen {
    private Stage stage,stage1;
    private Viewport viewport;
    private BitmapFont font,font1;
    private TextButton.TextButtonStyle playbuttonStyle,skipbuttonStyle;
    public TextButton playbutton,skipbutton;
    private Skin skin;
    private TextureAtlas atlas,atlas1,atlas2;
    private FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private String text;


    private Lone_Warrior1 game;
    Label story;
    Label title;
    int level, act,beatscore;
    float speed;
    boolean tut=false;
    AudioManager audioManager;
    private Array<Image> image;

    public InGameTutorials(Lone_Warrior1 game,float speed, int level, int act,int beatscore){
        this.game=game;
        this.level=level;
        this.act=act;
        this.speed=speed;
        this.beatscore=beatscore;
        viewport=new StretchViewport(Lone_Warrior1.V_Width,Lone_Warrior1.V_Height,new OrthographicCamera());

        audioManager=new AudioManager(game);

        atlas1=new TextureAtlas();
        atlas1=game.getAtlas(3);
        atlas2=new TextureAtlas();
        atlas2=game.getAtlas(5);

        stage1=new Stage(viewport,game.batch);


        generator= new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Variane Script.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=50;
        font=new BitmapFont();
        font=generator.generateFont(parameter);
        font1=new BitmapFont();
        parameter.size=100;
        font1=generator.generateFont(parameter);
        generator.dispose();
        skin=new Skin();
        skin.addRegions(atlas2);

        Gdx.input.setInputProcessor(stage1);

        //TUTORIALS
        title=new Label("Objective: Score more than "+ beatscore,new Label.LabelStyle(font1, Color.RED));
        story=new Label("New Enemy Unlocked!",new Label.LabelStyle(font1, Color.RED));
        image=new Array<Image>();
        for(int i=0;i<level+1;i++){
            image.add(new Image(atlas1.findRegion("tutorial"+i)));
        }
        Table scrollTable1=new Table();
        scrollTable1.add(title).center().padBottom(5);
        scrollTable1.row();
        if(level>1&&act==1){
            scrollTable1.add(story).center().padBottom(15);
            scrollTable1.row();
        }
        for(int i=0;i<level+1;i++) {
            scrollTable1.add(image.get(i)).center().padBottom(30);
            scrollTable1.row();
        }
        ScrollPane scrollPane1=new ScrollPane(scrollTable1);

        Table parentTable1=new Table();
        parentTable1.setFillParent(true);
        parentTable1.add(scrollPane1).fill().expand();

        Table screenElements1=new Table();
        screenElements1.right();
        screenElements1.setFillParent(true);

        skipbuttonStyle=new TextButton.TextButtonStyle();            //button 1 properties
        skipbuttonStyle.up= skin.getDrawable("skipup");
        skipbuttonStyle.down= skin.getDrawable("skipdown");
        skipbuttonStyle.font=font;
        skipbutton= new TextButton(" ",skipbuttonStyle);

        screenElements1.add(skipbutton).expandX().padBottom(10).width(100).height(100).right();

        stage1.addActor(parentTable1);
        stage1.addActor(screenElements1);


    }
    @Override
    public void create() {

    }

    @Override
    public void show() {
        skipbutton.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                audioManager.playbSound(0);
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                System.out.println("Start Clicked");
                stage1.dispose();
                game.setScreen(new Play_State(game,speed,level,act,beatscore));//levelsManager.levelSelector();//game.setScreen(new Play_State(game));
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            stage1.act();
            stage1.draw();
    }

    @Override
    public void hide() {

    }
}

