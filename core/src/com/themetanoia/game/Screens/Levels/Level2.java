package com.themetanoia.game.Screens.Levels;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.themetanoia.game.Screens.Play_State;

/**
 * Created by MITHOON on 06-04-2017.
 */
public class Level2 extends Game implements Screen {

    private Stage stage;
    private Viewport viewport;
    private BitmapFont font,font1;
    
    private TextButton.TextButtonStyle rightArrowStyle,leftArrowStyle;
    private TextButton.TextButtonStyle chapter1Style,chapter2Style,chapter3Style,chapter4Style;
    public TextButton rightArrow,leftArrow; 
    public TextButton chapter1,chapter2,chapter3,chapter4;

    private Skin skin;
    private TextureAtlas atlas;
    private FreeTypeFontGenerator generator;
    
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private Label act1,act2,act3,act4,level;

    private Lone_Warrior1 game;
    public InputMultiplexer multiplexer;

    public Level2(Lone_Warrior1 game){
        this.game = game;
        viewport=new StretchViewport(Lone_Warrior1.V_Width,Lone_Warrior1.V_Height,new OrthographicCamera());
        atlas=new TextureAtlas();
        atlas=game.getAtlas(5);
        stage=new Stage(viewport,game.batch);


        generator= new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Variane Script.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=70;
        font=new BitmapFont();
        font=generator.generateFont(parameter);
        parameter.size=30;
        font1=new BitmapFont();
        font1=generator.generateFont(parameter);
        generator.dispose();


        skin=new Skin();
        skin.addRegions(atlas);

        Gdx.input.setInputProcessor(stage);

        level=new Label("Level 2",new Label.LabelStyle(font,Color.FIREBRICK));

        Table table=new Table();
        table.top();
        table.setFillParent(true);
        table.add(level);

        Table table1=new Table();
        table1.center();
        table1.setFillParent(true);

        rightArrowStyle=new TextButton.TextButtonStyle();            //button 1 properties
        rightArrowStyle.up= skin.getDrawable("Rightarrow");
        rightArrowStyle.down= skin.getDrawable("Rightarrowdown");
        rightArrowStyle.font=font;
        rightArrow= new TextButton(" ",rightArrowStyle);

        leftArrowStyle=new TextButton.TextButtonStyle();            //button 1 properties
        leftArrowStyle.up= skin.getDrawable("Leftarrow");
        leftArrowStyle.down= skin.getDrawable("Leftarrowdown");
        leftArrowStyle.font=font;
        leftArrow= new TextButton(" ",leftArrowStyle);

        //table1.setDebug(true);
        table1.add(leftArrow).padBottom(120).left().expandX().width(70);
        table1.add(rightArrow).padBottom(120).right().expandX().width(70);


        Table table2=new Table();
        table2.top();
        table2.setFillParent(true);

        chapter1Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter1Style.up= skin.getDrawable("icon");
        chapter1Style.font=font1;
        chapter1= new TextButton("Act 1",chapter1Style);

        chapter2Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter2Style.up= skin.getDrawable("icon");
        chapter2Style.font=font1;
        chapter2= new TextButton("Act 2",chapter2Style);

        chapter3Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter3Style.up= skin.getDrawable("icon");
        chapter3Style.font=font1;
        chapter3= new TextButton("Act 3",chapter3Style);

        chapter4Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter4Style.up= skin.getDrawable("icon");
        chapter4Style.font=font1;
        chapter4= new TextButton("Act 4",chapter4Style);

        act1=new Label("Act 1",new Label.LabelStyle(font1,Color.WHITE));
        act2=new Label("Act 2",new Label.LabelStyle(font1,Color.WHITE));
        act3=new Label("Act 3",new Label.LabelStyle(font1,Color.WHITE));
        act4=new Label("Act 4",new Label.LabelStyle(font1,Color.WHITE));

        table2.add(chapter1).expandX().padTop(100).width(120).height(140);
        table2.add(chapter2).expandX().padTop(100).width(120).height(140);
        table2.add(chapter3).expandX().padTop(100).width(120).height(140);
        table2.add(chapter4).expandX().padTop(100).width(120).height(140);


        stage.addActor(table);
        stage.addActor(table1);
        stage.addActor(table2);

    }
    @Override
    public void create() {

    }

    @Override
    public void show() {
        rightArrow.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                System.out.println("Button pressed down");
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                System.out.println("Button released");
                stage.dispose();
                game.setScreen(new Level3(game));
            }
        });

        leftArrow.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                stage.dispose();
                game.setScreen(new Level1(game));
            }
        });

        chapter1.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                stage.dispose();
                game.setScreen(new Play_State(game,-0.5f,2,0));
            }
        });

        chapter2.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                stage.dispose();
                game.setScreen(new Play_State(game,-1f,2,0));
            }
        });

        chapter3.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                stage.dispose();
                game.setScreen(new Play_State(game,-1.5f,2,0));
            }
        });


        chapter4.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                stage.dispose();
                game.setScreen(new Play_State(game,-2f,2,0));
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
    public void hide() {

    }
}
