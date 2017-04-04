package com.themetanoia.game.Screen_Elements;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.themetanoia.game.Characters.Warrior;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Screens.Play_State;

/**
 * Created by MITHUN on 03-10-2016.
 */
public class Hud implements Disposable,ApplicationListener {
    public Stage stage;
    private Viewport viewport;


    private Integer score;
    private Integer Difficulty;

    Label scoreLabel;
    Label level;
    Label scoreString;
    Label levelString;

    public Hud(SpriteBatch sb)
    {
        score=0;
        Difficulty=1;

        viewport=new StretchViewport(Lone_Warrior1.V_Width,Lone_Warrior1.V_Height,new OrthographicCamera());
        stage=new Stage(viewport,sb);
        Gdx.input.setInputProcessor(stage);


        Table table=new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel=new Label(String.format("%06d",score),new Label.LabelStyle(new BitmapFont(), Color.WHITE));//String.format("%06d",score)
        level=new Label(String.format("%01d",Difficulty),new Label.LabelStyle(new BitmapFont(), Color.WHITE));//String.format("%01d",Difficulty)
        levelString=new Label("Level",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreString=new Label("Score",new Label.LabelStyle(new BitmapFont(), Color.WHITE));




        table.add(scoreString).expandX().padTop(10);
        table.add(levelString).expandX().padTop(10);
       // table.add(pauseButton).expandX().padTop(5).width(50).height(30).top();
        table.row();
        table.add(scoreLabel).expandX();
        table.add(level).expandX();

        stage.addActor(table);
    }


    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
