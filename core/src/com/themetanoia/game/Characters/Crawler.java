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
public class Crawler extends Enemies {private Body crawler1;
    private TextureAtlas atlas;
    private Animation approaching,defeated;
    private float time;
    private TextureRegion spearmaninit;

    private int crawlerstate =0,previousstate;


    public Crawler (Play_State state, float x, float y){
        super(state,x,y);
        atlas=new TextureAtlas();
        atlas= Lone_Warrior1.getAtlas(4);

        Array<TextureRegion> frames=new Array<TextureRegion>();
        for(int i=0;i<4;i++)
        {
            frames.add(new TextureRegion(atlas.findRegion("Crawler"+i)));
        }
        approaching=new Animation(0.15f,frames);
        frames.clear();
        for(int i=0;i<4;i++)
        {
            frames.add(new TextureRegion(atlas.findRegion("Crawlerdefeated"+i)));
        }
        defeated=new Animation(0.1f,frames);
        frames.clear();
        spearmaninit=new TextureRegion(atlas.findRegion("Crawler0"));
        setBounds(getX(),getY(),200/ Lone_Warrior1.PPM,170/Lone_Warrior1.PPM);
        setRegion(spearmaninit);
        time=0f;

    }

    public void update(float dt){

        if(crawlerstate !=-1) {
            setPosition(crawler1.getPosition().x - getWidth() / 2, (crawler1.getPosition().y - getHeight() / 2)+13/Lone_Warrior1.PPM);
            if (crawlerstate ==0 && crawler1.getLinearVelocity().x>0)
                crawlerstate =1;
            if (crawlerstate ==1 && crawler1.getLinearVelocity().x==0) {
                Play_State.bodiesToRemove.add(crawler1);
                Play_State.enemycounter++;
                crawlerstate = -1;

            }
            if(crawlerstate ==0)
                crawler1.setLinearVelocity(Lone_Warrior1.velocity,0);
            setRegion(getFrame(dt));
        }
    }
    public boolean check(){
        if(crawlerstate !=-1)
            return true;
        else
            return false;
    }
    public TextureRegion getFrame(float dt){
        TextureRegion region=null;
        region=approaching.getKeyFrame(time,true);
        if(crawlerstate ==1)
            region=defeated.getKeyFrame(time);
       /* if(!region.isFlipX())
        region.flip(true,false);*/
        time=crawlerstate==previousstate?time+dt:0;
        previousstate=crawlerstate;
        return region;

    }

    @Override
    public void defineEnemy() {
        BodyDef bdef=new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type=BodyDef.BodyType.DynamicBody;
        crawler1 =Play_State.world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape War1=new PolygonShape();
        War1.setAsBox(60/Lone_Warrior1.PPM,60/Lone_Warrior1.PPM);

        fdef.shape=War1;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_APPROACHING;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND|Lone_Warrior1.BIT_RUN|Lone_Warrior1.BIT_ATTACK;
        crawler1.createFixture(fdef).setUserData("crawler1");

    }
}
