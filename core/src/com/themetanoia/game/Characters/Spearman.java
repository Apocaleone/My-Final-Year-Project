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
 * Created by MITHUN on 05-02-2017.
 */
public class Spearman extends Sprite {
    public static World world;
    public static Body spearman;
    private TextureAtlas atlas;
    public Animation approaching;
    public static float time;
    private TextureRegion spearmaninit;

    public static int spearmanstate=0;

    public Spearman(World world){
        this.world=world;
        atlas=new TextureAtlas("Warrior.pack");
        time=0;

        Array<TextureRegion> frames=new Array<TextureRegion>();
        for(int i=0;i<6;i++)
        {
            frames.add(new TextureRegion(atlas.findRegion("running"+i)));
        }
        approaching=new Animation(0.1f,frames);
        frames.clear();
        spearmaninit=new TextureRegion(atlas.findRegion("running0"));
        setBounds(0,0,100/ Lone_Warrior1.PPM,100/Lone_Warrior1.PPM);
        setRegion(spearmaninit);

    }

    public void update(float dt){

        if(spearmanstate!=-1)
        setPosition(spearman.getPosition().x-getWidth()/2,spearman.getPosition().y-getHeight()/2);
        setRegion(getFrame(dt));
        if(spearmanstate==0&&spearman.getLinearVelocity().x>0)
            spearmanstate=1;
        if(spearmanstate==1&&spearman.getLinearVelocity().x==0){
            Play_State.bodiesToRemove.add(spearman);
            spearmanstate=-1;

    }
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

    public static void defineSpearman(){
        BodyDef bdef=new BodyDef();
        bdef.position.set((Lone_Warrior1.V_Width/Lone_Warrior1.PPM)*4,100/Lone_Warrior1.PPM );
        bdef.type=BodyDef.BodyType.DynamicBody;
        spearman=world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape War=new PolygonShape();
        War.setAsBox(50/Lone_Warrior1.PPM,45/Lone_Warrior1.PPM);

        fdef.shape=War;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_APPROACHING;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND|Lone_Warrior1.BIT_RUN|Lone_Warrior1.BIT_ATTACK;
        spearman.createFixture(fdef).setUserData("spearman");
    }

}
