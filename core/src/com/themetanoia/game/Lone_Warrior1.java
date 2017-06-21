package com.themetanoia.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.themetanoia.game.Load_Screens.LoadState;
import com.themetanoia.game.Load_Screens.PlayStateAssetmanager;
import com.themetanoia.game.Screens.MenuScreen;
import com.themetanoia.game.Screens.Play_State;
import com.themetanoia.game.Tools.AdHandler;


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
	public static final short BIT_BLOODSPLATTER=16;
	public LoadState loading;
	public Array<TextureAtlas> atlas;
	public Array<Sound> sound,grunts,bsounds;
	public Array<Music> fxmusic,music;
	public static TiledMap map;

	public Preferences prefs;

	public PlayStateAssetmanager assets;

	public LoadState load;

	public AdHandler handler;
	boolean toggle=false;

	public Lone_Warrior1(AdHandler handler) {
		this.handler = handler;
	}
	public Lone_Warrior1(){
	}

	@Override
	public void create () {
		batch = new SpriteBatch();//sprite batch initialized
        assets=new PlayStateAssetmanager();
	    atlas=new Array<TextureAtlas>();
		music=new Array<Music>();
		sound=new Array<Sound>();
		grunts=new Array<Sound>();
		bsounds=new Array<Sound>();
		fxmusic=new Array<Music>();
		load=new LoadState(this);

		setScreen(load);

		//loading.assets.dispose();
	}

	@Override
	public void render () {
		if(Gdx.input.justTouched()){
		}
		super.render();//calling the super class for rendering

	}

	public TextureAtlas getAtlas(int i){
		return atlas.get(i);

	}
	public Music getMusic(int i){
		return music.get(i);
	}
	public Preferences getPrefs() {
		return prefs;
	}

	public Sound getSound(int i)
	{
		return sound.get(i);

	}

	public void toggleAds(boolean toggle){
		handler.showAds(toggle);
	}


	public void assigner(){
		atlas.add(assets.manager.get("MenuButtons.pack",TextureAtlas.class));//0
		atlas.add(assets.manager.get("Buttons/ButtonAtlas.pack",TextureAtlas.class));//1
		atlas.add(assets.manager.get("Warrior/Warrior.pack",TextureAtlas.class));//2
		atlas.add(assets.manager.get("Tutorials/Tutorials.pack", TextureAtlas.class));//3
		atlas.add(assets.manager.get("Enemies/Enemies.pack",TextureAtlas.class));//4
		atlas.add(assets.manager.get("Levels/Levels.pack",TextureAtlas.class));//5


		music.add(assets.manager.get("Music/music/Bgmusic.ogg",Music.class));//0
		music.add(assets.manager.get("Music/music/angel.ogg",Music.class));//1
		music.add(assets.manager.get("Music/music/drums.ogg",Music.class));//2

		fxmusic.add(assets.manager.get("Music/Enemies/Maceman/Swoosh.ogg", Music.class));//0
		fxmusic.add(assets.manager.get("Music/Enemies/Crawler/Crawl.ogg", Music.class));//1
		fxmusic.add(assets.manager.get("Music/Enemies/Spearman/approaching.ogg", Music.class));//2
		fxmusic.add(assets.manager.get("Music/Enemies/Berserker/berserkerrun.ogg", Music.class));//3
		fxmusic.add(assets.manager.get("Music/Enemies/Army/armyapp.ogg", Music.class));//4
		fxmusic.add(assets.manager.get("Music/Enemies/Ghost/ghostapp.ogg", Music.class));//5
		fxmusic.add(assets.manager.get("Music/Enemies/Locusts/locustsapp.ogg", Music.class));//6
		fxmusic.add(assets.manager.get("Music/Enemies/Tridentman/panting.ogg", Music.class));//7
		fxmusic.add(assets.manager.get("Music/Enemies/Warrior/warriorgrunt.ogg", Music.class));//8



		sound.add(assets.manager.get("Music/Enemies/Maceman/Impact2.wav", Sound.class));//0
		sound.add(assets.manager.get("Music/Enemies/Crawler/Impact3.wav", Sound.class));//1
		sound.add(assets.manager.get("Music/Enemies/Spearman/attack.wav", Sound.class));//2
		sound.add(assets.manager.get("Music/Enemies/Berserker/boom.wav", Sound.class));//3
		sound.add(assets.manager.get("Music/Enemies/Army/multikick.wav", Sound.class));//4
		sound.add(assets.manager.get("Music/Enemies/Ghost/electric.wav", Sound.class));//5
		sound.add(assets.manager.get("Music/Enemies/Locusts/slap.wav", Sound.class));//6
		sound.add(assets.manager.get("Music/Enemies/Tridentman/Impact1.wav", Sound.class));//7

		grunts.add(assets.manager.get("Music/Enemies/Maceman/macemangrunt.wav", Sound.class));//0
		grunts.add(assets.manager.get("Music/Enemies/Crawler/crawlergrunt.wav", Sound.class));//1
		grunts.add(assets.manager.get("Music/Enemies/Spearman/spearmangrunt.wav", Sound.class));//2
		grunts.add(assets.manager.get("Music/Enemies/Berserker/berserkergrunt.wav", Sound.class));//3
		grunts.add(assets.manager.get("Music/Enemies/Army/armygrunt.wav", Sound.class));//4
		grunts.add(assets.manager.get("Music/Enemies/Ghost/ghostgrunt.wav", Sound.class));//5
		grunts.add(assets.manager.get("Music/Enemies/Locusts/locustsgrunt.wav", Sound.class));//6
		grunts.add(assets.manager.get("Music/Enemies/Tridentman/tridentmangrunt.wav", Sound.class));//7

		bsounds.add(assets.manager.get("Music/Buttons/click.wav", Sound.class));//0
		bsounds.add(assets.manager.get("Music/Buttons/level.wav", Sound.class));//1

		prefs=Gdx.app.getPreferences("GameData");

		map=assets.manager.get("bg.tmx");
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
