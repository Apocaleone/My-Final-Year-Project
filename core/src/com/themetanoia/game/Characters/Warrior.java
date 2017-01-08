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
    public Body hero;
    public Animation running,highkick,lowkick;
    private float time;
    private TextureRegion warriorinit;

    boolean rightside;
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
        warriorinit=new TextureRegion(state.getAtlas().findRegion("running0"));
        setBounds(0,0,100/Lone_Warrior1.PPM,100/Lone_Warrior1.PPM);
        setRegion(warriorinit);
    }

    public void update(float dt){
        setPosition(hero.getPosition().x-getWidth()/2,hero.getPosition().y-getHeight()/2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        currentState=getMove();

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
                break;
            default:
                region=running.getKeyFrame(time,true);
                break;
        }

        if((hero.getLinearVelocity().x<0||!rightside)&&!region.isFlipX())
        {
            region.flip(true,false);
            rightside=false;
        }
        else if((hero.getLinearVelocity().x>0||rightside)&& region.isFlipX()){
            region.flip(true,false);
            rightside=true;
        }

        time=currentState==previousState ? time+dt : 0;
        previousState=currentState;
        return region;
    }


    public Move getMove(){
        if(hero.getLinearVelocity().y!=0)
            return Move.Highkick;
        else if((hero.getLinearVelocity().x>2||hero.getLinearVelocity().x<0)&&hero.getLinearVelocity().y==0)
            return Move.Lowkick;
        else
            return Move.Running;
    }



    public void defineWarrior(){
    BodyDef bdef=new BodyDef();
     bdef.position.set(100/ Lone_Warrior1.PPM,200/Lone_Warrior1.PPM);
     bdef.type=BodyDef.BodyType.DynamicBody;
     hero=world.createBody(bdef);

     FixtureDef fdef=new FixtureDef();
     PolygonShape War=new PolygonShape();
     War.setAsBox(50/Lone_Warrior1.PPM,45/Lone_Warrior1.PPM);

     fdef.shape=War;
     fdef.restitution=0.4f;
     hero.createFixture(fdef);


 }
}
