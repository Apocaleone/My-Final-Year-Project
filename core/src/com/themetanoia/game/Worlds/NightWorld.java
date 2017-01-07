package com.themetanoia.game.Worlds;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.themetanoia.game.Lone_Warrior1;

/**
 * Created by MITHUN on 01-11-2016.
 */
public class NightWorld {
    public NightWorld(World world, TiledMap bg){
        BodyDef bdef=new BodyDef();
        PolygonShape shape=new PolygonShape();
        FixtureDef fdef=new FixtureDef();
        Body body;

        for(MapObject object : bg.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect= ((RectangleMapObject) object).getRectangle();

            bdef.type=BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/ Lone_Warrior1.PPM,(rect.getY()+rect.getHeight()/2)/Lone_Warrior1.PPM);

            body=world.createBody(bdef);

            shape.setAsBox((rect.getWidth()/2)/Lone_Warrior1.PPM,(rect.getHeight()/2)/Lone_Warrior1.PPM);
            fdef.shape=shape;
            body.createFixture(fdef);
        }

    }
}
