package com.themetanoia.game.Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.sun.jndi.ldap.Ber;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Screens.Play_State;

/**
 * Created by MITHOON on 01-03-2017.
 */
public class Berserker extends Enemies {

    public Body berserker1;
    private TextureAtlas atlas;
    public Animation approaching,defeated;
    public static float time;
    private TextureRegion berkserkerinit;

    public int berserkerstate=0;

    public Berserker(){

    }


    public Berserker(Play_State state,float x, float y){
        super(state, x,  y);
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
            setPosition(berserker1.getPosition().x-(getWidth()/2)+0.5f,berserker1.getPosition().y-((getHeight()/2)+0.1f));
            if(berserkerstate==0&&berserker1.getLinearVelocity().x>0)
                berserkerstate=1;
            if(berserkerstate==1&&berserker1.getLinearVelocity().x==0){
                Play_State.bodiesToRemove.add(berserker1);
                Play_State.flag2=true;
                berserkerstate=-1;
            }
            if(berserkerstate==0)
                berserker1.applyForceToCenter(-0.3f,0,true);
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
    public boolean check(){
        if(berserkerstate!=-1)
           return true;
        else
            return false;
    }

    @Override
    public void defineEnemy() {
        BodyDef bdef=new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type=BodyDef.BodyType.DynamicBody;
        berserker1 = Play_State.world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape War=new PolygonShape();
        War.setAsBox(150/Lone_Warrior1.PPM,100/Lone_Warrior1.PPM);

        fdef.shape=War;
        fdef.restitution=0.2f;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_APPROACHING;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND|Lone_Warrior1.BIT_RUN|Lone_Warrior1.BIT_ATTACK;
        berserker1.createFixture(fdef).setUserData("berserker1");//to be checked

    }
    public Body getBerserker1() {
        return berserker1;
    }
}
