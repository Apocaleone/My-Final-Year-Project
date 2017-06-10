package com.themetanoia.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Screens.Levels.Level1;
import com.themetanoia.game.Tools.AudioManager;

/**
 * Created by MITHUN on 28-11-2016.
 */
public class MenuScreen extends Game implements Screen {
    private Stage stage;
    private Viewport viewport;
    private BitmapFont font;
    private TextButton.TextButtonStyle startbuttonStyle,settingbuttonStyle,testButtonStyle,creditButtonStyle,aboutButtonStyle;
    public TextButton startbutton,settingbutton,testButton,creditButton,aboutButton;
    private Skin skin;
    private TextureAtlas atlas;
    private FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private Image image;
    private Texture texture;

    private AudioManager audio;


    private Lone_Warrior1 game;

    public MenuScreen(Lone_Warrior1 game){

        this.game=game;

        viewport=new StretchViewport(Lone_Warrior1.V_Width,Lone_Warrior1.V_Height,new OrthographicCamera());

        atlas=new TextureAtlas();
        atlas=game.getAtlas(0);
        stage=new Stage(viewport,game.batch);
        generator= new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Variane Script.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=50;
        font=new BitmapFont();
        font=generator.generateFont(parameter);
        generator.dispose();
        skin=new Skin();
        skin.addRegions(atlas);

        audio=new AudioManager(game);

        texture=new Texture(Gdx.files.internal("paper.png"));
        image=new Image(texture);
        Table table1=new Table();
        table1.center();
        table1.setFillParent(true);

        Table table=new Table();
        table.center();
        table.setFillParent(true);
        table1.add(image);

        Gdx.input.setInputProcessor(stage);


        startbuttonStyle=new TextButton.TextButtonStyle();            //button 1 properties
        startbuttonStyle.up= skin.getDrawable("startbutton");
        startbuttonStyle.down= skin.getDrawable("startbuttondown");
        startbuttonStyle.font=font;
        startbutton= new TextButton("Start",startbuttonStyle);

        settingbuttonStyle=new TextButton.TextButtonStyle();            //button 1 properties
        settingbuttonStyle.up= skin.getDrawable("startbutton");
        settingbuttonStyle.down= skin.getDrawable("startbuttondown");
        settingbuttonStyle.font=font;
        settingbutton= new TextButton("Settings",settingbuttonStyle);

        testButtonStyle=new TextButton.TextButtonStyle();            //button 1 properties
        testButtonStyle.up= skin.getDrawable("startbutton");
        testButtonStyle.down= skin.getDrawable("startbuttondown");
        testButtonStyle.font=font;
        testButton= new TextButton("Tutorials",testButtonStyle);

        creditButtonStyle=new TextButton.TextButtonStyle();            //button 1 properties
        creditButtonStyle.up= skin.getDrawable("startbutton");
        creditButtonStyle.down= skin.getDrawable("startbuttondown");
        creditButtonStyle.font=font;
        creditButton= new TextButton("Credits",creditButtonStyle);

        aboutButtonStyle=new TextButton.TextButtonStyle();            //button 1 properties
        aboutButtonStyle.up= skin.getDrawable("startbutton");
        aboutButtonStyle.down= skin.getDrawable("startbuttondown");
        aboutButtonStyle.font=font;
        aboutButton= new TextButton("About",aboutButtonStyle);


        table.add(startbutton).expandX().padBottom(10).width(400).height(100).center();
        table.row();
        table.add(settingbutton).expandX().padBottom(10).width(400).height(100).center();
        table.row();
        table.add(testButton).expandX().padBottom(10).width(400).height(100).center();
        table.row();
        table.add(creditButton).expandX().padBottom(10).width(400).height(100).center();
        table.row();
        table.add(aboutButton).expandX().padBottom(10).width(400).height(100).center();



        stage.addActor(table1);
        stage.addActor(table);
        audio.playMusic(0);
        game.prefs.flush();



    }


    @Override
    public void show() {
        startbutton.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                audio.playbSound(0);
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                dispose();
                game.setScreen(new Level1(game));//game.setScreen(new Play_State(game));
            }
        });

        settingbutton.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                audio.playbSound(0);

                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                dispose();
                game.setScreen(new Settings(game));
            }
        });
        testButton.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                audio.playbSound(0);

                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                dispose();
                game.setScreen(new Tutorials(game));
            }
        });

        creditButton.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                audio.playbSound(0);

                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                dispose();
                game.setScreen(new Credits(game));
            }
        });

        aboutButton.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                audio.playbSound(0);

                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                dispose();
                game.setScreen(new About(game));
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,0.95f,0.95f,1);
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
