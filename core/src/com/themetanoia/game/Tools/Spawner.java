package com.themetanoia.game.Tools;

import com.badlogic.gdx.math.RandomXS128;
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
    public Array<Berserker> berserkers;
    public Array<Spearman> spearman;
    public Play_State screen;

    public Spawner(Play_State screen) {
        this.screen=screen;
        berserkers=new Array<Berserker>();
        spearman=new Array<Spearman>();  
       /* for(float j=3f;j<5f;j+=0.5f){
            spearman.add(new Spearman(screen,(Lone_Warrior1.V_Width/Lone_Warrior1.PPM)*j,100/Lone_Warrior1.PPM ));
        }*/

    }

    public void spawn(){
        Play_State.enemycounter=0;
       for(int i=0;i<3;i++) {
            RandomXS128 random = new RandomXS128();
            System.out.println("Generating random number");
            int a = random.nextInt(1) + 1;

            switch (a) {
                case 1:
                    System.out.println("creating berserker");
                    spearman.add(new Spearman(screen, (Lone_Warrior1.V_Width / Lone_Warrior1.PPM) + (Lone_Warrior1.x+(i*500/Lone_Warrior1.PPM)), 100 / Lone_Warrior1.PPM));
                    break;
                case 2:
                    berserkers.add(new Berserker(screen, (Lone_Warrior1.V_Width / Lone_Warrior1.PPM) + (Lone_Warrior1.x+(i*500/Lone_Warrior1.PPM)), 100 / Lone_Warrior1.PPM));
                    break;

            }
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
