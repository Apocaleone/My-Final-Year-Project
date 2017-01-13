package com.themetanoia.game.Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Screens.Play_State;

/**
 * Created by MITHUN on 12-01-2017.
 */
public class Enemies extends Sprite {
    public World world;
    public static Body berserker;
    public Animation approaching;
    public static float time;
    private TextureRegion berkserkerinit;
    boolean side;

    public Enemies(World world, Play_State state){
        super(state.getAtlas().findRegion("running0"));
        this.world=world;

        time=0;
        side=true;

        Array<TextureRegion> frames=new Array<TextureRegion>();
        for(int i=0;i<6;i++)
        {
            frames.add(new TextureRegion(state.getAtlas().findRegion("running"+i)));
        }
        approaching=new Animation(0.1f,frames);
        frames.clear();
        defineBerserker();

        berkserkerinit=new TextureRegion(state.getAtlas().findRegion("running0"));
        setBounds(0,0,100/ Lone_Warrior1.PPM,100/Lone_Warrior1.PPM);
        setRegion(berkserkerinit);
    }

    public void update(float dt){
        setPosition(berserker.getPosition().x-getWidth()/2,berserker.getPosition().y-getHeight()/2);

        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        TextureRegion region;
        region=approaching.getKeyFrame(time,true);
        if((berserker.getLinearVelocity().x<0||!side)&&!region.isFlipX())
        {
            region.flip(true,false);
            side=false;
        }
        time=time+dt;
        return region;

    }

    public void defineBerserker(){
        BodyDef bdef=new BodyDef();
        bdef.position.set((Lone_Warrior1.V_Width/Lone_Warrior1.PPM)*2,100/Lone_Warrior1.PPM );
        bdef.type=BodyDef.BodyType.DynamicBody;
        berserker=world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape War=new PolygonShape();
        War.setAsBox(50/Lone_Warrior1.PPM,45/Lone_Warrior1.PPM);

        fdef.shape=War;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_APPROACHING;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND|Lone_Warrior1.BIT_RUN;
        berserker.createFixture(fdef).setUserData("berserker");
        berserker.setUserData(this);

    }

}
