package com.themetanoia.game.Screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.themetanoia.game.Lone_Warrior1;

/**
 * Created by MITHOON on 04-04-2017.
 */
public class PauseScreen extends Game implements Screen,ApplicationListener {
    public SpriteBatch sb;
    public Sprite sprite;
    public Texture texture;
    private TextButton.TextButtonStyle startbuttonStyle;
    public TextButton startbutton;
    private Skin skin;
    public Stage stage;
    private Viewport viewport;
    private BitmapFont font;


    public PauseScreen(Play_State state){
        viewport=new StretchViewport(Lone_Warrior1.V_Width,Lone_Warrior1.V_Height,new OrthographicCamera());
        font=new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);
        skin=new Skin();
        sb=new SpriteBatch();
        texture=new Texture("Lightscreen.png");
        sprite=new Sprite(texture);
    }

    @Override
    public void create() {
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void hide() {

    }
}
