package com.themetanoia.game.Screen_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.themetanoia.game.Characters.Warrior;
import com.themetanoia.game.Load_Screens.LoadState;
import com.themetanoia.game.Load_Screens.PlayStateAssetmanager;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Screens.Play_State;

/**
 * Created by MITHUN on 10-11-2016.
 */
public class Buttons {
    public Stage stage;
    private BitmapFont font;
    private TextButton.TextButtonStyle button1Style,button2Style,button3Style,button4Style;
    public TextButton button1,button2,button3,button4;
    private Skin skin;

    LoadState loading;




    public Buttons(){
        loading=new LoadState();


        stage=new Stage();
        font=new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);
        skin=new Skin();
        skin.addRegions(loading.assets.manager.get("ButtonAtlas.pack",TextureAtlas.class));

        Table table=new Table();
        table.center();
        table.setFillParent(true);

        Gdx.input.setInputProcessor(stage);


        button1Style=new TextButton.TextButtonStyle();            //button 1 properties
        button1Style.up= skin.getDrawable("topleft");
        button1Style.font=font;
        button1= new TextButton(" ",button1Style);



        button2Style=new TextButton.TextButtonStyle(); //Button2 properties!
        button2Style.up= skin.getDrawable("topright");
        button2Style.font=font;
        button2= new TextButton(" ",button2Style);


        



        button3Style=new TextButton.TextButtonStyle(); //Button3 properties!
        button3Style.up= skin.getDrawable("bottomleft");
        button3Style.font=font;
        button3= new TextButton(" ",button3Style);






        button4Style=new TextButton.TextButtonStyle(); //Button4 properties!
        button4Style.up= skin.getDrawable("bottomright");
        button4Style.font=font;
        button4= new TextButton(" ",button4Style);


        table.add(button1).expandX().padTop(100).width(200).height(50).left();
        table.add(button2).expandX().padTop(100).width(200).height(50).right();
        table.row();
        table.add(button3).expandX().padTop(30).width(200).height(50).left();
        table.add(button4).expandX().padTop(30).width(200).height(50).right();

        stage.addActor(table);

    }

    public void dispose() {
        stage.dispose();
    }
}
