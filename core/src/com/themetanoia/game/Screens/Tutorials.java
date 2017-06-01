package com.themetanoia.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
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
import com.themetanoia.game.Screens.MenuScreen;
import com.themetanoia.game.Screens.Play_State;

/**
 * Created by MITHOON on 30-05-2017.
 */
public class Tutorials extends Game implements Screen {

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


    public  Tutorials(Lone_Warrior1 game){
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
        screenElements.right();
        screenElements.setFillParent(true);

        atlas1=game.getAtlas(6);

        for(int i=0;i<8;i++){
            image.add(new Image(atlas1.findRegion("tutorial"+i)));
        }

        Table scrollTable=new Table();
        for(int i=0;i<8;i++){
        scrollTable.add(image.get(i)).center().padBottom(30);
        scrollTable.row();
        }

        ScrollPane scrollPane=new ScrollPane(scrollTable);

        Table parentTable=new Table();
        parentTable.setFillParent(true);
        parentTable.add(scrollPane).fill().expand();

        screenElements.add(menuButton).expandX().padBottom(10).width(400).height(100).right();


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
                System.out.println("Start Clicked");
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
