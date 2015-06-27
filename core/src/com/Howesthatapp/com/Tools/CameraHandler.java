package com.Howesthatapp.com.Tools;

import com.Howesthatapp.com.Screens.GameScreen;

/**
 * Created by Mike on 22/06/2015.
 *
 */
public class CameraHandler {

    public static boolean cameraShake = false;

    public static void updateCamera(){

        if (cameraShake) {
            shakeCamera();
        }


        GameScreen.getCamera().update();



    }


    public static void shakeCamera(){
        // TODO: This
    }


}
