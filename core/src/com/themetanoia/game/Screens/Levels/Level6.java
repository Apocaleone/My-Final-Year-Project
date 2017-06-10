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
 * Created by MITHOON on 11-06-2017.
 */
public class Level6 extends Game implements Screen {
    private Stage stage;
    private Viewport viewport;
    private BitmapFont font,font1;

    private TextButton.TextButtonStyle rightArrowStyle,leftArrowStyle;
    private TextButton.TextButtonStyle chapter1Style,chapter2Style,chapter3Style,chapter4Style,chapter5Style,chapter6Style,
            chapter7Style,chapter8Style,chapter9Style,chapter10Style,chapter11Style,chapter12Style,chapter13Style,
            chapter14Style,chapter15Style;//change
    public TextButton rightArrow,leftArrow;
    public TextButton chapter1,chapter2,chapter3,chapter4,chapter5,chapter6,chapter7,chapter8,chapter9,chapter10,chapter11,chapter12,
    chapter13,chapter14,chapter15;
    //change
    private Skin skin;
    private TextureAtlas atlas;
    private FreeTypeFontGenerator generator;

    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private Label act1,act2,act3,act4,act5,act6,act7,act8,act9,act10,act11,act12,level;//change

    private Lone_Warrior1 game;
    public InputMultiplexer multiplexer;
    private Image image;
    private Texture texture;

    private AudioManager audio;
    int levelstate=6;

