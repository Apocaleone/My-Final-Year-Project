package com.themetanoia.game.Characters;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.themetanoia.game.Screens.Play_State;

/**
 * Created by MITHOON on 27-02-2017.
 */
public abstract class Enemies extends Sprite {
    World world;
    Body b2body;
    public Enemies(Play_State screen,float x, float y){
        this.world=screen.world;
        setPosition(x,y);
        defineEnemy();
    }

    public abstract void defineEnemy();
    public abstract void update(float dt);
    public abstract boolean check();
}
