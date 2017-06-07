package com.themetanoia.game.Tools;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Screens.Play_State;

/**
 * Created by MITHOON on 02-06-2017.
 */
public class BloodSplatter {

    public BloodSplatter(){

    }

    public void splatter(){
        Body blood;
        BodyDef bdef=new BodyDef();
        bdef.position.set(5,10);
        bdef.type=BodyDef.BodyType.DynamicBody;
        blood = Play_State.world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        CircleShape War1=new CircleShape();
        War1.setRadius(5/Lone_Warrior1.PPM);

        fdef.shape=War1;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_RUN;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND;
        blood.createFixture(fdef).setUserData("blood");//change

    }
}
