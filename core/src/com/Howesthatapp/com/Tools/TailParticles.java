package com.Howesthatapp.com.Tools;

import com.Howesthatapp.com.GameSettings;
import com.Howesthatapp.com.Screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Mike on 23/06/2015.
 *
 *
 */
public class TailParticles {

    private static ParticleEffect particleEffect;
    private static ParticleEffectPool pool;
    private static Array<ParticleEffectPool.PooledEffect> emitters;


    public static void createEffect(){
        particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal("Graphics/tail.p"), Gdx.files.internal("Graphics"));
        particleEffect.start();
        particleEffect.scaleEffect(0.5f);
        pool = new ParticleEffectPool(particleEffect, 0, 1);
        emitters = new Array<ParticleEffectPool.PooledEffect>();

        ParticleEffectPool.PooledEffect effect1 = pool.obtain();
        emitters.add(effect1);
    }



    public static void renderEffect(float delta, SpriteBatch batch){

        for (int i = 0; i < emitters.size; i++){
            emitters.get(i).setPosition(GameScreen.getCamera().position.x + 150, GameSettings.notes[GameSettings.noteindex]);
            emitters.get(i).draw(batch, delta);
            if (emitters.get(i).isComplete()){
                emitters.removeValue(emitters.get(i), true);
                emitters.get(i).free();
            }
        }
    }



}
