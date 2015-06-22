package com.Howesthatapp.com.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Mike on 17/06/2015.
 *
 *
 */
public class ParticleStuff {

    private static ParticleEffect particleEffect;
    private static ParticleEffectPool pool;
    private static Array<ParticleEffectPool.PooledEffect> emitters;


    public static void createEffect(){
        particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal("Graphics/bar.p"), Gdx.files.internal("Graphics"));
        particleEffect.start();
        particleEffect.setPosition(-Gdx.graphics.getWidth()/2, 20);
        pool = new ParticleEffectPool(particleEffect, 0, 1);
        emitters = new Array<ParticleEffectPool.PooledEffect>();


        ParticleEffectPool.PooledEffect effect1 = pool.obtain();
        emitters.add(effect1);


    }



    public static void renderEffect(float delta, SpriteBatch batch){



        for (ParticleEffectPool.PooledEffect effect : emitters){
            effect.setPosition(0, 0);
            effect.draw(batch, delta);
            if (effect.isComplete()){
                emitters.removeValue(effect, true);
                effect.free();
            }
        }


    }



}
