package com.themetanoia.game.Screen_Elements;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.themetanoia.game.Characters.Warrior;
import com.themetanoia.game.Load_Screens.LoadState;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Screens.Play_State;

/**
 * Created by MITHUN on 10-11-2016.
 */
public class Buttons implements ApplicationListener {
    private Viewport viewport;
    public Stage stage;
    private BitmapFont font;
    private TextButton.TextButtonStyle button1Style,button2Style,button3Style,button4Style;
    public TextButton button1,button2,button3,button4;
    private Skin skin;
    private boolean b1check=false;

    LoadState loading;




    public Buttons(SpriteBatch sb){
        loading=new LoadState();

        viewport=new StretchViewport(Lone_Warrior1.V_Width,Lone_Warrior1.V_Height,new OrthographicCamera());
        stage=new Stage(viewport,sb);
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

    @Override
    public void create() {
        button1.addListener(new ClickListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                b1check=true;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                b1check=false;
                System.out.println("Star Clicked");
            }
        });



        button2.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                //if(b1check==true) {
                    if(Play_State.ongoingmove==false) {
                        Play_State.ongoingmove = true;
                        Warrior.posture = 1;
                        Play_State.bodiesToRemove.add(Warrior.hero);
                        Warrior.defineHighkick();
                        Warrior.highkickattack.applyForceToCenter(100,0, true);
                   // }
                }
                return true;
            }
        });
        button3.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                System.out.println("button3 Clicked");
                Warrior.hero.applyForceToCenter(-100,0,true);
                return true;
            }
        });
        button4.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                System.out.println("button4 Clicked");
                //if(b1check==true) {
                    if(Play_State.ongoingmove==false) {
                        Play_State.ongoingmove = true;
                        Warrior.posture = 2;
                        Play_State.bodiesToRemove.add(Warrior.hero);
                        Warrior.defineLowkick();
                        Warrior.lowkickattack.applyForceToCenter(100,0, true);
                        // }
                    }
                return true;
            }
        });

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

    public void dispose() {
        stage.dispose();
    }
}
