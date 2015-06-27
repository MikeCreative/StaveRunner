package com.Howesthatapp.com.Tools;

import com.Howesthatapp.com.GameSettings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Mike on 17/06/2015.
 *
 *
 */
public class lineAnimation {

    private static Animation lineanimation;
    private static TextureRegion[] frames;
    private static float stateTime;

    public static void createAnimation(Texture texture, int rows, int columns){
        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth()/columns, texture.getHeight()/rows);
        frames = new TextureRegion[rows*columns];
        int index = 0;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                frames[index++] = tmp[i][j];
            }
        }

        lineanimation = new Animation(0.05f, frames);

        stateTime = 0;

    }

    // Settings
    private static float height = 20;
    private static int number = 5;

    // Update Frame
    private static float nextFrame = GameSettings.xScreenSize/2;
    private static float current = -GameSettings.xScreenSize/2;
    private static int frameNumber = 0;

    public static void renderLineAnimation(SpriteBatch batch){
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = lineanimation.getKeyFrame(stateTime, true);
        // This frame
        for (int i = 0; i < number; i++){
            batch.draw(currentFrame, current + frameNumber*GameSettings.xScreenSize, GameSettings.heights[i], GameSettings.xScreenSize, height);
        }

        // Next Frame
        for (int i = 0; i < number; i++){
            batch.draw(currentFrame, nextFrame + frameNumber*GameSettings.xScreenSize, GameSettings.heights[i], GameSettings.xScreenSize, height);
        }


        if (GameSettings.xPosition > ((frameNumber + 1)*GameSettings.xScreenSize)){
            frameNumber++;
        }

        // TODO: Repeating Frames - When frame is out of view, then remove and place before view


    }



}
