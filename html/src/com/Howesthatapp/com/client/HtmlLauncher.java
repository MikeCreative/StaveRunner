package com.Howesthatapp.com.client;

import com.Howesthatapp.com.Tools.SoundGenerator;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.Howesthatapp.com.GameClass;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(480, 320);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new GameClass(new SoundGenerator() {
                    @Override
                    public void Generate() {

                    }

                    @Override
                    public void changeFreq(float freq) {

                    }
                });
        }
}