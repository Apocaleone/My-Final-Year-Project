package com.themetanoia.game.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.themetanoia.game.Characters.Enemies;
import com.themetanoia.game.Characters.Warrior;

/**
 * Created by MITHUN on 13-01-2017.
 */
public class MyContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fa=contact.getFixtureA();
        Fixture fb=contact.getFixtureB();

        if(fb.getUserData()!=null&&fb.getUserData().equals("warrior1")){
            System.out.println("fb attack");
            if(fa.getUserData()!=null&&fa.getUserData().equals("berserker")){
                System.out.println("fa Attacked!");
                Enemies.berserker.applyForceToCenter(500,0,true);
            }
        }

        if(fa.getUserData()!=null&&fa.getUserData().equals("warrior")){
            System.out.println("fa attacked by enemy");
            if(fb.getUserData()!=null&&fb.getUserData().equals("berserker")){
                System.out.println("fa Attack!");
                Warrior.hero.applyForceToCenter(-500,0,true);
            }
        }

        if(fb.getUserData()!=null&&fb.getUserData().equals("warrior")){
            System.out.println("fb attacked by enemy");
            if(fa.getUserData()!=null&&fa.getUserData().equals("berserker")){
                System.out.println("fa Attack!");
                Warrior.hero.applyForceToCenter(-500,0,true);
            }
        }

        if(fa.getUserData()!=null&&fa.getUserData().equals("warriorreturn")){
            System.out.println("Warriorreturned");
        }
        if(fb.getUserData()!=null&&fb.getUserData().equals("warriorreturn")){
            System.out.println("Warriorreturned");
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
