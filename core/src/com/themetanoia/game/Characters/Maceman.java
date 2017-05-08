package com.themetanoia.game.Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Screens.Play_State;
import com.themetanoia.game.Tools.AudioManager;

/**
 * Created by MITHOON on 27-03-2017.
 */
public class Maceman extends Enemies {private Body maceman1;
    private TextureAtlas atlas;
    private Animation approaching,defeated;
    private float time;
    private TextureRegion spearmaninit;
    private AudioManager audio;

    private int macemanstate =0,previousstate;
    Play_State state;


    public Maceman(Play_State state, float x, float y){
        super(state,x,y);
        this.state=state;
        atlas=new TextureAtlas();
        atlas= state.game.getAtlas(4);
        audio=new AudioManager(state.game);

        Array<TextureRegion> frames=new Array<TextureRegion>();
        for(int i=0;i<4;i++)
        {
            frames.add(new TextureRegion(atlas.findRegion("Maceman"+i)));
        }
        approaching=new Animation(0.15f,frames);
        frames.clear();
        for(int i=0;i<5;i++)
        {
            frames.add(new TextureRegion(atlas.findRegion("Macemandefeated"+i)));
        }
        defeated=new Animation(0.1f,frames);
        frames.clear();
        spearmaninit=new TextureRegion(atlas.findRegion("Maceman0"));
        setBounds(getX(),getY(),200/ Lone_Warrior1.PPM,170/Lone_Warrior1.PPM);
        setRegion(spearmaninit);
        time=0f;

    }

    public void update(float dt){

        if(macemanstate !=-1) {
            if (macemanstate ==0 && maceman1.getLinearVelocity().x>0){
                audio.playSound(2);
                macemanstate =1;}
            if (macemanstate ==1 && maceman1.getLinearVelocity().x==0) {
                Play_State.bodiesToRemove.add(maceman1);
                Play_State.enemycounter++;
                macemanstate = -1;

            }
            if(macemanstate ==0){
                setBounds(0,0,400/Lone_Warrior1.PPM,400/Lone_Warrior1.PPM);
                setPosition(maceman1.getPosition().x - getWidth() / 2, (maceman1.getPosition().y - getHeight() / 2)+130/Lone_Warrior1.PPM);
                maceman1.setLinearVelocity(state.getVelocity(),0);}
            if(macemanstate==1){
                setPosition(maceman1.getPosition().x - getWidth() / 2, (maceman1.getPosition().y - getHeight() / 2)+130/Lone_Warrior1.PPM);
            }
            setRegion(getFrame(dt));
        }
    }
    public boolean check(){
        if(macemanstate !=-1)
            return true;
        else
            return false;
    }
    public TextureRegion getFrame(float dt){
        TextureRegion region=null;
        region=approaching.getKeyFrame(time,true);
        if(macemanstate ==1)
            region=defeated.getKeyFrame(time);
       /* if(!region.isFlipX())
        region.flip(true,false);*/
        time=macemanstate==previousstate?time+dt:0;
        previousstate=macemanstate;
        return region;

    }

    @Override
    public void defineEnemy() {
        BodyDef bdef=new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type=BodyDef.BodyType.DynamicBody;
        maceman1 =Play_State.world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape War1=new PolygonShape();
        War1.setAsBox(60/Lone_Warrior1.PPM,60/Lone_Warrior1.PPM);

        fdef.shape=War1;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_APPROACHING;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND|Lone_Warrior1.BIT_RUN|Lone_Warrior1.BIT_ATTACK;
        maceman1.createFixture(fdef).setUserData("maceman1");

    }
}
