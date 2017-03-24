package com.themetanoia.game.Tools;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.themetanoia.game.Characters.Berserker;
import com.themetanoia.game.Characters.Spearman;
import com.themetanoia.game.Characters.Warrior;
import com.themetanoia.game.Screens.Play_State;

/**
 * Created by MITHUN on 13-01-2017.
 */
public class MyContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fa=contact.getFixtureA();
        Fixture fb=contact.getFixtureB();

        if(fb.getUserData()!=null&&fb.getUserData().equals("berserker1")){
            System.out.println("berserker attacked");
            if(fa.getUserData()!=null&&fa.getUserData().equals("warriorhighkick")){
                System.out.println("berserker lost!");
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(300,100,true);
                fb.getBody().applyForceToCenter(500,100,true);
            }
            else{
                System.out.println("berserker wins");
                fa.getBody().applyForceToCenter(-500,0,true);
            }
        }

        if(fa.getUserData()!=null&&fa.getUserData().equals("berserker1")){
            System.out.println("berserker attacked");
            if(fb.getUserData()!=null&&fb.getUserData().equals("warriorhighkick")){
                System.out.println("berserker lost!");
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(300,100,true);
                fa.getBody().applyForceToCenter(500,100,true);
            }
            else{
                System.out.println("berserker wins");
                fb.getBody().applyForceToCenter(-500,0,true);
            }
        }



        if(fb.getUserData()!=null&&fb.getUserData().equals("spearman1")){
            System.out.println("spearman attacked");
            if(fa.getUserData()!=null&&fa.getUserData().equals("warriorhighkick")){
                System.out.println("spearman lost!");
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(300,100,true);
                fb.getBody().applyForceToCenter(300,100,true);
            }
            else{
                System.out.println("spearman wins");
                //fa.getBody().applyForceToCenter(-300,0,true);
                if(!fa.getUserData().equals("Ground")){
                Play_State.bodiesToRemove.add(fa.getBody());
                    Warrior.posture=-1;}
            }
        }

        if(fa.getUserData()!=null&&fa.getUserData().equals("spearman1")){
            System.out.println("berserker attacked");
            if(fb.getUserData()!=null&&fb.getUserData().equals("warriorhighkick")){
                System.out.println("berserker lost!");
                //Spawner.berserkers.get(0).berserker1.applyForceToCenter(300,100,true);
                fa.getBody().applyForceToCenter(300,100,true);
            }
            else{
                System.out.println("berserker wins");
                //fb.getBody().applyForceToCenter(-300,0,true);
                if(!fb.getUserData().equals("Ground")){
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
                Warrior.hero.applyForceToCenter(-300,0,true);

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
                Warrior.hero.applyForceToCenter(-300,0,true);

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
