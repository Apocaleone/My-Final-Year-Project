package com.themetanoia.game.Load_Screens;

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
import com.themetanoia.game.Screens.Levels.Level1;
import com.themetanoia.game.Screens.MenuScreen;

/**
 * Created by MITHUN on 13-11-2016.
 */
public class LoadState extends Game implements Screen {
    public Lone_Warrior1 game;

    private Stage stage,stage1;
    private Viewport viewport;
    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private TextButton.TextButtonStyle playbuttonStyle;
    public TextButton playbutton;
    private Skin skin;
    private TextureAtlas atlas;


    private Label loading;
    private String progress;
    private Texture texture;
    private Image image;

    public LoadState(Lone_Warrior1 game){
        this.game=game;
        viewport=new StretchViewport(Lone_Warrior1.V_Width,Lone_Warrior1.V_Height,new OrthographicCamera());
        stage=new Stage(viewport,game.batch);
        stage1=new Stage(viewport,game.batch);


        texture=new Texture("Logo.png");
        atlas=new TextureAtlas("MenuButtons.pack");


        generator= new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Variane Script.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=50;
        font=new BitmapFont();
        font=generator.generateFont(parameter);
        generator.dispose();

        Table table=new Table();
        table.center();
        table.setFillParent(true);

        loading=new Label("Loading: "+ progress+"%",new Label.LabelStyle(font, Color.BLACK));
        image=new Image(texture);

        table.add(image);
        table.row();
        table.add(loading);

        stage.addActor(table);

        Table table1=new Table();
        table1.bottom();
        table1.setFillParent(true);

        Gdx.input.setInputProcessor(stage1);
        skin=new Skin();
        skin.addRegions(atlas);

        playbuttonStyle=new TextButton.TextButtonStyle();            //button 1 properties
        playbuttonStyle.up= skin.getDrawable("startbutton");
        playbuttonStyle.down= skin.getDrawable("startbuttondown");
        playbuttonStyle.font=font;
        playbutton= new TextButton("Play",playbuttonStyle);


        table1.add(playbutton).expandX().width(400).height(80).right();
        stage1.addActor(table1);

        game.assets.load();
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
                stage.dispose();
                stage1.dispose();
                game.assigner();
                game.setScreen(new MenuScreen(game));
            }
        });

    }

    public void update(float delta){

        if(game.assets.manager.update()){
            stage1.act();
            stage1.draw();
            /**/
        }

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);


       // if(!game.assets.manager.update()){
            progress=Integer.toString(Math.round(game.assets.manager.getProgress()*100));
            loading.setText("Loading "+progress+"%");

        stage.act();
        stage.draw();


    }

    @Override
    public void hide() {

    }
}
