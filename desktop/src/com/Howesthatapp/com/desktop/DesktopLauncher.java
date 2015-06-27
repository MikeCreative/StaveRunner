package com.Howesthatapp.com.desktop;

import com.Howesthatapp.com.Tools.SoundGenerator;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.Howesthatapp.com.GameClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1366;
        config.height = 768;
		new LwjglApplication(new GameClass(new SoundGenerator() {
            @Override
            public void Generate() {
                System.out.println("Sound Was Generated");
            }

            @Override
            public void changeFreq(float freq) {

            }
        }), config);
	}
}
