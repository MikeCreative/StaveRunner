package com.Howesthatapp.com;

import com.Howesthatapp.com.Screens.GameScreen;
import com.Howesthatapp.com.Tools.Ripple;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Mike on 27/06/2015.
 *
 *
 */
public class MusicCharacter {

    private static Sprite arrow;


    public static void createCharacter(){
        arrow = new Sprite(new Texture("Graphics/play.png"));
        arrow.setY(-GameSettings.yCharacterOffset);
        arrow.setOrigin(arrow.getWidth()/2, arrow.getHeight()/2);


    }

    public static void renderCharacter(SpriteBatch fb){
        arrow.setX(GameScreen.getCamera().position.x + GameSettings.xCharacterPosition);
        arrow.setY(GameSettings.notes[GameSettings.noteindex] - GameSettings.yCharacterOffset);

        if (characterMove){
            characterYPosition += characterPositionUpdate;  // Update characterYPosition
            if (Math.abs(characterYPosition - (GameSettings.notes[GameSettings.noteindex] - GameSettings.yCharacterOffset)) > 5) {
                arrow.setY(characterYPosition);
//                arrow.scale(0.1f);
            } else {        // Character Move successful
                arrow.setY(GameSettings.notes[GameSettings.noteindex] - GameSettings.yCharacterOffset);
                characterMove = false;
//                arrow.scale(0.5f);
                Ripple.updateRipple();
            }
        }


        arrow.draw(fb);

    }

    public static boolean characterMove = false;
    public static float characterMoveSpeed = 5;
    public static float characterPositionUpdate = 0;
    public static float characterYPosition = 0;

    public static void updateCharacterPosition(int lastposition, int newposition){
        characterMove = true;   // Update boolean value
        if (newposition > lastposition){
            characterPositionUpdate = characterMoveSpeed;
        } else {
            characterPositionUpdate = -characterMoveSpeed;
        }
        characterYPosition = GameSettings.notes[lastposition] - GameSettings.yCharacterOffset;

    }


    public static Sprite getSprite() {
        return arrow;
    }
}
