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
        manager.load("berserker.pack", TextureAtlas.class);
        manager.load("Warrior/Warrior.pack",TextureAtlas.class);
        manager.load("Spearman.pack",TextureAtlas.class);
        manager.load("Enemies/Enemies.pack",TextureAtlas.class);
        manager.load("Levels/Levels.pack",TextureAtlas.class);
        manager.load("Tutorials/Tutorials.pack",TextureAtlas.class);

        manager.load("Music/Bgmusic.ogg", Music.class);
        manager.load("Music/attack.wav", Sound.class);
        manager.load("Music/PUNCH.wav", Sound.class);
        manager.load("Music/punch1.wav", Sound.class);
        manager.load("Music/punch2.wav", Sound.class);
        manager.load("Music/slap.wav", Sound.class);

        manager.setLoader(TiledMap.class,new TmxMapLoader(new InternalFileHandleResolver()));
        manager.load("background2.tmx",TiledMap.class);
        manager.load("bg.tmx",TiledMap.class);
    }

    public void dispose(){
        manager.dispose();
    }
}
