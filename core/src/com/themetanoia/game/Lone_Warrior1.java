package com.themetanoia.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.themetanoia.game.Screens.Play_State;

public class Lone_Warrior1 extends Game {
	public SpriteBatch batch;
	public static final int V_Width=800;
	public static final int V_Height=416;
	public static final float PPM=100;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new Play_State(this));
	}

	@Override
	public void render () {
		super.render();

	}
	
	@Override
	public void dispose () {

	}
}
