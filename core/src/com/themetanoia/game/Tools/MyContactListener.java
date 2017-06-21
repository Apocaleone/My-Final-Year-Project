package com.themetanoia.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.themetanoia.game.Characters.Berserker;
import com.themetanoia.game.Characters.Spearman;
import com.themetanoia.game.Characters.Warrior;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Screen_Elements.Hud;
import com.themetanoia.game.Screens.Play_State;

/**
 * Created by MITHUN on 13-01-2017.
 */
public class MyContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fa=contact.getFixtureA();
        Fixture fb=contact.getFixtureB();

        //BERSERKER
        if(fb.getUserData()!=null&&fb.getUserData().equals("berserker1")){
            if(fa.getUserData()!=null&&fa.getUserData().equals("warriormegapunch")){
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                 
                fb.getBody().applyForceToCenter(220,100,true);
                Hud.addScore(2);
            }
            else{
                if(!fa.getUserData().equals("Ground")&&!fa.getUserData().equals("berserker1")){
                    Play_State.bodiesToRemove.add(fa.getBody());
                    Warrior.posture=-1;}
            }
        }

        if(fa.getUserData()!=null&&fa.getUserData().equals("berserker1")){
            if(fb.getUserData()!=null&&fb.getUserData().equals("warriormegapunch")){
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fa.getBody().applyForceToCenter(220,100,true);
                 
                Hud.addScore(2);
            }
            else{
                if(!fb.getUserData().equals("Ground")&&!fb.getUserData().equals("berserker1")){
                    Play_State.bodiesToRemove.add(fa.getBody());
                    Warrior.posture=-1;}
            }
        }


        //SPEARMAN
        if(fb.getUserData()!=null&&fb.getUserData().equals("spearman1")){
            if(fa.getUserData()!=null&&fa.getUserData().equals("warriorhighkick")){
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fb.getBody().applyForceToCenter(260,100,true);
                 
                //Lone_Warrior1.sound.play();
                Hud.addScore(2);
            }
            else{
                //fa.getBody().applyForceToCenter(-200,0,true);
                if(!fa.getUserData().equals("Ground")&&!fa.getUserData().equals("spearman1")){
                Play_State.bodiesToRemove.add(fa.getBody());
                    Warrior.posture=-1;}
            }
        }

        if(fa.getUserData()!=null&&fa.getUserData().equals("spearman1")){
            if(fb.getUserData()!=null&&fb.getUserData().equals("warriorhighkick")){
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fa.getBody().applyForceToCenter(260,100,true);
                //Lone_Warrior1.sound.play();
                 
                Hud.addScore(2);
            }
            else{
                //fb.getBody().applyForceToCenter(-200,0,true);
                if(!fb.getUserData().equals("Ground")&&!fb.getUserData().equals("spearman1")){
                Play_State.bodiesToRemove.add(fb.getBody());
                Warrior.posture=-1;}
            }
        }


        //CRAWLER
        if(fb.getUserData()!=null&&fb.getUserData().equals("crawler1")){
            if(fa.getUserData()!=null&&fa.getUserData().equals("warriorgroundpunch")){
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fb.getBody().applyForceToCenter(200,100,true);
                 
                Hud.addScore(2);
            }
            else{
                //fa.getBody().applyForceToCenter(-200,0,true);
                if(!fa.getUserData().equals("Ground")&&!fa.getUserData().equals("crawler1")){
                    Play_State.bodiesToRemove.add(fa.getBody());
                    Warrior.posture=-1;}
            }
        }

        if(fa.getUserData()!=null&&fa.getUserData().equals("crawler1")){
            if(fb.getUserData()!=null&&fb.getUserData().equals("warriorgroundpunch")){
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fa.getBody().applyForceToCenter(200,100,true);
                 
                Hud.addScore(2);
            }
            else{
                //fb.getBody().applyForceToCenter(-200,0,true);
                if(!fb.getUserData().equals("Ground")&&!fb.getUserData().equals("crawler1")){
                    Play_State.bodiesToRemove.add(fb.getBody());
                    Warrior.posture=-1;}
            }
        }

        //ARMY
        if(fb.getUserData()!=null&&fb.getUserData().equals("army1")){
            if(fa.getUserData()!=null&&fa.getUserData().equals("warriormultikick")){
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fb.getBody().applyForceToCenter(200,100,true);
                 
                Hud.addScore(2);
            }
            else{
                //fa.getBody().applyForceToCenter(-200,0,true);
                if(!fa.getUserData().equals("Ground")&&!fa.getUserData().equals("army1")){
                    Play_State.bodiesToRemove.add(fa.getBody());
                    Warrior.posture=-1;}
            }
        }

        if(fa.getUserData()!=null&&fa.getUserData().equals("army1")){
            if(fb.getUserData()!=null&&fb.getUserData().equals("warriormultikick")){
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fa.getBody().applyForceToCenter(200,100,true);
                 
                Hud.addScore(2);
            }
            else{
                //fb.getBody().applyForceToCenter(-200,0,true);
                if(!fb.getUserData().equals("Ground")&&!fb.getUserData().equals("army1")){
                    Play_State.bodiesToRemove.add(fb.getBody());
                    Warrior.posture=-1;}
            }
        }



        //GHOST
        if(fb.getUserData()!=null&&fb.getUserData().equals("ghost1")){
            if(fa.getUserData()!=null&&fa.getUserData().equals("warriorexorcize")){
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fb.getBody().applyForceToCenter(200,100,true);
                 
                Hud.addScore(2);
            }
            else{
                 //fa.getBody().applyForceToCenter(-200,0,true);
                if(!fa.getUserData().equals("Ground")&&!fa.getUserData().equals("ghost1")){
                    Play_State.bodiesToRemove.add(fa.getBody());
                    Warrior.posture=-1;}
            }
        }

        if(fa.getUserData()!=null&&fa.getUserData().equals("ghost1")){
            if(fb.getUserData()!=null&&fb.getUserData().equals("warriorexorcize")){
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fa.getBody().applyForceToCenter(200,100,true);
                 
                Hud.addScore(2);
            }
            else{
                //fb.getBody().applyForceToCenter(-200,0,true);
                if(!fb.getUserData().equals("Ground")&&!fb.getUserData().equals("ghost1")){
                    Play_State.bodiesToRemove.add(fb.getBody());
                    Warrior.posture=-1;}
            }
        }


        //LOCUSTS
        if(fb.getUserData()!=null&&fb.getUserData().equals("locusts1")){
            if(fa.getUserData()!=null&&fa.getUserData().equals("warriorhurricanebreath")){
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fb.getBody().applyForceToCenter(200,100,true);
                 
                Hud.addScore(2);
            }
            else{
                //fa.getBody().applyForceToCenter(-200,0,true);
                if(!fa.getUserData().equals("Ground")&&!fa.getUserData().equals("locusts1")){
                    Play_State.bodiesToRemove.add(fa.getBody());
                    Warrior.posture=-1;}
            }
        }

        if(fa.getUserData()!=null&&fa.getUserData().equals("locusts1")){
            if(fb.getUserData()!=null&&fb.getUserData().equals("warriorhurricanebreath")){
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fa.getBody().applyForceToCenter(200,100,true);
                 
                Hud.addScore(2);
            }
            else{
                //fb.getBody().applyForceToCenter(-200,0,true);
                if(!fb.getUserData().equals("Ground")&&!fb.getUserData().equals("locusts1")){
                    Play_State.bodiesToRemove.add(fb.getBody());
                    Warrior.posture=-1;}
            }
        }

        //MACEMAN
        if(fb.getUserData()!=null&&fb.getUserData().equals("maceman1")){
            if(fa.getUserData()!=null&&fa.getUserData().equals("warriorlowkick")){
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fb.getBody().applyForceToCenter(200,100,true);
                 
                Hud.addScore(2);
                 
            }
            else{
                //fa.getBody().applyForceToCenter(-200,0,true);
                if(!fa.getUserData().equals("Ground")&&!fa.getUserData().equals("maceman1")){
                    Play_State.bodiesToRemove.add(fa.getBody());
                    Warrior.posture=-1;}
            }
        }

        if(fa.getUserData()!=null&&fa.getUserData().equals("maceman1")){
            if(fb.getUserData()!=null&&fb.getUserData().equals("warriorlowkick")){
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fa.getBody().applyForceToCenter(200,100,true);
                 
                Hud.addScore(2);
            }
            else{
                //fb.getBody().applyForceToCenter(-200,0,true);
                if(!fb.getUserData().equals("Ground")&&!fb.getUserData().equals("maceman1")){
                    Play_State.bodiesToRemove.add(fb.getBody());
                    Warrior.posture=-1;}
            }
        }

        //TRIDENTMAN
        if(fb.getUserData()!=null&&fb.getUserData().equals("tridentman1")){
            if(fa.getUserData()!=null&&fa.getUserData().equals("warriorspinpunch")){
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fb.getBody().applyForceToCenter(200,100,true);
                 
                Hud.addScore(2);
            }
            else{
                //fa.getBody().applyForceToCenter(-200,0,true);
                if(!fa.getUserData().equals("Ground")&&!fa.getUserData().equals("tridentman1")){
                    Play_State.bodiesToRemove.add(fa.getBody());
                    Warrior.posture=-1;}
            }
        }

        if(fa.getUserData()!=null&&fa.getUserData().equals("tridentman1")){
            if(fb.getUserData()!=null&&fb.getUserData().equals("warriorspinpunch")){
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fa.getBody().applyForceToCenter(200,100,true);
                 
                Hud.addScore(2);
            }
            else{
                //fb.getBody().applyForceToCenter(-200,0,true);
                if(!fb.getUserData().equals("Ground")&&!fb.getUserData().equals("tridentman1")){
                    Play_State.bodiesToRemove.add(fb.getBody());
                    Warrior.posture=-1;}
            }
        }










        /*if(fa.getUserData()!=null&&fa.getUserData().equals("warrior")){
            if(fb.getUserData()!=null&&fb.getUserData().equals("berserker1")){
                 ("warrior attacked by enemy");
                 ("berserker Attacks!");
                Warrior.hero.applyForceToCenter(-500,0,true);
            }
            if (fb.getUserData() != null && fb.getUserData().equals("spearman1")){
                 ("warrior attacked by spearman1");
                Warrior.hero.applyForceToCenter(-200,0,true);

            }
        }
        if(fb.getUserData()!=null&&fb.getUserData().equals("warrior")){
            if(fa.getUserData()!=null&&fa.getUserData().equals("berserker1")){
                 ("warrior attacked by enemy");
                 ("berserker Attacks!");
                Warrior.hero.applyForceToCenter(-500,0,true);
            }
            if (fa.getUserData() != null && fa.getUserData().equals("spearman1")){
                 ("warrior attacked by spearman1");
                Warrior.hero.applyForceToCenter(-200,0,true);

            }
        }*/

        if(fa.getUserData()!=null&&fa.getUserData().equals("warriorreturn")){
        }
        if(fb.getUserData()!=null&&fb.getUserData().equals("warriorreturn")){
        }



    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
