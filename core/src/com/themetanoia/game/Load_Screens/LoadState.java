package com.themetanoia.game.Load_Screens;

/**
 * Created by MITHUN on 13-11-2016.
 */
public class LoadState {
   public static PlayStateAssetmanager assets;

    public LoadState(){
        assets=new PlayStateAssetmanager();
        assets.load();
        while(!assets.manager.update()){
            System.out.println(assets.manager.getProgress()*100+"%");
        }
    }
}
