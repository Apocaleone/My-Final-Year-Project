package com.themetanoia.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.themetanoia.game.Screens.Play_State;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

public class Lone_Warrior1 extends Game {
	public SpriteBatch batch;
	public static final int V_Width=800;
	public static final int V_Height=416;
	public static final float PPM=100;
	public static float x=100/PPM,y=50/PPM;
	public static final short BIT_GROUND=1;
	public static final short BIT_RUN=2;
	public static final short BIT_ATTACK=4;
	public static final short BIT_APPROACHING=8;


	public static TweenManager tweenManager;

	
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
