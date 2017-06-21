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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Tools.AudioManager;

/**
 * Created by MITHOON on 06-05-2017.
 */
public class StoryView extends Game implements Screen {
    private Stage stage,stage1;
    private Viewport viewport;
    private BitmapFont font,font1;
    private TextButton.TextButtonStyle playbuttonStyle,settingbuttonStyle;
    public TextButton playbutton,settingbutton;
    private Skin skin;
    private TextureAtlas atlas,atlas1;
    private FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private String text;


    private Lone_Warrior1 game;
    Label story;
    Label title;
    int level, act,beatscore;
    float speed;
    private AudioManager audio;


    public StoryView(Lone_Warrior1 game,float speed, int level, int act,int beatscore){
        this.game=game;
        this.level=level;
        this.act=act;
        this.speed=speed;
        this.beatscore=beatscore;
        FileHandle test;
        if(level<3||(level==3&&act==1))
        { test=Gdx.files.internal("Story/Chapter"+level+act+".txt");}
        else
        test=Gdx.files.internal("Story/default.txt");
        text=test.readString();
        viewport=new StretchViewport(Lone_Warrior1.V_Width,Lone_Warrior1.V_Height,new OrthographicCamera());

        audio=new AudioManager(game);

        atlas=new TextureAtlas();
        atlas=game.getAtlas(5);

        stage=new Stage(viewport,game.batch);

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
        skin.addRegions(atlas);

        Gdx.input.setInputProcessor(stage);

        story=new Label(text,new Label.LabelStyle(font, Color.FIREBRICK));
        story.setWrap(true);
        story.pack();

        title=new Label("ACT "+act,new Label.LabelStyle(font1, Color.WHITE));

        playbuttonStyle=new TextButton.TextButtonStyle();            //button 1 properties
        playbuttonStyle.up= skin.getDrawable("skipup");
        playbuttonStyle.down= skin.getDrawable("skipdown");
        playbuttonStyle.font=font;
        playbutton= new TextButton(" ",playbuttonStyle);


        Table screenElements=new Table();
        screenElements.right();
        screenElements.setFillParent(true);

        Table scrollTable=new Table();
        scrollTable.add(title).center().padBottom(30);
        scrollTable.row();
        scrollTable.add(story).width(1000);

        ScrollPane scrollPane=new ScrollPane(scrollTable);

        Table parentTable=new Table();
        parentTable.setFillParent(true);
        parentTable.add(scrollPane).fill().expand();

        screenElements.add(playbutton).expandX().padBottom(10).width(100).height(100).right();


        stage.addActor(parentTable);
        stage.addActor(screenElements);
        audio.stopAll();
        audio.playMusic(1);
        audio.music.get(1).setVolume(0.4f);
    }
    @Override
    public void create() {

    }

    @Override
    public void show() {
        playbutton.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                audio.playbSound(0);
                audio.stopAll();
                stage.dispose();
                game.setScreen(new InGameTutorials(game,speed,level,act,beatscore));//levelsManager.levelSelector();//game.setScreen(new Play_State(game));
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {

    }
}
