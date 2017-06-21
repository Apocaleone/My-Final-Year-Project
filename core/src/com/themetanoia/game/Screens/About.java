package com.themetanoia.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
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
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.themetanoia.game.Lone_Warrior1;

/**
 * Created by MITHOON on 11-06-2017.
 */
public class About extends Game implements Screen {

    private Stage stage;
    private Viewport viewport;
    private BitmapFont font,font1;
    private TextButton.TextButtonStyle menuButtonStyle ;
    public TextButton rightArrow,menuButton;

    private Skin skin;
    private TextureAtlas atlas,atlas1;
    private FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    Label level;

    private Lone_Warrior1 game;
    public InputMultiplexer multiplexer;
    private Array<Image> image;
    private Array<Texture> texture;


    public  About(Lone_Warrior1 game){
        this.game=game;
        viewport=new StretchViewport(Lone_Warrior1.V_Width,Lone_Warrior1.V_Height,new OrthographicCamera());

        atlas=new TextureAtlas();
        atlas1=new TextureAtlas();
        image=new Array<Image>();
        atlas=game.getAtlas(0);
        stage=new Stage(viewport,game.batch);
        generator= new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Variane Script.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=50;
        font=new BitmapFont();
        font=generator.generateFont(parameter);
        generator.dispose();
        skin=new Skin();
        skin.addRegions(atlas);

        Gdx.input.setInputProcessor(stage);

        menuButtonStyle=new TextButton.TextButtonStyle();            //button 1 properties
        menuButtonStyle.up= skin.getDrawable("startbutton");
        menuButtonStyle.down= skin.getDrawable("startbuttondown");
        menuButtonStyle.font=font;
        menuButton= new TextButton("Menu",menuButtonStyle);


        Table screenElements=new Table();
        screenElements.left().top();
        screenElements.setFillParent(true);

        atlas1=game.getAtlas(3);

        level=new Label("I know the art is a bit crappy but I do intend to update the game at a frequent rate and one of the first major changes would be to the animation, but ofcourse that would come after I " +
                "finish the story. I have some major plans for this project. This is my very first android application as well as my first developed game so please be gentle on your reviews. Feedback and criticism are ofcourse very welcome. I hope you enjoy the game as much as I enjoyed making this game." +
                "Updates might not start coming until after a few months though. I have to get a job first and I am afraid that might take some time. I was not the most studious of the lot you see. Anyway, enjoy the game, leave a review, rate the app, share the app and spread the word. Tell me if you find any bugs. My emil id is tmvmithun@gmail.com. For sending suggesions and contacting me. Toodles :)\n By Creator Mithun",new Label.LabelStyle(font, Color.WHITE));
level.setWrap(true);
        level.pack();
        Table scrollTable=new Table();
        scrollTable.row();
        scrollTable.row();
        scrollTable.add(level).width(1000).padTop(100);

        ScrollPane scrollPane=new ScrollPane(scrollTable);

        Table parentTable=new Table();
        parentTable.setFillParent(true);
        parentTable.add(scrollPane).fill().expand();

        screenElements.add(menuButton).expandX().padBottom(10).width(400).height(100).left().top();


        stage.addActor(parentTable);
        stage.addActor(screenElements);

    }
    @Override
    public void show() {
        menuButton.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(new MenuScreen(game));
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
    public void create() {

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

    }
}

