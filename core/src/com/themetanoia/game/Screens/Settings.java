package com.themetanoia.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Tools.AudioManager;

/**
 * Created by MITHOON on 04-05-2017.
 */
public class Settings extends Game implements Screen {

    private Stage stage;
    private Viewport viewport;
    private BitmapFont font;
    private TextButton.TextButtonStyle style;
    public TextButton musicswitch,soundswitch;
    private TextButton backarrow;
    private TextButton.TextButtonStyle bstyle;
    private Skin skin,skin1;
    private TextureAtlas atlas;
    private FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    private AudioManager audio;

    Lone_Warrior1 game;

    public Settings(Lone_Warrior1 game){
        this.game=game;

        viewport=new StretchViewport(Lone_Warrior1.V_Width,Lone_Warrior1.V_Height,new OrthographicCamera());

        atlas=new TextureAtlas();
        atlas=game.getAtlas(0);
        stage=new Stage(viewport,game.batch);
        generator= new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Variane Script.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=50;
        parameter.color= Color.WHITE;
        font=new BitmapFont();
        font=generator.generateFont(parameter);
        generator.dispose();
        skin=new Skin();
        skin.addRegions(atlas);
        atlas=game.getAtlas(5);
        skin1=new Skin();
        skin1.addRegions(atlas);

        audio=new AudioManager(game);

        Table table=new Table();
        table.center();
        table.setFillParent(true);

        Table table1=new Table();
        table1.top().left();
        table1.setFillParent(true);

        Gdx.input.setInputProcessor(stage);

        style=new TextButton.TextButtonStyle();
        style.font=font;
        style.up= skin.getDrawable("startbutton");
        style.down= skin.getDrawable("startbuttondown");
        if(game.getPrefs().getBoolean("musicoff"))
        musicswitch =new TextButton("Music Off",style);
        else
        musicswitch =new TextButton("Music On",style);

        style=new TextButton.TextButtonStyle();
        style.font=font;
        style.up= skin.getDrawable("startbutton");
        style.down= skin.getDrawable("startbuttondown");
        if(game.getPrefs().getBoolean("soundoff"))
            soundswitch =new TextButton("Sound Effects Off",style);
        else
            soundswitch =new TextButton("Sound Effects On",style);


        bstyle=new TextButton.TextButtonStyle();
        bstyle.font=font;
        bstyle.up=skin1.getDrawable("Leftarrow");
        bstyle.down=skin1.getDrawable("Leftarrowdown");
        backarrow=new TextButton("",bstyle);

        table.add(musicswitch).expandX().padBottom(10).width(400).height(100).center();
        table.row();
        table.add(soundswitch).expandX().padBottom(100).width(400).height(100).center();
        table1.add(backarrow).expandX().padLeft(10).width(100).height(100).top().left();

        stage.addActor(table);
        stage.addActor(table1);

    }

    @Override
    public void create() {

    }

    @Override
    public void show() {
        musicswitch.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Start Clicked");
                if(!game.getPrefs().getBoolean("musicoff")){
                    game.getPrefs().putBoolean("musicoff",true);
                    audio.stopAll();
                    musicswitch.setText("Music Off");
                }
                else if(game.getPrefs().getBoolean("musicoff")){
                    game.getPrefs().putBoolean("musicoff",false);
                    musicswitch.setText("Music On");
                }
                game.getPrefs().flush();
            }
        });

        soundswitch.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Start Clicked");
                if(!game.getPrefs().getBoolean("Soundoff")){
                    game.getPrefs().putBoolean("Soundoff",true);
                    soundswitch.setText("Sound Effects Off");
                }
                else if(game.getPrefs().getBoolean("Soundoff")){
                    game.getPrefs().putBoolean("Soundoff",false);
                    soundswitch.setText("Sound Effects On");
                }
                game.getPrefs().flush();
            }
        });


        backarrow.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(new MenuScreen(game));
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
    public void hide() {

    }
}
