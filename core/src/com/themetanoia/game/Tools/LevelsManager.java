package com.themetanoia.game.Tools;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Screens.Levels.Level1;

/**
 * Created by MITHOON on 05-04-2017.
 */
public class LevelsManager extends Game implements Screen {
    public Lone_Warrior1 game;

    public LevelsManager(Lone_Warrior1 game){
        this.game=game;

    }
    public void levelSelector(){
    game.setScreen(new Level1(game));
    }
    @Override
    public void create() {

    }

    public void summonLevel(){

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void hide() {

    }
}
