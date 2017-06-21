package com.themetanoia.game.Tools;

import com.badlogic.gdx.Gdx;
import com.themetanoia.game.Lone_Warrior1;

/**
 * Created by MITHOON on 18-06-2017.
 */
public class Effects {
    public Lone_Warrior1 game;

    public Effects(Lone_Warrior1 game) {
    this.game=game;
    }
    public void vibrate(){
        if(!game.getPrefs().getBoolean("vibrateoff")){
            Gdx.input.vibrate(200);
        }
    }
}
