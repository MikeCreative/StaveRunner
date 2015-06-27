package com.Howesthatapp.com.Tools;

import com.Howesthatapp.com.GameClass;
import com.Howesthatapp.com.GameSettings;
import com.Howesthatapp.com.Screens.GameScreen;
import com.badlogic.gdx.Gdx;

/**
 * Created by Mike on 23/06/2015.
 *
 */
public class Ripple {

    public static void updateRipple(){
        GameScreen.x = -GameSettings.xCharacterOffset;
        GameScreen.y = -GameSettings.notes[GameSettings.noteindex];
        GameScreen.x_offset = GameScreen.getCamera().position.x;
        GameScreen.y_offset = GameScreen.getCamera().position.y;
        GameScreen.start = System.nanoTime();

//        Oscillator.playSound();
        GameClass.soundGenerator.Generate();
    }

}
