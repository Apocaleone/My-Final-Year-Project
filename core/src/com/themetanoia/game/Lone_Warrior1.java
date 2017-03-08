package com.themetanoia.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.themetanoia.game.Load_Screens.LoadState;
import com.themetanoia.game.Screens.MenuScreen;
import com.themetanoia.game.Screens.Play_State;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

public class Lone_Warrior1 extends Game {
	public SpriteBatch batch;//common sprite batch for all rendering because batch functions are heavy
	public static final int V_Width=900;//global variable for screen resolution
	public static final int V_Height=416;
	public static final float PPM=100;//to reduce box2d bodies to 100 meters per pixel instead of 1 meter per pixel
	public static float x=100/PPM,y=50/PPM,x1=0,y1=0;//to save the positions of the player and enemy bodies when they get destroyed
	public static final short BIT_GROUND=1;//for collision filtering
	public static final short BIT_RUN=2;
	public static final short BIT_ATTACK=4;
	public static final short BIT_APPROACHING=8;
	public static final short BIT_DEFEATED=16;
	public static LoadState loading;
	public static Array<TextureAtlas> atlas;


	public static TweenManager tweenManager;//for animation manipulation

	
	@Override
	public void create () {
		batch = new SpriteBatch();//sprite batch initialized
		loading=new LoadState();
		atlas=new Array<TextureAtlas>();
		atlas.add(loading.assets.manager.get("MenuButtons.pack",TextureAtlas.class));
		atlas.add(loading.assets.manager.get("berserker.pack",TextureAtlas.class));
		atlas.add(loading.assets.manager.get("Warrior.pack",TextureAtlas.class));
		setScreen(new MenuScreen(this));//setting the menu screen
	}

	@Override
	public void render () {
		super.render();//calling the super class for rendering

	}

	public static TextureAtlas getAtlas(int i){
		return atlas.get(i);

	}
	
	@Override
	public void dispose () {

	}
}
