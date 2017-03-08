package com.themetanoia.game.Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
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
 * Created by MITHUN on 05-02-2017.
 */
public class Spearman extends Enemies {
    public Body spearman1;
    private TextureAtlas atlas;
    public Animation approaching;
    public static float time;
    private TextureRegion spearmaninit;

    public int spearmanstate=0;


    public Spearman(Play_State state,float x, float y){
        super(state,x,y);
        atlas=new TextureAtlas();
        atlas=Lone_Warrior1.getAtlas(2);
        time=0;

        Array<TextureRegion> frames=new Array<TextureRegion>();
        for(int i=0;i<6;i++)
        {
            frames.add(new TextureRegion(atlas.findRegion("running"+i)));
        }
        approaching=new Animation(0.1f,frames);
        frames.clear();
        spearmaninit=new TextureRegion(atlas.findRegion("running0"));
        setBounds(getX(),getY(),100/ Lone_Warrior1.PPM,100/Lone_Warrior1.PPM);
        setRegion(spearmaninit);

    }

    public void update(float dt){

        if(spearmanstate!=-1) {
            setPosition(spearman1.getPosition().x - getWidth() / 2, spearman1.getPosition().y - getHeight() / 2);
            if (spearmanstate==0 && spearman1.getLinearVelocity().x>0)
                spearmanstate=1;
            if (spearmanstate==1 && spearman1.getLinearVelocity().x==0) {
                Play_State.bodiesToRemove.add(spearman1);
                Play_State.flag2=true;
                spearmanstate = -1;

            }
            if(spearmanstate==0)
                spearman1.applyForceToCenter(-2.3f,0,true);
            setRegion(getFrame(dt));
        }
    }
    public boolean check(){
        if(spearmanstate!=-1)
             return true;
    else
        return false;
    }
    public TextureRegion getFrame(float dt){
        TextureRegion region=null;
        //if(berserkerstate==0){
        region=approaching.getKeyFrame(time,true);//}
        //if(berserkerstate==1)
           // region=defeated.getKeyFrame(time,true);
        if(!region.isFlipX())
        region.flip(true,false);
        time=time+dt;
        return region;

    }

    @Override
    public void defineEnemy() {
        BodyDef bdef=new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type=BodyDef.BodyType.DynamicBody;
        spearman1=Play_State.world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape War1=new PolygonShape();
        War1.setAsBox(50/Lone_Warrior1.PPM,45/Lone_Warrior1.PPM);

        fdef.shape=War1;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_APPROACHING;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND|Lone_Warrior1.BIT_RUN|Lone_Warrior1.BIT_ATTACK;
        spearman1.createFixture(fdef).setUserData("spearman1");

    }

}
