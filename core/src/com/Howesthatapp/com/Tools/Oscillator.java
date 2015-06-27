package com.Howesthatapp.com.Tools;

import com.Howesthatapp.com.GameSettings;
import com.Howesthatapp.com.Screens.GameScreen;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Mike on 23/06/2015.
 *
 */
public class Oscillator {

    private static float[] samples = new float[10000];

    public static void generateSound(){

        // TODO: Get this working
        for (int i = 0; i < 10000; i++){
            samples[i] = (float) (10000*Math.sin(i));
        }
    }



        public static void playSound() {

            GameScreen.device.writeSamples(samples, 0, samples.length);
        }





}
