package com.Howesthatapp.com.Tools;

import com.Howesthatapp.com.GameSettings;
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
public class BarParticles {

    private static ParticleEffect particleEffect;
    private static ParticleEffectPool pool;
    private static Array<ParticleEffectPool.PooledEffect> emitters;


    public static void createEffect(){
        particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal("Graphics/bar2.p"), Gdx.files.internal("Graphics"));
        particleEffect.start();
        particleEffect.setPosition(-Gdx.graphics.getWidth(), 0);
        pool = new ParticleEffectPool(particleEffect, 0, 1);
        emitters = new Array<ParticleEffectPool.PooledEffect>();

        ParticleEffectPool.PooledEffect effect1 = pool.obtain();
        emitters.add(effect1);
        emitters.add(effect1);
        emitters.add(effect1);
        emitters.add(effect1);
        emitters.add(effect1);

    }



    public static void renderEffect(float delta, SpriteBatch batch){

        for (int i = 0; i < emitters.size; i++){
            emitters.get(i).setPosition(GameSettings.xPosition, GameSettings.heights[i] + 10);
            emitters.get(i).draw(batch, delta);
            if (emitters.get(i).isComplete()){
                emitters.removeValue(emitters.get(i), true);
                emitters.get(i).free();
            }
        }
    }

}
