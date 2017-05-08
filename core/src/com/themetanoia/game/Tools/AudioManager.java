package com.themetanoia.game.Tools;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;
import com.themetanoia.game.Lone_Warrior1;

/**
 * Created by MITHOON on 22-04-2017.
 */
public class AudioManager {
public  Music music;
public Array<Sound> sound;
    public Lone_Warrior1 game;
    public AudioManager(Lone_Warrior1 game){
        this.game=game;
        sound=new Array<Sound>();
        music=game.getMusic();
        for(int i=0;i<5;i++)
        sound.add(game.getSound(i));

    }

    public void playMusic(){
        if(!game.getPrefs().getBoolean("musicoff")){
            music.setLooping(true);
            music.play();}
    }
    public void stopMusic(){
        if(music.isPlaying())
        music.stop();
    }

    public void playSound(int i){
        if(!game.getPrefs().getBoolean("Soundoff"))
            sound.get(i).play();
    }
}
