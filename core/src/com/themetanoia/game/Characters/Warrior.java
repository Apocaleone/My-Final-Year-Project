package com.themetanoia.game.Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import     com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.sun.org.apache.xpath.internal.operations.String;
import com.themetanoia.game.Load_Screens.LoadState;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Screens.Play_State;

import static com.themetanoia.game.Screens.Play_State.*;

/**
 * Created by MITHUN on 17-10-2016.
 */
public class Warrior extends Sprite {
    public enum Move{Running,Highkick,Lowkick};
    public Move currentState;
    public Move previousState;
    public World world;
    public static Body hero,heroattack,heroretreating;
    public Animation running,highkick,lowkick;
    public static float time;
    private TextureRegion warriorinit;

    boolean rightside;
    public int posture=0;
    LoadState loading;


    public Warrior(World world, Play_State state){
        super(state.getAtlas().findRegion("running0"));
        this.world=world;


        loading=new LoadState();

        currentState=Move.Running;
        previousState=Move.Running;
        time=0;
        rightside=true;

        Array<TextureRegion> frames=new Array<TextureRegion>();
        for(int i=0;i<6;i++)
        {
            frames.add(new TextureRegion(state.getAtlas().findRegion("running"+i)));
        }
        running=new Animation(0.1f,frames);
        frames.clear();
        for(int i=0;i<8;i++)
        {
            frames.add(new TextureRegion(state.getAtlas().findRegion("highkick"+i)));
        }
        highkick=new Animation(0.1f,frames);
        frames.clear();
        for(int i=0;i<6;i++)
        {
            frames.add(new TextureRegion(state.getAtlas().findRegion("lowkick"+i)));
        }
        lowkick=new Animation(0.1f,frames);
        frames.clear();
        defineWarrior();
        if(posture==1)
        defineHeroAttack();
        warriorinit=new TextureRegion(state.getAtlas().findRegion("running0"));
        setBounds(0,0,100/Lone_Warrior1.PPM,100/Lone_Warrior1.PPM);
        setRegion(warriorinit);
    }

    public void update(float dt){
        setPosition(hero.getPosition().x-getWidth()/2,hero.getPosition().y-getHeight()/2);
        if(posture==1)
        setPosition(heroattack.getPosition().x-getWidth()/2,heroattack.getPosition().y-getHeight()/2);

        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        currentState=getMove(posture);


        TextureRegion region;
        switch (currentState){
            case Running:
                region=running.getKeyFrame(time,true);
                break;
            case Highkick:
                region=highkick.getKeyFrame(time);
                break;
            case Lowkick:
                region=lowkick.getKeyFrame(time);
                if(lowkick.isAnimationFinished(time))
                    posture=0;
                break;
            default:
                region=running.getKeyFrame(time,true);
                break;
        }

        /*if((hero.getLinearVelocity().x<0||!rightside)&&!region.isFlipX())
        {
            region.flip(true,false);
            rightside=false;
        }
        else if((hero.getLinearVelocity().x>0||rightside)&& region.isFlipX()){
            region.flip(true,false);
            rightside=true;
        }*/

        time=currentState==previousState ? time+dt : 0;
        previousState=currentState;
        return region;
    }


    public Move getMove(int posture){
        if(posture==1)
            return Move.Highkick;
        else if(posture==2)
            return Move.Lowkick;
        else
            return Move.Running;
    }



    public void defineWarrior(){
    BodyDef bdef=new BodyDef();
     bdef.position.set(Lone_Warrior1.x,Lone_Warrior1.y );
     bdef.type=BodyDef.BodyType.DynamicBody;
     hero=world.createBody(bdef);

     FixtureDef fdef=new FixtureDef();
     PolygonShape War=new PolygonShape();
     War.setAsBox(50/Lone_Warrior1.PPM,45/Lone_Warrior1.PPM);

     fdef.shape=War;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_RUN;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND|Lone_Warrior1.BIT_APPROACHING;
     hero.createFixture(fdef).setUserData("warrior");

    }

    public void defineHeroAttack(){
        BodyDef bdef=new BodyDef();
        bdef.position.set(hero.getPosition().x,hero.getPosition().y );
        bdef.type=BodyDef.BodyType.DynamicBody;
        heroattack=world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape War=new PolygonShape();
        War.setAsBox(50/Lone_Warrior1.PPM,45/Lone_Warrior1.PPM);

        fdef.shape=War;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_ATTACK;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND|Lone_Warrior1.BIT_APPROACHING;
        heroattack.createFixture(fdef).setUserData("warrior1");
    }

    public void defineHeroRetreating(){
        BodyDef bdef=new BodyDef();
        bdef.position.set(heroattack.getPosition().x,heroattack.getPosition().y );
        bdef.type=BodyDef.BodyType.DynamicBody;
        heroretreating=world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape War=new PolygonShape();
        War.setAsBox(50/Lone_Warrior1.PPM,45/Lone_Warrior1.PPM);

        fdef.shape=War;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_RUN;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND|Lone_Warrior1.BIT_APPROACHING;
        heroretreating.createFixture(fdef).setUserData("warriorreturn");
    }
}
