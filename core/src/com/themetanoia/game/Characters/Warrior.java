package com.themetanoia.game.Characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import     com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.themetanoia.game.Load_Screens.LoadState;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Screens.Play_State;
import com.themetanoia.game.Screens.Play_State;
import com.themetanoia.game.Tools.AudioManager;

/**
 * Created by MITHUN on 17-10-2016.
 */
public class Warrior extends Sprite {
    public enum Move{Defeat,Running,Highkick,Lowkick,Megapunch,Groundpunch,Multikick,Spinpunch,Exorcize,Hurricanebreath};
    public Move currentState;
    public Move previousState;
    public static World world;                               //NEED TO REMOVE!!!
    public static Body hero,highkickattack,heroretreating,lowkickattack,herodefeated,megapunchattack,groundpunchattack,spinpunchattack,
    exorcizeattack,hurricanebreathattack,multikickattack;
    public Animation running,highkick,lowkick,retreat,defeat,megapunch,groundpunch,multikick,spinpunch,exorcize,hurricanebreath;
    public float time;
    private TextureRegion warriorinit;

    boolean rightside;
    public static int posture=0;
    public static boolean fallback=false;
    private Play_State state;
    
    private TextureAtlas atlas;
    private AudioManager audio;



    public Warrior(World world, Play_State state){
        //super(atlas.findRegion("running0"));
        this.state=state;
        atlas=state.game.getAtlas(2);

        this.world=world;

        audio=new AudioManager(state.game);


        currentState=Move.Running;
        previousState=Move.Running;
        time=0;
        rightside=true;

        Array<TextureRegion> frames=new Array<TextureRegion>();
        for(int i=0;i<3;i++)
        {
            frames.add(new TextureRegion(atlas.findRegion("running"+i)));
        }
        running=new Animation(0.1f,frames);
        frames.clear();
        for(int i=0;i<7;i++)
        {
            frames.add(new TextureRegion(atlas.findRegion("highkick"+i)));
        }
        highkick=new Animation(0.04f,frames);
        frames.clear();
        for(int i=0;i<13;i++)
        {
            if(i<=9)
            frames.add(new TextureRegion(atlas.findRegion("lowkick0"+i)));
            else
                frames.add(new TextureRegion(atlas.findRegion("lowkick"+i)));
        }
        lowkick=new Animation(0.027f,frames);
        frames.clear();
        for(int i=0;i<4;i++)
        {
                frames.add(new TextureRegion(atlas.findRegion("exorcize"+i)));
        }
        exorcize=new Animation(0.06f,frames);
        frames.clear();
        for(int i=0;i<7;i++) {
            frames.add(new TextureRegion(atlas.findRegion("groundpunch" + i)));
        }
        groundpunch=new Animation(0.05f,frames);
        frames.clear();
        for(int i=0;i<7;i++)
        {
                frames.add(new TextureRegion(atlas.findRegion("hurricanebreath"+i)));
        }
        hurricanebreath=new Animation(0.07f,frames);
        frames.clear();
        for(int i=0;i<4;i++)
        {
                frames.add(new TextureRegion(atlas.findRegion("multikick"+i)));
        }
        multikick=new Animation(0.05f,frames);
        frames.clear();
        for(int i=0;i<7;i++)
        {
                frames.add(new TextureRegion(atlas.findRegion("powerpunch"+i)));
        }
        megapunch=new Animation(0.06f,frames);
        frames.clear();
        for(int i=0;i<5;i++)
        {
                frames.add(new TextureRegion(atlas.findRegion("spinpunch"+i)));
        }
        spinpunch=new Animation(0.05f,frames);
        frames.clear();
        for(int i=0;i<2;i++)
        {
            frames.add(new TextureRegion(atlas.findRegion("retreat"+i)));
        }
        retreat=new Animation(0.8f,frames);
        frames.clear();
        for(int i=0;i<5;i++)
        {
            frames.add(new TextureRegion(atlas.findRegion("defeat"+i)));
        }
        defeat=new Animation(0.2f,frames);
        frames.clear();
        defineWarrior();
        warriorinit=new TextureRegion(atlas.findRegion("running0"));
        setBounds(0,0,100/Lone_Warrior1.PPM,100/Lone_Warrior1.PPM);
        setRegion(warriorinit);
    }

