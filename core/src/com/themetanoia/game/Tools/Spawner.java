package com.themetanoia.game.Tools;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.themetanoia.game.Characters.Army;
import com.themetanoia.game.Characters.Berserker;
import com.themetanoia.game.Characters.Crawler;
import com.themetanoia.game.Characters.Enemies;
import com.themetanoia.game.Characters.Ghost;
import com.themetanoia.game.Characters.Locusts;
import com.themetanoia.game.Characters.Maceman;
import com.themetanoia.game.Characters.Spearman;
import com.themetanoia.game.Characters.Tridentman;
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
    public Array<Army> army;
    public Array<Crawler> crawlers;
    public Array<Ghost> ghosts;
    public Array<Locusts> locusts;
    public Array<Maceman> macemen;
    public Array<Tridentman> tridentmen;
    public Play_State screen;

    public Spawner(Play_State screen) {
        this.screen=screen;
        berserkers=new Array<Berserker>();
        spearman=new Array<Spearman>();
        army=new Array<Army>();
        crawlers=new Array<Crawler>();
        ghosts=new Array<Ghost>();
        locusts=new Array<Locusts>();
        macemen=new Array<Maceman>();
        tridentmen=new Array<Tridentman>();
       /* for(float j=3f;j<5f;j+=0.5f){
            spearman.add(new Spearman(screen,(Lone_Warrior1.V_Width/Lone_Warrior1.PPM)*j,100/Lone_Warrior1.PPM ));
        }*/

    }

    public void spawn(){
        Play_State.enemycounter=0;
       for(int i=0;i<3;i++) {
            RandomXS128 random = new RandomXS128();
            System.out.println("Generating random number");
            int a =random.nextInt(4) + 1;

            switch (a) {
                case 1:
                    System.out.println("creating berserker");
                    spearman.add(new Spearman(screen, (Lone_Warrior1.V_Width / Lone_Warrior1.PPM) + (Lone_Warrior1.x+(i*500/Lone_Warrior1.PPM)), 100 / Lone_Warrior1.PPM));
                    break;
                case 8:
                    berserkers.add(new Berserker(screen, (Lone_Warrior1.V_Width / Lone_Warrior1.PPM) + (Lone_Warrior1.x+(i*500/Lone_Warrior1.PPM)), 100 / Lone_Warrior1.PPM));
                    break;
                case 7:
                    army.add(new Army(screen, (Lone_Warrior1.V_Width / Lone_Warrior1.PPM) + (Lone_Warrior1.x+(i*500/Lone_Warrior1.PPM)), 100 / Lone_Warrior1.PPM));
                    break;
                case 4:
                    crawlers.add(new Crawler(screen, (Lone_Warrior1.V_Width / Lone_Warrior1.PPM) + (Lone_Warrior1.x+(i*500/Lone_Warrior1.PPM)), 100 / Lone_Warrior1.PPM));
                    break;
                case 5:
                    ghosts.add(new Ghost(screen, (Lone_Warrior1.V_Width / Lone_Warrior1.PPM) + (Lone_Warrior1.x+(i*500/Lone_Warrior1.PPM)), 100 / Lone_Warrior1.PPM));
                    break;
                case 6:
                    locusts.add(new Locusts(screen, (Lone_Warrior1.V_Width / Lone_Warrior1.PPM) + (Lone_Warrior1.x+(i*500/Lone_Warrior1.PPM)), 100 / Lone_Warrior1.PPM));
                    break;
                case 3:
                    macemen.add(new Maceman(screen, (Lone_Warrior1.V_Width / Lone_Warrior1.PPM) + (Lone_Warrior1.x+(i*500/Lone_Warrior1.PPM)), 100 / Lone_Warrior1.PPM));
                    break;
                case 2:
                    tridentmen.add(new Tridentman(screen, (Lone_Warrior1.V_Width / Lone_Warrior1.PPM) + (Lone_Warrior1.x+(i*500/Lone_Warrior1.PPM)), 100 / Lone_Warrior1.PPM));
                    break;
                default:
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
        enemies.addAll(army);
        enemies.addAll(tridentmen);
        enemies.addAll(crawlers);
        enemies.addAll(ghosts);
        enemies.addAll(locusts);
        enemies.addAll(macemen);
        return enemies;
    }
}