    public Level6(Lone_Warrior1 game){
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

        level=new Label("Level "+levelstate,new Label.LabelStyle(font, Color.FIREBRICK));//change

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
        if(game.getPrefs().getBoolean("unlock"+levelstate+"1")==true){//change
            chapter1Style.fontColor=Color.BLACK;
            chapter1= new TextButton("Act 1",chapter1Style);}
        else{
            chapter1Style.fontColor=Color.FIREBRICK;
            chapter1= new TextButton("Locked",chapter1Style);}

        chapter2Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter2Style.up= skin.getDrawable("icon");
        chapter2Style.down=skin.getDrawable("icondown");
        chapter2Style.font=font1;
        if(game.getPrefs().getBoolean("unlock"+levelstate+"2")==true){
            chapter2Style.fontColor=Color.BLACK;
            chapter2= new TextButton("Act 2",chapter2Style);}
        else{
            chapter2Style.fontColor=Color.FIREBRICK;
            chapter2= new TextButton("Locked",chapter2Style);}

        chapter3Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter3Style.up= skin.getDrawable("icon");
        chapter3Style.down=skin.getDrawable("icondown");
        chapter3Style.font=font1;
        if(game.getPrefs().getBoolean("unlock"+levelstate+"3")==true){
            chapter3Style.fontColor=Color.BLACK;
            chapter3= new TextButton("Act 3",chapter3Style);}
        else{
            chapter3Style.fontColor=Color.FIREBRICK;
            chapter3= new TextButton("Locked",chapter3Style);}

        chapter4Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter4Style.up= skin.getDrawable("icon");
        chapter4Style.down=skin.getDrawable("icondown");
        chapter4Style.font=font1;
        if(game.getPrefs().getBoolean("unlock"+levelstate+"4")==true){
            chapter4Style.fontColor=Color.BLACK;
            chapter4= new TextButton("Act 4",chapter4Style);}
        else{
            chapter4Style.fontColor=Color.FIREBRICK;
            chapter4= new TextButton("Locked",chapter4Style);}


        chapter5Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter5Style.up= skin.getDrawable("icon");
        chapter5Style.down=skin.getDrawable("icondown");
        chapter5Style.font=font1;
        if(game.getPrefs().getBoolean("unlock"+levelstate+"5")==true){
            chapter5Style.fontColor=Color.BLACK;
            chapter5= new TextButton("Act 5",chapter5Style);}
        else{
            chapter5Style.fontColor=Color.FIREBRICK;
            chapter5= new TextButton("Locked",chapter5Style);}


        chapter6Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter6Style.up= skin.getDrawable("icon");
        chapter6Style.down=skin.getDrawable("icondown");
        chapter6Style.font=font1;
        if(game.getPrefs().getBoolean("unlock"+levelstate+"6")==true){
            chapter6Style.fontColor=Color.BLACK;
            chapter6= new TextButton("Act 6",chapter6Style);}
        else{
            chapter6Style.fontColor=Color.FIREBRICK;
            chapter6= new TextButton("Locked",chapter6Style);}

        chapter7Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter7Style.up= skin.getDrawable("icon");
        chapter7Style.down=skin.getDrawable("icondown");
        chapter7Style.font=font1;
        if(game.getPrefs().getBoolean("unlock"+levelstate+"7")==true){
            chapter7Style.fontColor=Color.BLACK;
            chapter7= new TextButton("Act 7",chapter7Style);}
        else{
            chapter7Style.fontColor=Color.FIREBRICK;
            chapter7= new TextButton("Locked",chapter7Style);}

        chapter8Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter8Style.up= skin.getDrawable("icon");
        chapter8Style.down=skin.getDrawable("icondown");
        chapter8Style.font=font1;
        if(game.getPrefs().getBoolean("unlock"+levelstate+"8")==true){
            chapter8Style.fontColor=Color.BLACK;
            chapter8= new TextButton("Act 8",chapter8Style);}
        else{
            chapter8Style.fontColor=Color.FIREBRICK;
            chapter8= new TextButton("Locked",chapter8Style);}

        chapter9Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter9Style.up= skin.getDrawable("icon");
        chapter9Style.down=skin.getDrawable("icondown");
        chapter9Style.font=font1;
        if(game.getPrefs().getBoolean("unlock"+levelstate+"9")==true){
            chapter9Style.fontColor=Color.BLACK;
            chapter9= new TextButton("Act 9",chapter9Style);}
        else{
            chapter9Style.fontColor=Color.FIREBRICK;
            chapter9= new TextButton("Locked",chapter9Style);}

        chapter10Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter10Style.up= skin.getDrawable("icon");
        chapter10Style.down=skin.getDrawable("icondown");
        chapter10Style.font=font1;
        if(game.getPrefs().getBoolean("unlock"+levelstate+"10")==true){
            chapter10Style.fontColor=Color.BLACK;
            chapter10= new TextButton("Act 10",chapter10Style);}
        else{
            chapter10Style.fontColor=Color.FIREBRICK;
            chapter10= new TextButton("Locked",chapter10Style);}

        chapter11Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter11Style.up= skin.getDrawable("icon");
        chapter11Style.down=skin.getDrawable("icondown");
        chapter11Style.font=font1;
        if(game.getPrefs().getBoolean("unlock"+levelstate+"11")==true){
            chapter11Style.fontColor=Color.BLACK;
            chapter11= new TextButton("Act 11",chapter11Style);}
        else{
            chapter11Style.fontColor=Color.FIREBRICK;
            chapter11= new TextButton("Locked",chapter11Style);}

        chapter12Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter12Style.up= skin.getDrawable("icon");
        chapter12Style.down=skin.getDrawable("icondown");
        chapter12Style.font=font1;
        if(game.getPrefs().getBoolean("unlock"+levelstate+"12")==true){
            chapter12Style.fontColor=Color.BLACK;
            chapter12= new TextButton("Act 12",chapter12Style);}
        else{
            chapter12Style.fontColor=Color.FIREBRICK;
            chapter12= new TextButton("Locked",chapter12Style);}

        chapter13Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter13Style.up= skin.getDrawable("icon");
        chapter13Style.down=skin.getDrawable("icondown");
        chapter13Style.font=font1;
        if(game.getPrefs().getBoolean("unlock"+levelstate+"13")==true){
            chapter13Style.fontColor=Color.BLACK;
            chapter13= new TextButton("Act 13",chapter13Style);}
        else{
            chapter13Style.fontColor=Color.FIREBRICK;
            chapter13= new TextButton("Locked",chapter13Style);}

        chapter14Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter14Style.up= skin.getDrawable("icon");
        chapter14Style.down=skin.getDrawable("icondown");
        chapter14Style.font=font1;
        if(game.getPrefs().getBoolean("unlock"+levelstate+"14")==true){
            chapter14Style.fontColor=Color.BLACK;
            chapter14= new TextButton("Act 14",chapter14Style);}
        else{
            chapter14Style.fontColor=Color.FIREBRICK;
            chapter14= new TextButton("Locked",chapter14Style);}

        chapter15Style=new TextButton.TextButtonStyle();            //button 1 properties
        chapter15Style.up= skin.getDrawable("icon");
        chapter15Style.down=skin.getDrawable("icondown");
        chapter15Style.font=font1;
        if(game.getPrefs().getBoolean("unlock"+levelstate+"15")==true){
            chapter15Style.fontColor=Color.BLACK;
            chapter15= new TextButton("Act 15",chapter15Style);}
        else{
            chapter15Style.fontColor=Color.FIREBRICK;
            chapter15= new TextButton("Locked",chapter15Style);}


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
        act11=new Label("Act 11",new Label.LabelStyle(font1,Color.WHITE));
        act12=new Label("Act 12",new Label.LabelStyle(font1,Color.WHITE));



        table2.add(chapter1).expandX().padTop(100).width(120).height(140);
        table2.add(chapter2).expandX().padTop(100).width(120).height(140);
        table2.add(chapter3).expandX().padTop(100).width(120).height(140);
        table2.add(chapter4).expandX().padTop(100).width(120).height(140);
        table2.add(chapter5).expandX().padTop(100).width(120).height(140);
        table2.row();
        table2.add(chapter6).expandX().width(120).height(140);
        table2.add(chapter7).expandX().width(120).height(140);
        table2.add(chapter8).expandX().width(120).height(140);
        table2.add(chapter9).expandX().width(120).height(140);
        table2.add(chapter10).expandX().width(120).height(140);
        table2.row();
        table2.add(chapter11).expandX().width(120).height(140);
        table2.add(chapter12).expandX().width(120).height(140);
        table2.add(chapter13).expandX().width(120).height(140);
        table2.add(chapter14).expandX().width(120).height(140);
        table2.add(chapter15).expandX().width(120).height(140);






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
                audio.playbSound(0);
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                stage.dispose();
                game.setScreen(new Level7(game));

            }
        });

        leftArrow.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                audio.playbSound(0);
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                stage.dispose();
                game.setScreen(new Level5(game));
            }
        });

        chapter1.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock"+levelstate+"1")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-1.5f,levelstate,1,50));}
            }
        });

        chapter2.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock"+levelstate+"2")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-1.7f,levelstate,2,100));}
            }
        });

        chapter3.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock"+levelstate+"3")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-2f,levelstate,3,150));}
            }
        });


        chapter4.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock"+levelstate+"4")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-2.3f,levelstate,4,240));}
            }
        });


        chapter5.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock"+levelstate+"5")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-2.5f,levelstate,5,300));}
            }
        });


        chapter6.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock"+levelstate+"6")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-2.7f,levelstate,6,350));}
            }
        });

        chapter7.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock"+levelstate+"7")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-2.9f,levelstate,7,400));}
            }
        });

        chapter8.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock"+levelstate+"8")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-3.1f,levelstate,8,500));}
            }
        });
        chapter9.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock"+levelstate+"9")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-3.3f,levelstate,9,550));}
            }
        });

        chapter10.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock"+levelstate+"10")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-3.5f,levelstate,10,600));}
            }
        });

        chapter11.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock"+levelstate+"11")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-3.5f,levelstate,11,600));}
            }
        });

        chapter12.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock"+levelstate+"12")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-3.5f,levelstate,12,600));}
            }
        });

        chapter13.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock"+levelstate+"13")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-3.5f,levelstate,13,600));}
            }
        });

        chapter14.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock"+levelstate+"14")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-3.5f,levelstate,14,600));}
            }
        });

        chapter15.addListener(new InputListener(){           //Button properties!
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                if(game.getPrefs().getBoolean("unlock"+levelstate+"15")==true){
                    audio.playbSound(1);
                    stage.dispose();
                    game.setScreen(new StoryView(game,-3.5f,levelstate,15,600));}
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

