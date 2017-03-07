package com.themetanoia.game.Tools;

import com.badlogic.gdx.utils.Array;
import com.themetanoia.game.Characters.Berserker;
import com.themetanoia.game.Characters.Enemies;
import com.themetanoia.game.Characters.Spearman;
import com.themetanoia.game.Characters.Warrior;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Screens.Play_State;

import java.util.Random;

/**
 * Created by MITHUN on 30-01-2017.
 */
public class Spawner {
    public static Array<Berserker> berserkers;
    public static Array<Spearman> spearman;
    public Play_State screen;

    public Spawner(Play_State screen) {
        this.screen=screen;
        berserkers=new Array<Berserker>();
        spearman=new Array<Spearman>();
        for(float i=1f;i<2f;i++){
            berserkers.add(new Berserker(screen,(Lone_Warrior1.V_Width/Lone_Warrior1.PPM)*i,100/Lone_Warrior1.PPM ));
        }
       /* for(float j=3f;j<5f;j+=0.5f){
            spearman.add(new Spearman(screen,(Lone_Warrior1.V_Width/Lone_Warrior1.PPM)*j,100/Lone_Warrior1.PPM ));
        }*/

    }

    public void spawn(){
        Random random = new Random();
        int a = random.nextInt(2) + 1;

        switch(a){
            case 1:
                berserkers.add(new Berserker(screen,(Lone_Warrior1.V_Width/Lone_Warrior1.PPM)+ Lone_Warrior1.x,100/Lone_Warrior1.PPM ));
                break;
            case 2:
                spearman.add(new Spearman(screen,(Lone_Warrior1.V_Width/Lone_Warrior1.PPM)+ Lone_Warrior1.x,100/Lone_Warrior1.PPM ));

        }
    }

    public Array<Berserker> getBerserkers() {
        return berserkers;
    }
    public Array<Spearman> getSpearman() {
        return spearman;
    }
    public Array<Enemies> getEnemies(){
        Array<Enemies> enemies=new Array<Enemies>();
        enemies.addAll(berserkers);
        enemies.addAll(spearman);
        return enemies;
    }
}
