package com.Howesthatapp.com;

import com.Howesthatapp.com.Screens.GameScreen;
import com.Howesthatapp.com.Tools.Ripple;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Mike on 14/06/2015.
 *
 */
public class CustomGestureDetector implements GestureDetector.GestureListener {
    /**
     * @param x
     * @param y
     * @param pointer
     * @param button
     */
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        System.out.println("down");
        // Working Example
//        GameScreen.x = - ( x - Gdx.graphics.getWidth()/2);
//        GameScreen.y = y  - (Gdx.graphics.getHeight())/2;
//        System.out.println("x " + GameScreen.x + " y " + GameScreen.y);
//        GameScreen.x_offset = GameScreen.getCamera().position.x;
//        GameScreen.y_offset = GameScreen.getCamera().position.y;
//        GameScreen.start = System.nanoTime();
        return false;
    }

    /**
     * Called when a tap occured. A tap happens if a touch went down on the screen and was lifted again without moving outside
     * of the tap square. The tap square is a rectangular area around the initial touch position as specified on construction
     * time of the {@link com.badlogic.gdx.input.GestureDetector}.
     *
     * @param x
     * @param y
     * @param count  the number of taps.
     * @param button
     */
    @Override
    public boolean tap(float x, float y, int count, int button) {
        System.out.println("tap");
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    /**
     * Called when the user dragged a finger over the screen and lifted it. Reports the last known velocity of the finger in
     * pixels per second.
     *
     * @param velocityX velocity on x in seconds
     * @param velocityY velocity on y in seconds
     * @param button
     */
    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
//        System.out.println("fling " + velocityY);
        // TODO, Stop if X is bigger
        if(velocityY > 0){
            System.out.println("direction = down");
            if (GameSettings.noteindex > 0) {
                GameSettings.noteindex--;
            }
        }else if (velocityY < 0){
            System.out.println("direction = up");
            if (GameSettings.noteindex < 8) {
                GameSettings.noteindex++;
            }
        }

        // Create Ripple
        Ripple.updateRipple();
        return true;
    }

    /**
     * Called when the user drags a finger over the screen.
     *
     * @param x
     * @param y
     * @param deltaX the difference in pixels to the last drag event on x.
     * @param deltaY the difference in pixels to the last drag event on y.
     */
    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    /**
     * Called when no longer panning.
     *
     * @param x
     * @param y
     * @param pointer
     * @param button
     */
    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    /**
     * Called when the user performs a pinch zoom gesture. The original distance is the distance in pixels when the gesture
     * started.
     *
     * @param initialDistance distance between fingers when the gesture started.
     * @param distance        current distance between fingers.
     */
    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    /**
     * Called when a user performs a pinch zoom gesture. Reports the initial positions of the two involved fingers and their
     * current positions.
     *
     * @param initialPointer1
     * @param initialPointer2
     * @param pointer1
     * @param pointer2
     */
    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
