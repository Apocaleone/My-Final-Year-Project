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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.themetanoia.game.Lone_Warrior1;

/**
 * Created by MITHOON on 06-05-2017.
 */
public class StoryView extends Game implements Screen {
    private Stage stage;
    private Viewport viewport;
    private BitmapFont font,font1;
    private TextButton.TextButtonStyle playbuttonStyle,settingbuttonStyle;
    public TextButton playbutton,settingbutton;
    private Skin skin;
    private TextureAtlas atlas;
    private FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private String text;


    private Lone_Warrior1 game;
    Label story;
    Label title;

    public StoryView(Lone_Warrior1 game){
        this.game=game;
        FileHandle test=Gdx.files.internal("test1.txt");
        text=test.readString();
        viewport=new StretchViewport(Lone_Warrior1.V_Width,Lone_Warrior1.V_Height,new OrthographicCamera());

        atlas=new TextureAtlas();
        atlas=game.getAtlas(0);
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

        story=new Label(text,new Label.LabelStyle(font, Color.BLACK));
        story.setWrap(true);
        story.pack();

        title=new Label("Chapter 1",new Label.LabelStyle(font1, Color.RED));

        playbuttonStyle=new TextButton.TextButtonStyle();            //button 1 properties
        playbuttonStyle.up= skin.getDrawable("startbutton");
        playbuttonStyle.down= skin.getDrawable("startbuttondown");
        playbuttonStyle.font=font;
        playbutton= new TextButton("Play",playbuttonStyle);


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

        screenElements.add(playbutton).expandX().padBottom(10).width(400).height(100).right();


        stage.addActor(parentTable);
        stage.addActor(screenElements);



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
                System.out.println("Start Clicked");
                dispose();
                game.setScreen(new MenuScreen(game));//levelsManager.levelSelector();//game.setScreen(new Play_State(game));
            }
        });


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0.95f,0.95f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

    }

    @Override
    public void hide() {

    }
}
