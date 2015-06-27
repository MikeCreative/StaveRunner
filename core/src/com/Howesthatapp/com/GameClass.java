package com.Howesthatapp.com;

import com.Howesthatapp.com.Screens.GameScreen;
import com.Howesthatapp.com.Screens.MainMenu;
import com.Howesthatapp.com.Tools.SoundGenerator;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Sound;

public class GameClass extends Game {

//    public static IGoogleServices googleServices;

//    public MyGdxGame(IGoogleServices googleServices) {
//        super();
//        MyGdxGame.googleServices = googleServices;
//    }

    public static SoundGenerator soundGenerator;

    public GameClass(SoundGenerator generator){
        super();
        soundGenerator = generator;

    }

    @Override
    public void create() {
//        setScreen(new MainMenu());
        setScreen(new GameScreen());
    }


    @Override
    public void dispose(){
        super.dispose();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height){
        super.resize(width, height);
    }

    @Override
    public void pause(){
        super.pause();
    }

    @Override
    public void resume(){
        super.resume();
    }

}