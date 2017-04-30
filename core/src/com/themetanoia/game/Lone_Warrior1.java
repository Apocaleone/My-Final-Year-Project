package com.themetanoia.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.themetanoia.game.Load_Screens.LoadState;
import com.themetanoia.game.Screens.MenuScreen;
import com.themetanoia.game.Screens.Play_State;


public class Lone_Warrior1 extends Game implements Screen {
	public SpriteBatch batch;//common sprite batch for all rendering because batch functions are heavy
	public static final int V_Width=1000;//global variable for screen resolution
	public static final int V_Height=600;
	public static final float PPM=100;//to reduce box2d bodies to 100 meters per pixel instead of 1 meter per pixel
	public static float x=100/PPM,y=50/PPM,x1=0,y1=0;//to save the positions of the player and enemy bodies when they get destroyed
	public static final short BIT_GROUND=1;//for collision filtering
	public static final short BIT_RUN=2;
	public static final short BIT_ATTACK=4;
	public static final short BIT_APPROACHING=8;
	public static final short BIT_DEFEATED=16;
	public static LoadState loading;
	public Array<TextureAtlas> atlas;
	public static Music music;
	public static Sound sound;


	
	@Override
	public void create () {
		batch = new SpriteBatch();//sprite batch initialized
		loading=new LoadState();
		atlas=new Array<TextureAtlas>();
		atlas.add(loading.assets.manager.get("MenuButtons.pack",TextureAtlas.class));//0
		atlas.add(loading.assets.manager.get("ButtonAtlas.pack",TextureAtlas.class));//1
		atlas.add(loading.assets.manager.get("Warrior/Warrior.pack",TextureAtlas.class));//2
		atlas.add(loading.assets.manager.get("Spearman.pack",TextureAtlas.class));//3
		atlas.add(loading.assets.manager.get("Enemies/Enemies.pack",TextureAtlas.class));//4
		atlas.add(loading.assets.manager.get("Levels/Levels.pack",TextureAtlas.class));//5
		music=loading.assets.manager.get("Music/Bgmusic.ogg",Music.class);
		sound=loading.assets.manager.get("Music/attack.wav",Sound.class);
		setScreen(new MenuScreen(this));//setting the menu screen
	}

	@Override
	public void render () {
		super.render();//calling the super class for rendering

	}

	public TextureAtlas getAtlas(int i){
		return atlas.get(i);

	}
	public static Music getMusic(){
		return music;
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

	@Override
	public void dispose () {

	}
}
