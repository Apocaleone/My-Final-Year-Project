package com.themetanoia.game.Load_Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Created by MITHUN on 01-11-2016.
 */
public class PlayStateAssetmanager {
    public AssetManager manager=new AssetManager();

    public PlayStateAssetmanager() {
    }

    public void load(){
        manager.load("Buttons/ButtonAtlas.pack", TextureAtlas.class);
        manager.load("MenuButtons.pack", TextureAtlas.class);
        manager.load("Warrior/Warrior.pack",TextureAtlas.class);
        manager.load("Enemies/Enemies.pack",TextureAtlas.class);
        manager.load("Levels/Levels.pack",TextureAtlas.class);
        manager.load("Tutorials/Tutorials.pack",TextureAtlas.class);

        manager.load("Music/music/Bgmusic.ogg", Music.class);
        manager.load("Music/music/angel.ogg", Music.class);        
        manager.load("Music/music/drums.ogg", Music.class);

        
        manager.load("Music/Enemies/Maceman/Swoosh.ogg", Music.class);
        manager.load("Music/Enemies/Crawler/Crawl.ogg", Music.class);
        manager.load("Music/Enemies/Spearman/approaching.ogg", Music.class);
        manager.load("Music/Enemies/Berserker/berserkerrun.ogg", Music.class);
        manager.load("Music/Enemies/Army/armyapp.ogg", Music.class);
        manager.load("Music/Enemies/Ghost/ghostapp.ogg", Music.class);
        manager.load("Music/Enemies/Locusts/locustsapp.ogg", Music.class);
        manager.load("Music/Enemies/Tridentman/panting.ogg", Music.class);

        manager.load("Music/Enemies/Maceman/Impact2.wav", Sound.class);
        manager.load("Music/Enemies/Crawler/Impact3.wav", Sound.class);
        manager.load("Music/Enemies/Spearman/attack.wav", Sound.class);
        manager.load("Music/Enemies/Berserker/boom.wav", Sound.class);
        manager.load("Music/Enemies/Army/multikick.wav", Sound.class);
        manager.load("Music/Enemies/Ghost/electric.wav", Sound.class);
        manager.load("Music/Enemies/Locusts/slap.wav", Sound.class);
        manager.load("Music/Enemies/Tridentman/Impact1.wav", Sound.class);

        manager.load("Music/Enemies/Maceman/macemangrunt.wav", Sound.class);
        manager.load("Music/Enemies/Crawler/crawlergrunt.wav", Sound.class);
        manager.load("Music/Enemies/Spearman/spearmangrunt.wav", Sound.class);
        manager.load("Music/Enemies/Berserker/berserkergrunt.wav", Sound.class);
        manager.load("Music/Enemies/Army/armygrunt.wav", Sound.class);
        manager.load("Music/Enemies/Ghost/ghostgrunt.wav", Sound.class);
        manager.load("Music/Enemies/Locusts/locustsgrunt.wav", Sound.class);
        manager.load("Music/Enemies/Tridentman/tridentmangrunt.wav", Sound.class);

        manager.load("Music/Buttons/click.wav", Sound.class);
        manager.load("Music/Buttons/level.wav", Sound.class);
        manager.load("Music/Buttons/menu.wav", Sound.class);




        manager.setLoader(TiledMap.class,new TmxMapLoader(new InternalFileHandleResolver()));
        manager.load("background2.tmx",TiledMap.class);
        manager.load("bg.tmx",TiledMap.class);
    }

    public void dispose(){
        manager.dispose();
    }
}
