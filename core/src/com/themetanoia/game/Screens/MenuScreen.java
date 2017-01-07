package com.themetanoia.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.themetanoia.game.Load_Screens.LoadState;
import com.themetanoia.game.Lone_Warrior1;

/**
 * Created by MITHUN on 28-11-2016.
 */
public class MenuScreen extends Game implements Screen {
    public Stage stage;
    private BitmapFont font;
    private TextButton.TextButtonStyle startbuttonStyle;
    public TextButton startbutton;
    private Skin skin;

    LoadState loading;

    Lone_Warrior1 game;

    public MenuScreen(Lone_Warrior1 game){
        loading=new LoadState();

        this.game=game;


        stage=new Stage();
        font=new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);
        skin=new Skin();
        skin.addRegions(loading.assets.manager.get("MenuButtons.pack",TextureAtlas.class));

        Table table=new Table();
        table.center();
        table.setFillParent(true);

        Gdx.input.setInputProcessor(stage);


        startbuttonStyle=new TextButton.TextButtonStyle();            //button 1 properties
        startbuttonStyle.up= skin.getDrawable("startbutton");
        startbuttonStyle.down= skin.getDrawable("startbuttondown");
        startbuttonStyle.font=font;
        startbutton= new TextButton(" ",startbuttonStyle);

        table.add(startbutton).expandX().padTop(100).width(200).height(50).center();

        stage.addActor(table);


    }


    @Override
    public void show() {
        startbutton.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                System.out.println("Start Clicked");
                setScreen(new Play_State(game));
                return true;
            }
        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
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
        stage.dispose();

    }
}
