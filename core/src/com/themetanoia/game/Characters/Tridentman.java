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

/**
 * Created by MITHOON on 27-03-2017.
 */
public class Tridentman extends Enemies {private Body tridentman1;//change
    private TextureAtlas atlas;
    private Animation approaching,defeated;
    private float time;
    private TextureRegion spearmaninit;

    private int tridentmanstate =0,previousstate;//change
   public Play_State state;


    public Tridentman(Play_State state, float x, float y){
        super(state,x,y);
        this.state=state;
        atlas=new TextureAtlas();
        atlas= state.game.getAtlas(4);

        Array<TextureRegion> frames=new Array<TextureRegion>();
        for(int i=0;i<3;i++)//change
        {
            frames.add(new TextureRegion(atlas.findRegion("Tridentman"+i)));//change
        }
        approaching=new Animation(0.15f,frames);
        frames.clear();
        for(int i=0;i<4;i++)
        {
            frames.add(new TextureRegion(atlas.findRegion("Tridentmandefeated"+i)));//change
        }
        defeated=new Animation(0.1f,frames);
        frames.clear();
        spearmaninit=new TextureRegion(atlas.findRegion("Tridentman0"));
        setBounds(getX(),getY(),200/ Lone_Warrior1.PPM,170/Lone_Warrior1.PPM);
        setRegion(spearmaninit);
        time=0f;

    }

    public void update(float dt){

        if(tridentmanstate !=-1) {
            setPosition(tridentman1.getPosition().x - getWidth() / 2, (tridentman1.getPosition().y - getHeight() / 2)+13/Lone_Warrior1.PPM);
            if (tridentmanstate ==0 && tridentman1.getLinearVelocity().x>0)
                tridentmanstate =1;
            if (tridentmanstate ==1 && tridentman1.getLinearVelocity().x==0) {
                Play_State.bodiesToRemove.add(tridentman1);
                Play_State.enemycounter++;
                tridentmanstate = -1;

            }
            if(tridentmanstate ==0)
                tridentman1.setLinearVelocity(state.getVelocity(),0);
            setRegion(getFrame(dt));
        }
    }
    public boolean check(){
        if(tridentmanstate !=-1)
            return true;
        else
            return false;
    }
    public TextureRegion getFrame(float dt){
        TextureRegion region=null;
        region=approaching.getKeyFrame(time,true);
        if(tridentmanstate ==1)
            region=defeated.getKeyFrame(time);
       /* if(!region.isFlipX())
        region.flip(true,false);*/
        time=tridentmanstate==previousstate?time+dt:0;
        previousstate=tridentmanstate;
        return region;

    }

    @Override
    public void defineEnemy() {
        BodyDef bdef=new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type=BodyDef.BodyType.DynamicBody;
        tridentman1 =Play_State.world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape War1=new PolygonShape();
        War1.setAsBox(60/Lone_Warrior1.PPM,60/Lone_Warrior1.PPM);

        fdef.shape=War1;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_APPROACHING;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND|Lone_Warrior1.BIT_RUN|Lone_Warrior1.BIT_ATTACK;
        tridentman1.createFixture(fdef).setUserData("tridentman1");//change

    }
}
