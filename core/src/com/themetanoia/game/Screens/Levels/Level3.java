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
public class Level3 extends Game implements Screen {

    private Stage stage;
    private Viewport viewport;
    private BitmapFont font,font1;

    private TextButton.TextButtonStyle rightArrowStyle,leftArrowStyle;
    private TextButton.TextButtonStyle chapter1Style,chapter2Style,chapter3Style,chapter4Style,chapter5Style,chapter6Style;
    public TextButton rightArrow,leftArrow;
    public TextButton chapter1,chapter2,chapter3,chapter4,chapter5,chapter6;

    public Image image;
    private Skin skin;
    private TextureAtlas atlas;
    private FreeTypeFontGenerator generator;

    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private Label act1,act2,act3,act4,act5,act6;

    private Lone_Warrior1 game;
    public InputMultiplexer multiplexer;

    public Level3(Lone_Warrior1 game){
        this.game = game;
        viewport=new StretchViewport(Lone_Warrior1.V_Width,Lone_Warrior1.V_Height,new OrthographicCamera());
        atlas=new TextureAtlas();
        atlas=game.getAtlas(5);
        image=new Image(atlas.findRegion("Level3"));
        stage=new Stage(viewport,game.batch);


        generator= new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Variane Script.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=30;
        font=new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(2);
        font1=new BitmapFont();
        font1=generator.generateFont(parameter);
        generator.dispose();


        skin=new Skin();
        skin.addRegions(atlas);

        Gdx.input.setInputProcessor(stage);

        Table table=new Table();
        table.top();
        table.setFillParent(true);
        table.add(image);

        Table table1=new Table();
        table1.center();
        table1.setFillParent(true);

        rightArrowStyle=new TextButton.TextButtonStyle();            //button 1 properties
        rightArrowStyle.up= skin.getDrawable("Rightarrowup");
        rightArrowStyle.down= skin.getDrawable("Rightarrowdown");
        rightArrowStyle.font=font;
        rightArrow= new TextButton(" ",rightArrowStyle);

        leftArrowStyle=new TextButton.TextButtonStyle();            //button 1 properties
        leftArrowStyle.up= skin.getDrawable("Leftarrowup");
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
        chapter1Style.font=font;
        chapter1= new TextButton(" ",chapter1Style);

        chapter2Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter2Style.up= skin.getDrawable("icon");
        chapter2Style.font=font;
        chapter2= new TextButton(" ",chapter2Style);

        chapter3Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter3Style.up= skin.getDrawable("icon");
        chapter3Style.font=font;
        chapter3= new TextButton(" ",chapter3Style);

        chapter4Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter4Style.up= skin.getDrawable("icon");
        chapter4Style.font=font;
        chapter4= new TextButton(" ",chapter4Style);

        chapter5Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter5Style.up= skin.getDrawable("icon");
        chapter5Style.font=font;
        chapter5= new TextButton(" ",chapter5Style);

        chapter6Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter6Style.up= skin.getDrawable("icon");
        chapter6Style.font=font;
        chapter6= new TextButton(" ",chapter6Style);

        act1=new Label("Act 1",new Label.LabelStyle(font1,Color.WHITE));
        act2=new Label("Act 2",new Label.LabelStyle(font1,Color.WHITE));
        act3=new Label("Act 3",new Label.LabelStyle(font1,Color.WHITE));
        act4=new Label("Act 4",new Label.LabelStyle(font1,Color.WHITE));
        act5=new Label("Act 5",new Label.LabelStyle(font1,Color.WHITE));
        act6=new Label("Act 6",new Label.LabelStyle(font1,Color.WHITE));

        table2.add(chapter1).expandX().padTop(100).width(100).height(100);
        table2.add(chapter2).expandX().padTop(100).width(100).height(100);
        table2.add(chapter3).expandX().padTop(100).width(100).height(100);
        table2.add(chapter4).expandX().padTop(100).width(100).height(100);
        table2.add(chapter5).expandX().padTop(100).width(100).height(100);
        table2.row();
        table2.add(act1).expandX().padTop(10);
        table2.add(act2).expandX().padTop(10);
        table2.add(act3).expandX().padTop(10);
        table2.add(act4).expandX().padTop(10);
        table2.add(act5).expandX().padTop(10);
        table2.row();
        table2.add(chapter6).expandX().padTop(10).width(100).height(100);
        table2.row();
        table2.add(act6).expandX().padTop(10);


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
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
            }
        });

        leftArrow.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(new Level2(game));
            }
        });

        chapter1.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(new Play_State(game,-0.5f,7,0));
            }
        });

        chapter2.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(new Play_State(game,-1f,3,0));
            }
        });

        chapter3.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(new Play_State(game,-1.5f,3,0));
            }
        });


        chapter4.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(new Play_State(game,-2f,3,0));
            }
        });


        chapter5.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(new Play_State(game,-2.2f,3,0));
            }
        });


        chapter6.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(new Play_State(game,-2.4f,7,0));
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
