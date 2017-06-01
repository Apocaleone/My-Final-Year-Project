package com.themetanoia.game.Worlds;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Screens.Play_State;

/**
 * Created by MITHUN on 01-11-2016.
 */
public class NightWorld {

    public TmxMapLoader backgroundloader;
    public static TiledMap bg;
    public TiledMap bg2;
    public static OrthogonalTiledMapRenderer renderer;
    public static OrthogonalTiledMapRenderer renderer2;
    public static Array<RectangleMapObject> marker;
    SpriteBatch batch;

    public NightWorld(World world, Play_State state){
        backgroundloader= new TmxMapLoader();
        batch=new SpriteBatch();
        bg2=new TiledMap();
        MapLayers layers=bg2.getLayers();
        TiledMapTileLayer layer=new TiledMapTileLayer(1000, 600, 8, 8);
        layers.add(layer);
        bg=backgroundloader.load("background2.tmx");
        bg2=Lone_Warrior1.map;
        renderer=new OrthogonalTiledMapRenderer(bg,1/Lone_Warrior1.PPM);
        renderer2=new OrthogonalTiledMapRenderer(bg2,1/Lone_Warrior1.PPM);
        BodyDef bdef=new BodyDef();
        PolygonShape shape=new PolygonShape();
        FixtureDef fdef=new FixtureDef();
        Body body;

        for(MapObject object : bg.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect= ((RectangleMapObject) object).getRectangle();

            bdef.type=BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/ Lone_Warrior1.PPM,(rect.getY()+rect.getHeight()/2)/Lone_Warrior1.PPM);

            body=world.createBody(bdef);

            shape.setAsBox((rect.getWidth()/2)/Lone_Warrior1.PPM,(rect.getHeight()/2)/Lone_Warrior1.PPM);
            fdef.shape=shape;
            fdef.filter.categoryBits=Lone_Warrior1.BIT_GROUND;
            fdef.filter.maskBits=Lone_Warrior1.BIT_RUN|Lone_Warrior1.BIT_APPROACHING|Lone_Warrior1.BIT_ATTACK;
            Fixture fixture=body.createFixture(fdef);
            fixture.setUserData("Ground");
        }

    }

}
