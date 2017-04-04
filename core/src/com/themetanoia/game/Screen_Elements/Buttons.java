package com.themetanoia.game.Screen_Elements;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Screens.Play_State;

/**
 * Created by MITHUN on 10-11-2016.
 */
public class Buttons implements ApplicationListener {
    private Viewport viewport;
    public Stage stage, resumestage;
    private BitmapFont font;
    private TextButton.TextButtonStyle button1Style,button2Style,button3Style,button4Style,pauseButtonStyle,startbuttonStyle;
    public TextButton button1,button2,button3,button4,pauseButton,startbutton;
    private Skin skin,pauseskin,startskin;
    private boolean b1check=false, b2check=false, b3check=false, b4check=false;
    private TextureAtlas atlas;
    private Play_State state;





    public Buttons(SpriteBatch sb,Play_State state){
        this.state=state;

        viewport=new StretchViewport(Lone_Warrior1.V_Width,Lone_Warrior1.V_Height,new OrthographicCamera());
        stage=new Stage(viewport,sb);
        resumestage=new Stage(viewport,sb);
        atlas=new TextureAtlas();
        atlas=Lone_Warrior1.getAtlas(1);
        font=new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3);
        skin=new Skin();
        pauseskin=new Skin();
        startskin=new Skin();
        skin.addRegions(atlas);
        pauseskin.add("pause",new Texture("pausebutton.png"));
        startskin.add("resume",new Texture("Resumebuttondown.png"));


        Table table=new Table();
        Table resumetable=new Table();



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

        pauseButtonStyle=new TextButton.TextButtonStyle(); //Button4 properties!
        pauseButtonStyle.up= pauseskin.getDrawable("pause");
        pauseButtonStyle.font=font;
        pauseButton= new TextButton(" ",pauseButtonStyle);

        startbuttonStyle=new TextButton.TextButtonStyle();            //button 1 properties
        startbuttonStyle.up= startskin.getDrawable("resume");
        //startbuttonStyle.down= skin.getDrawable("Resumebutton");
        startbuttonStyle.font=font;
        startbutton= new TextButton(" ",startbuttonStyle);


        table.top();
        table.setFillParent(true);
        table.add(pauseButton).expandX().padTop(5).padLeft(1100).width(50).height(30).right();

        //table.center();
        table.row();
        table.row();
        table.add(button1).expandX().padTop(150).width(300).height(50).left();
        table.add(button2).expandX().padTop(150).width(300).height(50).right();
        table.row();
        table.add(button3).expandX().padTop(30).width(300).height(50).left();
        table.add(button4).expandX().padTop(30).width(300).height(50).right();

        resumetable.bottom();
        resumetable.setFillParent(true);
        resumetable.add(startbutton).expandX().padBottom(100).width(200).height(50).center();

        stage.addActor(table);
        resumestage.addActor(resumetable);

    }

    public void buttonmode(){
        if(Play_State.halt==false){
            Gdx.input.setInputProcessor(stage);
        }
        else
            Gdx.input.setInputProcessor(resumestage);
    }

    @Override
    public void create() {
        pauseButton.addListener(new ClickListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                System.out.println("Pause button");
                Play_State.halt=true;
            }
        });
        button1.addListener(new ClickListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                b1check=true;
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                b1check=false;
                if(b2check==true){
                    if(state.flagchecker(1)==false&&Warrior.posture!=-1) {
                        state.switchstate();
                        Warrior.posture = 8;
                        state.bodiesToRemove.add(Warrior.hero);
                        Warrior.defineExorcize();
                        Warrior.exorcizeattack.applyForceToCenter(100,0, true);
                        // }
                    }
                }
                else if(state.flagchecker(1)==false&&Warrior.posture!=-1&&b2check==false) {
                    state.switchstate();
                    Warrior.posture = 4;
                    state.bodiesToRemove.add(Warrior.hero);
                    Warrior.defineGroundpunch();
                    Warrior.groundpunchattack.applyForceToCenter(100,0, true);
                    // }
                }
                System.out.println("Star Clicked");
            }
        });



        button2.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                b2check=true;
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                b2check=false;
                if(b1check==true){
                    if(state.flagchecker(1)==false&&Warrior.posture!=-1) {
                        state.switchstate();
                        Warrior.posture = 6;
                        state.bodiesToRemove.add(Warrior.hero);
                        Warrior.defineMegapunch();
                        Warrior.megapunchattack.applyForceToCenter(100,0, true);
                        // }
                    }
                }
               else if(state.flagchecker(1)==false&&Warrior.posture!=-1&&b1check==false) {
                    state.switchstate();
                    Warrior.posture = 5;
                    state.bodiesToRemove.add(Warrior.hero);
                    Warrior.defineSpinpunch();
                    Warrior.spinpunchattack.applyForceToCenter(100,0, true);
                    // }
                }
                System.out.println("Star Clicked");
            }
        });
        button3.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                b3check=true;
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                b3check=false;
                if(b4check==true){
                    if(state.flagchecker(1)==false&&Warrior.posture!=-1) {
                        state.switchstate();
                        Warrior.posture = 7;
                        state.bodiesToRemove.add(Warrior.hero);
                        Warrior.defineHurricanebreath();
                        Warrior.hurricanebreathattack.applyForceToCenter(100,0, true);
                    }
                }
                else if(state.flagchecker(1)==false&&Warrior.posture!=-1&&b4check==false) {
                    state.switchstate();
                    Warrior.posture = 2;
                    state.bodiesToRemove.add(Warrior.hero);
                    Warrior.defineLowkick();
                    Warrior.lowkickattack.applyForceToCenter(100,0, true);
                    // }
                }
                System.out.println("Star Clicked");
            }
        });
        button4.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                System.out.println("button4 Clicked");
                b4check=true;
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                b4check=false;
                if(b3check==true){
                    if(state.flagchecker(1)==false&&Warrior.posture!=-1) {
                        state.switchstate();
                        Warrior.posture = 3;
                        state.bodiesToRemove.add(Warrior.hero);
                        Warrior.defineMultikick();
                        Warrior.multikickattack.applyForceToCenter(100,0, true);
                        // }
                    }
                }
               else if(state.flagchecker(1)==false&&Warrior.posture!=-1&&b3check==false) {
                    state.switchstate();
                    Warrior.posture = 1;
                    state.bodiesToRemove.add(Warrior.hero);
                    Warrior.defineHighkick();
                    Warrior.highkickattack.applyForceToCenter(100,0, true);
                    // }
                }
                System.out.println("Star Clicked");
            }
        });

        startbutton.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                System.out.println("Start Clicked");
                Play_State.halt=false;
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
