package com.themetanoia.game.Load_Screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Created by MITHUN on 01-11-2016.
 */
public class PlayStateAssetmanager {
    public AssetManager manager=new AssetManager();

    public void load(){
        manager.load("ButtonAtlas.pack", TextureAtlas.class);
        manager.load("WarriorJump.pack", TextureAtlas.class);
    }

    public void dispose(){
        manager.dispose();
    }
}
