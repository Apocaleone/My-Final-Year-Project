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
import com.themetanoia.game.Screens.StoryView;
import com.themetanoia.game.Tools.AudioManager;

/**
 * Created by MITHOON on 10-06-2017.
 */
public class Level4 extends Game implements Screen {
    private Stage stage;
    private Viewport viewport;
    private BitmapFont font,font1;

    private TextButton.TextButtonStyle rightArrowStyle,leftArrowStyle;
    private TextButton.TextButtonStyle chapter1Style,chapter2Style,chapter3Style,chapter4Style,chapter5Style,chapter6Style,
            chapter7Style,chapter8Style,chapter9Style,chapter10Style;
    public TextButton rightArrow,leftArrow;
    public TextButton chapter1,chapter2,chapter3,chapter4,chapter5,chapter6,chapter7,chapter8,chapter9,chapter10;

    private Skin skin;
    private TextureAtlas atlas;
    private FreeTypeFontGenerator generator;

    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private Label act1,act2,act3,act4,act5,act6,act7,act8,act9,act10,level;

    private Lone_Warrior1 game;
    public InputMultiplexer multiplexer;
    private Image image;
    private Texture texture;

    private AudioManager audio;

    public Level4(Lone_Warrior1 game){
        this.game = game;
        viewport=new StretchViewport(Lone_Warrior1.V_Width,Lone_Warrior1.V_Height,new OrthographicCamera());
        atlas=new TextureAtlas();
        atlas=game.getAtlas(5);
        stage=new Stage(viewport,game.batch);

        audio=new AudioManager(game);


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
        stage.addActor(table3);


        skin=new Skin();
        skin.addRegions(atlas);

        Gdx.input.setInputProcessor(stage);

        level=new Label("Level 4",new Label.LabelStyle(font, Color.FIREBRICK));

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
        chapter1Style.down=skin.getDrawable("icondown");
        chapter1Style.font=font1;
        if(game.getPrefs().getBoolean("unlock41")==true){
            chapter1Style.fontColor=Color.BLACK;
            chapter1= new TextButton("Act 1",chapter1Style);}
        else{
            chapter1Style.fontColor=Color.FIREBRICK;
            chapter1= new TextButton("Locked",chapter1Style);}

        chapter2Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter2Style.up= skin.getDrawable("icon");
        chapter2Style.down=skin.getDrawable("icondown");
        chapter2Style.font=font1;
        if(game.getPrefs().getBoolean("unlock42")==true){
            chapter2Style.fontColor=Color.BLACK;
            chapter2= new TextButton("Act 2",chapter2Style);}
        else{
            chapter2Style.fontColor=Color.FIREBRICK;
            chapter2= new TextButton("Locked",chapter2Style);}

        chapter3Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter3Style.up= skin.getDrawable("icon");
        chapter3Style.down=skin.getDrawable("icondown");
        chapter3Style.font=font1;
        if(game.getPrefs().getBoolean("unlock43")==true){
            chapter3Style.fontColor=Color.BLACK;
            chapter3= new TextButton("Act 3",chapter3Style);}
        else{
            chapter3Style.fontColor=Color.FIREBRICK;
            chapter3= new TextButton("Locked",chapter3Style);}

        chapter4Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter4Style.up= skin.getDrawable("icon");
        chapter4Style.down=skin.getDrawable("icondown");
        chapter4Style.font=font1;
        if(game.getPrefs().getBoolean("unlock44")==true){
            chapter4Style.fontColor=Color.BLACK;
            chapter4= new TextButton("Act 4",chapter4Style);}
        else{
            chapter4Style.fontColor=Color.FIREBRICK;
            chapter4= new TextButton("Locked",chapter4Style);}


        chapter5Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter5Style.up= skin.getDrawable("icon");
        chapter5Style.down=skin.getDrawable("icondown");
        chapter5Style.font=font1;
        if(game.getPrefs().getBoolean("unlock45")==true){
            chapter5Style.fontColor=Color.BLACK;
            chapter5= new TextButton("Act 5",chapter5Style);}
        else{
            chapter5Style.fontColor=Color.FIREBRICK;
            chapter5= new TextButton("Locked",chapter5Style);}


        chapter6Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter6Style.up= skin.getDrawable("icon");
        chapter6Style.down=skin.getDrawable("icondown");
        chapter6Style.font=font1;
        if(game.getPrefs().getBoolean("unlock46")==true){
            chapter6Style.fontColor=Color.BLACK;
            chapter6= new TextButton("Act 6",chapter6Style);}
        else{
            chapter6Style.fontColor=Color.FIREBRICK;
            chapter6= new TextButton("Locked",chapter6Style);}

        chapter7Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter7Style.up= skin.getDrawable("icon");
        chapter7Style.down=skin.getDrawable("icondown");
        chapter7Style.font=font1;
        if(game.getPrefs().getBoolean("unlock47")==true){
            chapter7Style.fontColor=Color.BLACK;
            chapter7= new TextButton("Act 7",chapter7Style);}
        else{
            chapter7Style.fontColor=Color.FIREBRICK;
            chapter7= new TextButton("Locked",chapter7Style);}

        chapter8Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter8Style.up= skin.getDrawable("icon");
        chapter8Style.down=skin.getDrawable("icondown");
        chapter8Style.font=font1;
        if(game.getPrefs().getBoolean("unlock48")==true){
            chapter8Style.fontColor=Color.BLACK;
            chapter8= new TextButton("Act 8",chapter8Style);}
        else{
            chapter8Style.fontColor=Color.FIREBRICK;
            chapter8= new TextButton("Locked",chapter8Style);}

        chapter9Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter9Style.up= skin.getDrawable("icon");
        chapter9Style.down=skin.getDrawable("icondown");
        chapter9Style.font=font1;
        if(game.getPrefs().getBoolean("unlock49")==true){
            chapter9Style.fontColor=Color.BLACK;
            chapter9= new TextButton("Act 9",chapter9Style);}
        else{
            chapter9Style.fontColor=Color.FIREBRICK;
            chapter9= new TextButton("Locked",chapter9Style);}

        chapter10Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter10Style.up= skin.getDrawable("icon");
        chapter10Style.down=skin.getDrawable("icondown");
        chapter10Style.font=font1;
        if(game.getPrefs().getBoolean("unlock410")==true){
            chapter10Style.fontColor=Color.BLACK;
            chapter10= new TextButton("Act 10",chapter10Style);}
        else{
            chapter10Style.fontColor=Color.FIREBRICK;
            chapter10= new TextButton("Locked",chapter10Style);}


        act1=new Label("Act 1",new Label.LabelStyle(font1,Color.WHITE));
        act2=new Label("Act 2",new Label.LabelStyle(font1,Color.WHITE));
        act3=new Label("Act 3",new Label.LabelStyle(font1,Color.WHITE));
        act4=new Label("Act 4",new Label.LabelStyle(font1,Color.WHITE));
        act5=new Label("Act 5",new Label.LabelStyle(font1,Color.WHITE));
        act6=new Label("Act 6",new Label.LabelStyle(font1,Color.WHITE));
        act7=new Label("Act 7",new Label.LabelStyle(font1,Color.WHITE));
        act8=new Label("Act 8",new Label.LabelStyle(font1,Color.WHITE));
        act9=new Label("Act 9",new Label.LabelStyle(font1,Color.WHITE));
        act10=new Label("Act 10",new Label.LabelStyle(font1,Color.WHITE));

        table2.add(chapter1).expandX().padTop(100).width(120).height(140);
        table2.add(chapter2).expandX().padTop(100).width(120).height(140);
        table2.add(chapter3).expandX().padTop(100).width(120).height(140);
        table2.add(chapter4).expandX().padTop(100).width(120).height(140);
        table2.add(chapter5).expandX().padTop(100).width(120).height(140);
        table2.row();
        table2.add(chapter6).expandX().padTop(10).width(120).height(140);
        table2.add(chapter7).expandX().padTop(10).width(120).height(140);
        table2.add(chapter8).expandX().padTop(10).width(120).height(140);
        table2.add(chapter9).expandX().padTop(10).width(120).height(140);
        table2.add(chapter10).expandX().padTop(10).width(120).height(140);



        stage.addActor(table);
        stage.addActor(table1);
        stage.addActor(table2);
        game.prefs.flush();


    }

    @Override
    public void create() {

    }

    @Override
    public void show() {
        rightArrow.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                audio.playbSound(0);
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                stage.dispose();
                game.setScreen(new Level5(game));
            }
        });

        leftArrow.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                audio.playbSound(0);
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                stage.dispose();
                game.setScreen(new Level3(game));
            }
        });

        chapter1.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock41")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-1.5f,4,1,50));}
            }
        });

        chapter2.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock42")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-1.7f,4,2,100));}
            }
        });

        chapter3.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock43")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-2f,4,3,150));}
            }
        });


        chapter4.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock44")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-2.3f,4,4,240));}
            }
        });


        chapter5.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock45")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-2.5f,4,5,300));}
            }
        });


        chapter6.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock46")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-2.7f,4,6,350));}
            }
        });

        chapter7.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock47")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-2.9f,4,7,400));}
            }
        });

        chapter8.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock48")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-3.1f,4,8,500));}
            }
        });
        chapter9.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock49")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-3.3f,4,9,550));}
            }
        });

        chapter10.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock410")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-3.5f,4,10,600));}
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
