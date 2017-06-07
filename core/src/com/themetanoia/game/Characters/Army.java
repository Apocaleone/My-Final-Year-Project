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
public class Army extends Enemies {private Body army1;//change
    private TextureAtlas atlas;
    private Animation approaching,defeated;
    private float time;
    private TextureRegion spearmaninit;

    private int armystate =0,previousstate;//change
    Play_State state;
    private AudioManager audio;


    public Army(Play_State state, float x, float y){
        super(state,x,y);
        this.state=state;
        atlas=new TextureAtlas();
        atlas= state.game.getAtlas(4);
        audio=new AudioManager(state.game);

        Array<TextureRegion> frames=new Array<TextureRegion>();
        for(int i=0;i<5;i++)//change
        {
            frames.add(new TextureRegion(atlas.findRegion("Army"+i)));//change
        }
        approaching=new Animation(0.15f,frames);
        frames.clear();
        for(int i=0;i<4;i++)
        {
            frames.add(new TextureRegion(atlas.findRegion("Armydefeated"+i)));//change
        }
        defeated=new Animation(0.1f,frames);
        frames.clear();
        spearmaninit=new TextureRegion(atlas.findRegion("Army0"));//change
        setBounds(getX(),getY(),200/ Lone_Warrior1.PPM,170/Lone_Warrior1.PPM);
        setRegion(spearmaninit);
        time=0f;

    }

    public void update(float dt){

        if(armystate !=-1) {
            setPosition(army1.getPosition().x - getWidth() / 2, (army1.getPosition().y - getHeight() / 2)+13/Lone_Warrior1.PPM);
            if (armystate ==0 && army1.getLinearVelocity().x>0){
                armystate =1;
            audio.playSound(4);
                audio.stopfxMusic(4);
                audio.playGruntSound(4);
            }
            if (armystate ==1 && army1.getLinearVelocity().x==0) {
                Play_State.bodiesToRemove.add(army1);
                Play_State.enemycounter++;
                armystate = -1;

            }
            if(armystate ==0)
                army1.setLinearVelocity( state.getVelocity(),0);
            if((army1.getPosition().x-Lone_Warrior1.x)<((Lone_Warrior1.V_Width)/Lone_Warrior1.PPM)&&armystate==0)
                audio.playfxMusic(4);
            setRegion(getFrame(dt));
        }
    }
    public boolean check(){
        if(armystate !=-1)
            return true;
        else
            return false;
    }
    public TextureRegion getFrame(float dt){

        TextureRegion region=null;
        region=approaching.getKeyFrame(time,true);
        if(armystate ==1)
            region=defeated.getKeyFrame(time);
       /* if(!region.isFlipX())
        region.flip(true,false);*/
        time=armystate==previousstate?time+dt:0;
        previousstate=armystate;
        return region;

    }

    @Override
    public void defineEnemy() {
        BodyDef bdef=new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type=BodyDef.BodyType.DynamicBody;
        army1 =Play_State.world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape War1=new PolygonShape();
        War1.setAsBox(60/Lone_Warrior1.PPM,60/Lone_Warrior1.PPM);

        fdef.shape=War1;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_APPROACHING;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND|Lone_Warrior1.BIT_RUN|Lone_Warrior1.BIT_ATTACK;
        army1.createFixture(fdef).setUserData("army1");//change

    }
}
