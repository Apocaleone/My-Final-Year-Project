package com.themetanoia.game.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.themetanoia.game.Characters.Berserker;
import com.themetanoia.game.Characters.Spearman;
import com.themetanoia.game.Characters.Warrior;

/**
 * Created by MITHUN on 13-01-2017.
 */
public class MyContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fa=contact.getFixtureA();
        Fixture fb=contact.getFixtureB();

        if(fb.getUserData()!=null&&fb.getUserData().equals("warriorhighkick")){
            System.out.println("warrior attacks");
            if(fa.getUserData()!=null&&fa.getUserData().equals("berserker1")){
                System.out.println("berserker Attacked!");
                Spawner.berserkers.get(0).berserker1.applyForceToCenter(300,100,true);
            }
        }

        if(fa.getUserData()!=null&&fa.getUserData().equals("warriorhighkick")){
            System.out.println("warrior attacks");
            if(fb.getUserData()!=null&&fb.getUserData().equals("berserker1")){
                System.out.println("berserker Attacked!");
                Spawner.berserkers.get(0).berserker1.applyForceToCenter(300,100,true);
            }
        }



        if(fa.getUserData()!=null&&fa.getUserData().equals("warriorlowkick")) {
            System.out.println("warrior attacks");
            if (fb.getUserData() != null && fb.getUserData().equals("spearman1")) {
                System.out.println("spearman1 Attacked!");
                Spawner.spearman.get(0).spearman1.applyForceToCenter(300,100,true);
            }

        }


        if(fa.getUserData()!=null&&fa.getUserData().equals("warrior")){
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
        }

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
