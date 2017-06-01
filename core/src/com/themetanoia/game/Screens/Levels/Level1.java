package com.themetanoia.game.Screens.Levels;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
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
import com.themetanoia.game.Screens.Play_State;
import com.themetanoia.game.Screens.StoryView;

/**
 * Created by MITHOON on 05-04-2017.
 */
public class Level1 extends Game implements Screen {
    private Stage stage;
    private Viewport viewport;
    private BitmapFont font,font1;
    private TextButton.TextButtonStyle rightArrowStyle,chapter1Style,chapter2Style,chapter3Style;
    public TextButton rightArrow,chapter1,chapter2,chapter3;

    private Skin skin;
    private TextureAtlas atlas;
    private FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    Label level;

    private Lone_Warrior1 game;
    public InputMultiplexer multiplexer;
    private Image image;
    private Texture texture;

    public Level1(Lone_Warrior1 game){
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

        texture=new Texture(Gdx.files.internal("paper.png"));
        image=new Image(texture);

        Table table3=new Table();
        table3.center();
        table3.setFillParent(true);
        table3.add(image);


        skin=new Skin();
        skin.addRegions(atlas);

        Gdx.input.setInputProcessor(stage);

        level=new Label("Level 1",new Label.LabelStyle(font,Color.FIREBRICK));
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

        table1.add(rightArrow).padBottom(120).right().expandX().width(70);
        
        Table table2=new Table();
        table2.top();
        table2.setFillParent(true);

        chapter1Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter1Style.up= skin.getDrawable("icon");
        chapter1Style.down=skin.getDrawable("icondown");
        chapter1Style.font=font1;
        chapter1Style.fontColor=Color.BLACK;
        chapter1= new TextButton("Act 1 ",chapter1Style);

        chapter2Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter2Style.up= skin.getDrawable("icon");
        chapter2Style.down=skin.getDrawable("icondown");
        chapter2Style.font=font1;
        if(game.getPrefs().getBoolean("unlock12")==true){
        chapter2Style.fontColor=Color.BLACK;
        chapter2= new TextButton("Act 2",chapter2Style);}
        else{
            chapter2Style.fontColor=Color.FIREBRICK;
            chapter2= new TextButton("Locked",chapter2Style);}


        chapter3Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter3Style.up= skin.getDrawable("icon");
        chapter3Style.down=skin.getDrawable("icondown");
        chapter3Style.font=font1;
        if(game.getPrefs().getBoolean("unlock13")==true){
            chapter3Style.fontColor=Color.BLACK;
            chapter3= new TextButton("Act 3",chapter3Style);}
        else{
            chapter3Style.fontColor=Color.FIREBRICK;
            chapter3= new TextButton("Locked",chapter3Style);}


        table2.add(chapter1).expandX().padTop(100).width(120).height(140);
        table2.add(chapter2).expandX().padTop(100).width(120).height(140);
        table2.add(chapter3).expandX().padTop(100).width(120).height(140);

        stage.addActor(table3);
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
                stage.dispose();
                game.setScreen(new Level2(game));
            }
        });

        chapter1.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                stage.dispose();
                game.setScreen(new StoryView(game,0.5f,1,1,2));
            }
        });

        chapter2.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock12")==true){
                stage.dispose();
                game.setScreen(new StoryView(game,-1f,1,2,2));}
            }
        });

        chapter3.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){

                if(game.getPrefs().getBoolean("unlock13")==true){
                stage.dispose();
                 game.setScreen(new StoryView(game,-2f,1,3,2));}
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
