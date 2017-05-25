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
            System.out.println("berserker attacked");
            if(fa.getUserData()!=null&&fa.getUserData().equals("warriormegapunch")){
                System.out.println("berserker lost!");
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                Gdx.input.vibrate(500);
                fb.getBody().applyForceToCenter(260,100,true);
                Hud.addScore(2);
            }
            else{
                System.out.println("berserker wins");
                if(!fa.getUserData().equals("Ground")&&!fa.getUserData().equals("berserker1")){
                    Play_State.bodiesToRemove.add(fa.getBody());
                    Warrior.posture=-1;}
            }
        }

        if(fa.getUserData()!=null&&fa.getUserData().equals("berserker1")){
            System.out.println("berserker attacked");
            if(fb.getUserData()!=null&&fb.getUserData().equals("warriormegapunch")){
                System.out.println("berserker lost!");
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fa.getBody().applyForceToCenter(260,100,true);
                Gdx.input.vibrate(500);
                Hud.addScore(2);
            }
            else{
                System.out.println("berserker wins");
                if(!fb.getUserData().equals("Ground")&&!fb.getUserData().equals("berserker1")){
                    Play_State.bodiesToRemove.add(fa.getBody());
                    Warrior.posture=-1;}
            }
        }


        //SPEARMAN
        if(fb.getUserData()!=null&&fb.getUserData().equals("spearman1")){
            System.out.println("spearman attacked");
            if(fa.getUserData()!=null&&fa.getUserData().equals("warriorhighkick")){
                System.out.println("spearman lost!");
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fb.getBody().applyForceToCenter(260,100,true);
                Gdx.input.vibrate(500);
                //Lone_Warrior1.sound.play();
                Hud.addScore(2);
            }
            else{
                System.out.println("spearman wins");
                //fa.getBody().applyForceToCenter(-200,0,true);
                if(!fa.getUserData().equals("Ground")&&!fa.getUserData().equals("spearman1")){
                Play_State.bodiesToRemove.add(fa.getBody());
                    Warrior.posture=-1;}
            }
        }

        if(fa.getUserData()!=null&&fa.getUserData().equals("spearman1")){
            System.out.println("berserker attacked");
            if(fb.getUserData()!=null&&fb.getUserData().equals("warriorhighkick")){
                System.out.println("berserker lost!");
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fa.getBody().applyForceToCenter(260,100,true);
                //Lone_Warrior1.sound.play();
                Gdx.input.vibrate(500);
                Hud.addScore(2);
            }
            else{
                System.out.println("berserker wins");
                //fb.getBody().applyForceToCenter(-200,0,true);
                if(!fb.getUserData().equals("Ground")&&!fb.getUserData().equals("spearman1")){
                Play_State.bodiesToRemove.add(fb.getBody());
                Warrior.posture=-1;}
            }
        }


        //CRAWLER
        if(fb.getUserData()!=null&&fb.getUserData().equals("crawler1")){
            System.out.println("spearman attacked");
            if(fa.getUserData()!=null&&fa.getUserData().equals("warriorgroundpunch")){
                System.out.println("spearman lost!");
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fb.getBody().applyForceToCenter(200,100,true);
                Gdx.input.vibrate(500);
                Hud.addScore(2);
            }
            else{
                System.out.println("spearman wins");
                //fa.getBody().applyForceToCenter(-200,0,true);
                if(!fa.getUserData().equals("Ground")&&!fa.getUserData().equals("crawler1")){
                    Play_State.bodiesToRemove.add(fa.getBody());
                    Warrior.posture=-1;}
            }
        }

        if(fa.getUserData()!=null&&fa.getUserData().equals("crawler1")){
            System.out.println("berserker attacked");
            if(fb.getUserData()!=null&&fb.getUserData().equals("warriorgroundpunch")){
                System.out.println("berserker lost!");
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fa.getBody().applyForceToCenter(200,100,true);
                Gdx.input.vibrate(500);
                Hud.addScore(2);
            }
            else{
                System.out.println("berserker wins");
                //fb.getBody().applyForceToCenter(-200,0,true);
                if(!fb.getUserData().equals("Ground")&&!fb.getUserData().equals("crawler1")){
                    Play_State.bodiesToRemove.add(fb.getBody());
                    Warrior.posture=-1;}
            }
        }

        //ARMY
        if(fb.getUserData()!=null&&fb.getUserData().equals("army1")){
            System.out.println("spearman attacked");
            if(fa.getUserData()!=null&&fa.getUserData().equals("warriormultikick")){
                System.out.println("spearman lost!");
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fb.getBody().applyForceToCenter(200,100,true);
                Gdx.input.vibrate(500);
                Hud.addScore(2);
            }
            else{
                System.out.println("spearman wins");
                //fa.getBody().applyForceToCenter(-200,0,true);
                if(!fa.getUserData().equals("Ground")&&!fa.getUserData().equals("army1")){
                    Play_State.bodiesToRemove.add(fa.getBody());
                    Warrior.posture=-1;}
            }
        }

        if(fa.getUserData()!=null&&fa.getUserData().equals("army1")){
            System.out.println("berserker attacked");
            if(fb.getUserData()!=null&&fb.getUserData().equals("warriormultikick")){
                System.out.println("berserker lost!");
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fa.getBody().applyForceToCenter(200,100,true);
                Gdx.input.vibrate(500);
                Hud.addScore(2);
            }
            else{
                System.out.println("berserker wins");
                //fb.getBody().applyForceToCenter(-200,0,true);
                if(!fb.getUserData().equals("Ground")&&!fb.getUserData().equals("army1")){
                    Play_State.bodiesToRemove.add(fb.getBody());
                    Warrior.posture=-1;}
            }
        }



        //GHOST
        if(fb.getUserData()!=null&&fb.getUserData().equals("ghost1")){
            System.out.println("spearman attacked");
            if(fa.getUserData()!=null&&fa.getUserData().equals("warriorexorcize")){
                System.out.println("spearman lost!");
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fb.getBody().applyForceToCenter(200,100,true);
                Gdx.input.vibrate(500);
                Hud.addScore(2);
            }
            else{
                System.out.println("spearman wins");
                //fa.getBody().applyForceToCenter(-200,0,true);
                if(!fa.getUserData().equals("Ground")&&!fa.getUserData().equals("ghost1")){
                    Play_State.bodiesToRemove.add(fa.getBody());
                    Warrior.posture=-1;}
            }
        }

        if(fa.getUserData()!=null&&fa.getUserData().equals("ghost1")){
            System.out.println("berserker attacked");
            if(fb.getUserData()!=null&&fb.getUserData().equals("warriorexorcize")){
                System.out.println("berserker lost!");
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fa.getBody().applyForceToCenter(200,100,true);
                Gdx.input.vibrate(500);
                Hud.addScore(2);
            }
            else{
                System.out.println("berserker wins");
                //fb.getBody().applyForceToCenter(-200,0,true);
                if(!fb.getUserData().equals("Ground")&&!fb.getUserData().equals("ghost1")){
                    Play_State.bodiesToRemove.add(fb.getBody());
                    Warrior.posture=-1;}
            }
        }


        //LOCUSTS
        if(fb.getUserData()!=null&&fb.getUserData().equals("locusts1")){
            System.out.println("spearman attacked");
            if(fa.getUserData()!=null&&fa.getUserData().equals("warriorhurricanebreath")){
                System.out.println("spearman lost!");
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fb.getBody().applyForceToCenter(200,100,true);
                Gdx.input.vibrate(500);
                Hud.addScore(2);
            }
            else{
                System.out.println("spearman wins");
                //fa.getBody().applyForceToCenter(-200,0,true);
                if(!fa.getUserData().equals("Ground")&&!fa.getUserData().equals("locusts1")){
                    Play_State.bodiesToRemove.add(fa.getBody());
                    Warrior.posture=-1;}
            }
        }

        if(fa.getUserData()!=null&&fa.getUserData().equals("locusts1")){
            System.out.println("berserker attacked");
            if(fb.getUserData()!=null&&fb.getUserData().equals("warriorhurricanebreath")){
                System.out.println("berserker lost!");
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fa.getBody().applyForceToCenter(200,100,true);
                Gdx.input.vibrate(500);
                Hud.addScore(2);
            }
            else{
                System.out.println("berserker wins");
                //fb.getBody().applyForceToCenter(-200,0,true);
                if(!fb.getUserData().equals("Ground")&&!fb.getUserData().equals("locusts1")){
                    Play_State.bodiesToRemove.add(fb.getBody());
                    Warrior.posture=-1;}
            }
        }

        //MACEMAN
        if(fb.getUserData()!=null&&fb.getUserData().equals("maceman1")){
            System.out.println("spearman attacked");
            if(fa.getUserData()!=null&&fa.getUserData().equals("warriorlowkick")){
                System.out.println("spearman lost!");
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fb.getBody().applyForceToCenter(200,100,true);
                Gdx.input.vibrate(500);
                Hud.addScore(2);
                 
            }
            else{
                System.out.println("spearman wins");
                //fa.getBody().applyForceToCenter(-200,0,true);
                if(!fa.getUserData().equals("Ground")&&!fa.getUserData().equals("maceman1")){
                    Play_State.bodiesToRemove.add(fa.getBody());
                    Warrior.posture=-1;}
            }
        }

        if(fa.getUserData()!=null&&fa.getUserData().equals("maceman1")){
            System.out.println("berserker attacked");
            if(fb.getUserData()!=null&&fb.getUserData().equals("warriorlowkick")){
                System.out.println("berserker lost!");
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fa.getBody().applyForceToCenter(200,100,true);
                Gdx.input.vibrate(500);
                Hud.addScore(2);
            }
            else{
                System.out.println("berserker wins");
                //fb.getBody().applyForceToCenter(-200,0,true);
                if(!fb.getUserData().equals("Ground")&&!fb.getUserData().equals("maceman1")){
                    Play_State.bodiesToRemove.add(fb.getBody());
                    Warrior.posture=-1;}
            }
        }

        //TRIDENTMAN
        if(fb.getUserData()!=null&&fb.getUserData().equals("tridentman1")){
            System.out.println("spearman attacked");
            if(fa.getUserData()!=null&&fa.getUserData().equals("warriorspinpunch")){
                System.out.println("spearman lost!");
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fb.getBody().applyForceToCenter(200,100,true);
                Gdx.input.vibrate(500);
                Hud.addScore(2);
            }
            else{
                System.out.println("spearman wins");
                //fa.getBody().applyForceToCenter(-200,0,true);
                if(!fa.getUserData().equals("Ground")&&!fa.getUserData().equals("tridentman1")){
                    Play_State.bodiesToRemove.add(fa.getBody());
                    Warrior.posture=-1;}
            }
        }

        if(fa.getUserData()!=null&&fa.getUserData().equals("tridentman1")){
            System.out.println("berserker attacked");
            if(fb.getUserData()!=null&&fb.getUserData().equals("warriorspinpunch")){
                System.out.println("berserker lost!");
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(200,100,true);
                fa.getBody().applyForceToCenter(200,100,true);
                Gdx.input.vibrate(500);
                Hud.addScore(2);
            }
            else{
                System.out.println("berserker wins");
                //fb.getBody().applyForceToCenter(-200,0,true);
                if(!fb.getUserData().equals("Ground")&&!fb.getUserData().equals("tridentman1")){
                    Play_State.bodiesToRemove.add(fb.getBody());
                    Warrior.posture=-1;}
            }
        }










        /*if(fa.getUserData()!=null&&fa.getUserData().equals("warrior")){
            if(fb.getUserData()!=null&&fb.getUserData().equals("berserker1")){
                System.out.println("warrior attacked by enemy");
                System.out.println("berserker Attacks!");
                Warrior.hero.applyForceToCenter(-500,0,true);
            }
            if (fb.getUserData() != null && fb.getUserData().equals("spearman1")){
                System.out.println("warrior attacked by spearman1");
                Warrior.hero.applyForceToCenter(-200,0,true);

            }
        }
        if(fb.getUserData()!=null&&fb.getUserData().equals("warrior")){
            if(fa.getUserData()!=null&&fa.getUserData().equals("berserker1")){
                System.out.println("warrior attacked by enemy");
                System.out.println("berserker Attacks!");
                Warrior.hero.applyForceToCenter(-500,0,true);
            }
            if (fa.getUserData() != null && fa.getUserData().equals("spearman1")){
                System.out.println("warrior attacked by spearman1");
                Warrior.hero.applyForceToCenter(-200,0,true);

            }
        }*/

        if(fa.getUserData()!=null&&fa.getUserData().equals("warriorreturn")){
            System.out.println("Warriorreturning");
        }
        if(fb.getUserData()!=null&&fb.getUserData().equals("warriorreturn")){
            System.out.println("Warriorreturning");
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
