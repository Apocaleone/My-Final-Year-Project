package com.themetanoia.game.Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
public class Berserker extends Sprite {
    public static World world;
    public static Body berserker,berserkerdefeated;
    private TextureAtlas atlas;
    public Animation approaching,defeated;
    public static float time;
    private TextureRegion berkserkerinit;

    public static int berserkerstate=0;

    public Berserker(World world, Play_State state){
        this.world=world;
        atlas=new TextureAtlas("berserker.pack");

        time=0;

        Array<TextureRegion> frames=new Array<TextureRegion>();
        for(int i=0;i<4;i++)
        {
            frames.add(new TextureRegion(atlas.findRegion("berserker"+i)));
        }
        approaching=new Animation(0.1f,frames);
        frames.clear();
        berkserkerinit=new TextureRegion(atlas.findRegion("berserker1"));
        atlas=new TextureAtlas("falling.pack");
        for(int i=0;i<1;i++)
        {
            frames.add(new TextureRegion(atlas.findRegion("falling"+i)));
        }
        defeated=new Animation(0.1f,frames);
        frames.clear();


        setBounds(0,0,400/ Lone_Warrior1.PPM,250/Lone_Warrior1.PPM);//manipulates the size of sprite
        setRegion(berkserkerinit);
    }

    public void update(float dt){
        if(berserkerstate!=-1){
        setPosition(berserker.getPosition().x-(getWidth()/2)+0.5f,berserker.getPosition().y-((getHeight()/2)+0.1f));
        if(berserkerstate==0&&berserker.getLinearVelocity().x>0)
            berserkerstate=1;
        if(berserkerstate==1&&berserker.getLinearVelocity().x==0){
            Play_State.bodiesToRemove.add(berserker);
            berserkerstate=-1;
        }
       /* else{
        berserkerstate=0;}*/

        setRegion(getFrame(dt));
        }
    }

    public TextureRegion getFrame(float dt){
        TextureRegion region=null;
        //if(berserkerstate==0){
        region=approaching.getKeyFrame(time,true);//}
        if(berserkerstate==1)
            region=defeated.getKeyFrame(time,true);
        time=time+dt;
        return region;

    }

    public void defineBerserker(){
        BodyDef bdef=new BodyDef();
        bdef.position.set((Lone_Warrior1.V_Width/Lone_Warrior1.PPM)*3,100/Lone_Warrior1.PPM );
        bdef.type=BodyDef.BodyType.DynamicBody;
        berserker=world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape War=new PolygonShape();
        War.setAsBox(150/Lone_Warrior1.PPM,100/Lone_Warrior1.PPM);

        fdef.shape=War;
        fdef.restitution=0.2f;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_APPROACHING;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND|Lone_Warrior1.BIT_RUN|Lone_Warrior1.BIT_ATTACK;
        berserker.createFixture(fdef).setUserData("berserker");

    }

    public static void defeatedBerserker(){
        BodyDef bdef=new BodyDef();
        bdef.position.set(berserker.getPosition().x,berserker.getPosition().y );
        bdef.type=BodyDef.BodyType.DynamicBody;
        berserkerdefeated=world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape War=new PolygonShape();
        War.setAsBox(150/Lone_Warrior1.PPM,100/Lone_Warrior1.PPM);

        fdef.shape=War;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_APPROACHING;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND;
        berserkerdefeated.createFixture(fdef).setUserData("berserkerfalling");

    }

}