    public void update(float dt){


        if(posture==0)//run animation follows body only if posture=0
        {setBounds(0,0,200/Lone_Warrior1.PPM,250/Lone_Warrior1.PPM);
            setPosition((hero.getPosition().x-getWidth()/2),(hero.getPosition().y-getHeight()/2)+65/Lone_Warrior1.PPM);

            Lone_Warrior1.x=hero.getPosition().x;
            Lone_Warrior1.y=hero.getPosition().y;
            Lone_Warrior1.x1=hero.getPosition().x;
            Lone_Warrior1.y1=hero.getPosition().y;}
        if(posture==1){//flying kick animation follows body only if posture=1
            setBounds(0,0,200/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM);
        setPosition(highkickattack.getPosition().x-getWidth()/2, (highkickattack.getPosition().y-getHeight()/2));}
        if(posture==2){
            setBounds(0,0,200/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM);
            setPosition(lowkickattack.getPosition().x-getWidth()/2, (lowkickattack.getPosition().y-getHeight()/2)+50/Lone_Warrior1.PPM);}
        if(posture==3){
            setBounds(0,0,200/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM);
            setPosition(multikickattack.getPosition().x-getWidth()/2, (multikickattack.getPosition().y-getHeight()/2)+50/Lone_Warrior1.PPM);}
        if(posture==4){
            setBounds(0,0,200/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM);
            setPosition(groundpunchattack.getPosition().x-getWidth()/2, (groundpunchattack.getPosition().y-getHeight()/2)+50/Lone_Warrior1.PPM);}
        if(posture==5){
            setBounds(0,0,200/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM);
            setPosition(spinpunchattack.getPosition().x-getWidth()/2, (spinpunchattack.getPosition().y-getHeight()/2)+50/Lone_Warrior1.PPM);}
        if(posture==6){
            setBounds(0,0,200/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM);
            setPosition(megapunchattack.getPosition().x-getWidth()/2, (megapunchattack.getPosition().y-getHeight()/2)+50/Lone_Warrior1.PPM);}
        if(posture==7){
            setBounds(0,0,200/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM);
            setPosition(hurricanebreathattack.getPosition().x-getWidth()/2, (hurricanebreathattack.getPosition().y-getHeight()/2)+50/Lone_Warrior1.PPM);}
        if(posture==8){
            setBounds(0,0,200/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM);
            setPosition(exorcizeattack.getPosition().x-getWidth()/2, (exorcizeattack.getPosition().y-getHeight()/2)+50/Lone_Warrior1.PPM);}
        if(posture==-1){
            audio.playfxMusic(8);
            setBounds(0,0,200/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM);
            setPosition(herodefeated.getPosition().x-getWidth()/2,(herodefeated.getPosition().y-getHeight()/2)+50/Lone_Warrior1.PPM);}
        if(posture==0)
            hero.setLinearVelocity(-state.getVelocity(),0);
        setRegion(getFrame(dt));//set the texture region of animation to the sprite which is the entire class
    }

