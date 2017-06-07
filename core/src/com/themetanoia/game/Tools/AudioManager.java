package com.themetanoia.game.Tools;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;
import com.themetanoia.game.Lone_Warrior1;

/**
 * Created by MITHOON on 22-04-2017.
 */
public class AudioManager {
public  Array<Music> music;
public Array<Sound> sound,grunts,bsounds;
    public Array<Music> fxmusic;
    public Lone_Warrior1 game;
    public AudioManager(Lone_Warrior1 game){
        this.game=game;
        sound=new Array<Sound>();
        grunts=new Array<Sound>();
        bsounds=new Array<Sound>();
        fxmusic=new Array<Music>();
        music=new Array<Music>();
        for(int i=0;i<game.music.size;i++)
            music.add(game.music.get(i));
        for(int i=0;i<game.sound.size;i++)
        sound.add(game.getSound(i));
        for(int i=0;i<game.grunts.size;i++)
            grunts.add(game.grunts.get(i));
        for(int i=0;i<game.bsounds.size;i++)
            bsounds.add(game.bsounds.get(i));
        for(int i=0;i<game.fxmusic.size;i++)
            fxmusic.add(game.fxmusic.get(i));
    }

    public void playMusic(int i){
        if(!game.getPrefs().getBoolean("musicoff")){
            music.get(i).setLooping(true);
            music.get(i).play();}
    }
    public void stopMusic(int i){
        if(music.get(i).isPlaying())
        music.get(i).stop();
    }

    public void playfxMusic(int i){
        if(!game.getPrefs().getBoolean("Soundoff")){
            fxmusic.get(i).setLooping(true);
            fxmusic.get(i).play();}
    }
    public void stopfxMusic(int i){
        if(fxmusic.get(i).isPlaying())
            fxmusic.get(i).stop();
    }

    public void playSound(int i){
        if(!game.getPrefs().getBoolean("Soundoff"))
            sound.get(i).play();
    }


    public void playbSound(int i){
        if(!game.getPrefs().getBoolean("Soundoff"))
            bsounds.get(i).play();
    }

    public void playGruntSound(int i){
        if(!game.getPrefs().getBoolean("Soundoff"))
            grunts.get(i).play();
    }
    public  void stopSound(int i){
        sound.get(i).stop();
    }

    public void stopAll(){
        for(int i=0;i<sound.size;i++){
            sound.get(i).stop();
        }
        for(int i=0;i<grunts.size;i++){
            grunts.get(i).stop();
        }
        for(int i=0;i<fxmusic.size;i++){
            fxmusic.get(i).stop();
        }
        for(int i=0;i<music.size;i++){
            music.get(i).stop();
        }
    }
}