    public TextureRegion getFrame(float dt){//determines the animation to be executed.
        currentState=getMove(posture);//get the type of move that is being called


        TextureRegion region = null;//default value of the texture region in case running animation fails
        switch (currentState){//switch between different animation states.
            case Defeat:
                region=defeat.getKeyFrame(time);
                break;
            case Running://the real default value i.e. running animation
                region=running.getKeyFrame(time,true);
                break;
            case Highkick://1
                if(state.flagchecker(2)==true){
                    setBounds(heroretreating.getPosition().x-getWidth()/2,heroretreating.getPosition().y-getHeight()/2,200/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM);
                    region=retreat.getKeyFrame(time);}
                else
                region=highkick.getKeyFrame(time);
                break;
            case Lowkick://2
                if(state.flagchecker(2)==true){
                    setBounds(heroretreating.getPosition().x-getWidth()/2,(heroretreating.getPosition().y-getHeight()/2)+50/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM);
                    region=retreat.getKeyFrame(time);}
                else
                region=lowkick.getKeyFrame(time);
                break;
            case Multikick://3
                if(state.flagchecker(2)==true){
                    setBounds(heroretreating.getPosition().x-getWidth()/2,(heroretreating.getPosition().y-getHeight()/2)+50/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM);
                    region=retreat.getKeyFrame(time);}
                else
                    region=multikick.getKeyFrame(time);
                break;
            case Megapunch://4
                if(state.flagchecker(2)==true){
                    setBounds(heroretreating.getPosition().x-getWidth()/2,(heroretreating.getPosition().y-getHeight()/2)+50/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM);
                    region=retreat.getKeyFrame(time);}
                else
                    region=megapunch.getKeyFrame(time);
                break;
            case Groundpunch://5
                if(state.flagchecker(2)==true){
                    setBounds(heroretreating.getPosition().x-getWidth()/2,(heroretreating.getPosition().y-getHeight()/2)+50/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM);
                    region=retreat.getKeyFrame(time);}
                else
                    region=groundpunch.getKeyFrame(time);
                break;
            case Hurricanebreath://6
                if(state.flagchecker(2)==true){
                    setBounds(heroretreating.getPosition().x-getWidth()/2,(heroretreating.getPosition().y-getHeight()/2)+50/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM);
                    region=retreat.getKeyFrame(time);}
                else
                    region=hurricanebreath.getKeyFrame(time);
                break;
            case Spinpunch://7
                if(state.flagchecker(2)==true){
                    setBounds(heroretreating.getPosition().x-getWidth()/2,(heroretreating.getPosition().y-getHeight()/2)+50/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM);
                    region=retreat.getKeyFrame(time);}
                else
                    region=spinpunch.getKeyFrame(time);
                break;
            case Exorcize://8
                if(state.flagchecker(2)==true){
                    setBounds(heroretreating.getPosition().x-getWidth()/2,(heroretreating.getPosition().y-getHeight()/2)+50/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM,200/Lone_Warrior1.PPM);
                    region=retreat.getKeyFrame(time);}
                else
                    region=exorcize.getKeyFrame(time);
                break;
            default://is not needed, or the case running is not needed. Needs to be checked
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

        time=currentState==previousState ? time+dt : 0;//I don't know why I put it here but the tutorial said so, to swap between different states of animation.
        previousState=currentState;
        return region;//return the region to the called position
    }

    public float getTime(){
        return time;
    }


    public Move getMove(int posture){//the actual function that determines the move to be executed.
        if(posture==-1)
            return Move.Defeat;
        else if(posture==1)
            return Move.Highkick;
        else if(posture==2)
            return Move.Lowkick;
        else if(posture==3)
            return Move.Multikick;
        else if(posture==4)
            return Move.Groundpunch;
        else if(posture==5)
            return Move.Spinpunch;
        else if(posture==6)
            return Move.Megapunch;
        else if(posture==7)
            return Move.Hurricanebreath;
        else if(posture==8)
            return Move.Exorcize;
        else
            return Move.Running;
    }



    public static void defineWarrior(){
    BodyDef bdef=new BodyDef();
     bdef.position.set(Lone_Warrior1.x,Lone_Warrior1.y );
     bdef.type=BodyDef.BodyType.DynamicBody;
     hero=world.createBody(bdef);

     FixtureDef fdef=new FixtureDef();
     PolygonShape War=new PolygonShape();
     War.setAsBox(60/Lone_Warrior1.PPM,50/Lone_Warrior1.PPM);

     fdef.shape=War;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_RUN;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND|Lone_Warrior1.BIT_APPROACHING;
     hero.createFixture(fdef).setUserData("warrior");
    }

    public static void defineHighkick(){
        BodyDef bdef=new BodyDef();
        bdef.position.set(hero.getPosition().x,hero.getPosition().y);
        bdef.type=BodyDef.BodyType.DynamicBody;
        highkickattack =world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape War=new PolygonShape();
        War.setAsBox(50/Lone_Warrior1.PPM,100/Lone_Warrior1.PPM);

        fdef.shape=War;
        fdef.friction=0.34f;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_ATTACK;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND|Lone_Warrior1.BIT_APPROACHING;
        highkickattack.createFixture(fdef).setUserData("warriorhighkick");
    }

    public static void defineLowkick(){
        BodyDef bdef=new BodyDef();
        bdef.position.set(hero.getPosition().x,hero.getPosition().y);
        bdef.type=BodyDef.BodyType.DynamicBody;
        lowkickattack =world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape War=new PolygonShape();
        War.setAsBox(50/Lone_Warrior1.PPM,45/Lone_Warrior1.PPM);

        fdef.shape=War;
        fdef.friction=0.34f;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_ATTACK;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND|Lone_Warrior1.BIT_APPROACHING;
        lowkickattack.createFixture(fdef).setUserData("warriorlowkick");
    }

    public static void defineMegapunch(){
        BodyDef bdef=new BodyDef();
        bdef.position.set(hero.getPosition().x,hero.getPosition().y);
        bdef.type=BodyDef.BodyType.DynamicBody;
        megapunchattack =world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape War=new PolygonShape();
        War.setAsBox(50/Lone_Warrior1.PPM,45/Lone_Warrior1.PPM);

        fdef.shape=War;
        fdef.friction=0.34f;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_ATTACK;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND|Lone_Warrior1.BIT_APPROACHING;
        megapunchattack.createFixture(fdef).setUserData("warriormegapunch");
    }

    public static void defineGroundpunch(){
        BodyDef bdef=new BodyDef();
        bdef.position.set(hero.getPosition().x,hero.getPosition().y);
        bdef.type=BodyDef.BodyType.DynamicBody;
        groundpunchattack =world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape War=new PolygonShape();
        War.setAsBox(50/Lone_Warrior1.PPM,45/Lone_Warrior1.PPM);

        fdef.shape=War;
        fdef.friction=0.34f;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_ATTACK;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND|Lone_Warrior1.BIT_APPROACHING;
        groundpunchattack.createFixture(fdef).setUserData("warriorgroundpunch");
    }

    public static void defineSpinpunch(){
        BodyDef bdef=new BodyDef();
        bdef.position.set(hero.getPosition().x,hero.getPosition().y);
        bdef.type=BodyDef.BodyType.DynamicBody;
        spinpunchattack =world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape War=new PolygonShape();
        War.setAsBox(50/Lone_Warrior1.PPM,45/Lone_Warrior1.PPM);

        fdef.shape=War;
        fdef.friction=0.34f;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_ATTACK;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND|Lone_Warrior1.BIT_APPROACHING;
        spinpunchattack.createFixture(fdef).setUserData("warriorspinpunch");
    }

    public static void defineMultikick(){
        BodyDef bdef=new BodyDef();
        bdef.position.set(hero.getPosition().x,hero.getPosition().y);
        bdef.type=BodyDef.BodyType.DynamicBody;
        multikickattack =world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape War=new PolygonShape();
        War.setAsBox(50/Lone_Warrior1.PPM,45/Lone_Warrior1.PPM);

        fdef.shape=War;
        fdef.friction=0.34f;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_ATTACK;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND|Lone_Warrior1.BIT_APPROACHING;
        multikickattack.createFixture(fdef).setUserData("warriormultikick");
    }

    public static void defineExorcize(){
        BodyDef bdef=new BodyDef();
        bdef.position.set(hero.getPosition().x,hero.getPosition().y);
        bdef.type=BodyDef.BodyType.DynamicBody;
       exorcizeattack =world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape War=new PolygonShape();
        War.setAsBox(50/Lone_Warrior1.PPM,45/Lone_Warrior1.PPM);

        fdef.shape=War;
        fdef.friction=0.34f;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_ATTACK;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND|Lone_Warrior1.BIT_APPROACHING;
        exorcizeattack.createFixture(fdef).setUserData("warriorexorcize");
    }

    public static void defineHurricanebreath(){
        BodyDef bdef=new BodyDef();
        bdef.position.set(hero.getPosition().x,hero.getPosition().y);
        bdef.type=BodyDef.BodyType.DynamicBody;
        hurricanebreathattack =world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape War=new PolygonShape();
        War.setAsBox(50/Lone_Warrior1.PPM,45/Lone_Warrior1.PPM);

        fdef.shape=War;
        fdef.friction=0.34f;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_ATTACK;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND|Lone_Warrior1.BIT_APPROACHING;
        hurricanebreathattack.createFixture(fdef).setUserData("warriorhurricanebreath");
    }




    public static void defineHeroRetreating(){
        BodyDef bdef=new BodyDef();
        bdef.position.set(Lone_Warrior1.x1,Lone_Warrior1.y1);
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

    public static void defineHeroDefeated(){
        BodyDef bdef=new BodyDef();
        bdef.position.set(Lone_Warrior1.x1,Lone_Warrior1.y1);
        bdef.type=BodyDef.BodyType.DynamicBody;
        herodefeated=world.createBody(bdef);

        FixtureDef fdef=new FixtureDef();
        PolygonShape War=new PolygonShape();
        War.setAsBox(50/Lone_Warrior1.PPM,45/Lone_Warrior1.PPM);

        fdef.shape=War;
        fdef.filter.categoryBits=Lone_Warrior1.BIT_RUN;
        fdef.filter.maskBits=Lone_Warrior1.BIT_GROUND;
        herodefeated.createFixture(fdef).setUserData("warriorfallen");
    }
}
