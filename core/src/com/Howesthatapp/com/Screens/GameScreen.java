package com.Howesthatapp.com.Screens;

import com.Howesthatapp.com.CustomGestureDetector;
import com.Howesthatapp.com.GameSettings;
import com.Howesthatapp.com.ShadersClass;
import com.Howesthatapp.com.Tools.BarParticles;
import com.Howesthatapp.com.Tools.CameraHandler;
import com.Howesthatapp.com.Tools.Oscillator;
import com.Howesthatapp.com.Tools.TailParticles;
import com.Howesthatapp.com.Tools.lineAnimation;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Matrix4;

import java.util.Random;

import sun.audio.AudioDevice;

/**
 * Created by Mike on 14/06/2015.
 *
 *
 */
public class GameScreen implements Screen {

    private static OrthographicCamera camera;
    private static SpriteBatch batch, fb;
    private static Sprite back;
    private static Sprite arrow;


    Mesh mesh, mesh2;
    Texture texture;
    ShaderProgram rippleshader;

    // Frame Buffer
    FrameBuffer frameBuffer;

    public static OrthographicCamera getCamera() {
        return camera;
    }

    /**
     * Called when this screen becomes the current screen for a .
     */
    @Override
    public void show() {

        // Create Camera
        camera = new OrthographicCamera(1366, 768);

        batch = new SpriteBatch();
        fb = new SpriteBatch();

        Gdx.input.setInputProcessor(new GestureDetector(new CustomGestureDetector()));

        back = new Sprite(new Texture("Graphics/back2.png"));
        back.setBounds(-Gdx.graphics.getWidth() / 2, -Gdx.graphics.getHeight() / 2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        arrow = new Sprite(new Texture("Graphics/play.png"));
        arrow.setY(-GameSettings.yCharacterOffset);

        rippleshader = new ShaderProgram(ShadersClass.vertexShader, ShadersClass.fragmentShader);
        if (!rippleshader.isCompiled()) {
            Gdx.app.log("ShaderTest", rippleshader.getLog());
            Gdx.app.exit();
        }


        ShaderProgram.pedantic = false;

        // TODO: Check if Open GL ES 2.0 support exists


        // Animations
        Texture antex = new Texture(Gdx.files.internal("Graphics/bar.png"));
        lineAnimation.createAnimation(antex, 6, 1);

        // Particle Effects
        BarParticles.createEffect();
        TailParticles.createEffect();

        // Frame Buffer
        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, 1366, 768, false);

        start = System.nanoTime();

        device = Gdx.audio.newAudioDevice(44100, true);

        Oscillator.generateSound();


    }

    public static com.badlogic.gdx.audio.AudioDevice device;

    public static long start = 0;
    public static float x = 0;
    public static float y = 0;
    public static float x_offset = 0;
    public static float y_offset = 0;


    // X Character Location
    private static Random random = new Random();


    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {

        //TODO: Interpolation so that this is smooth
//        float positionShift = random.nextFloat() - 0.5f;
//
//        if ((GameSettings.xCharacterPosition + positionShift < GameSettings.xCharacterMax) && (GameSettings.xCharacterPosition + positionShift > GameSettings.xCharacterMin)){
//            GameSettings.xCharacterPosition += positionShift;
//        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        GameSettings.xPosition += 3;    // TODO: Do this after render
//        camera.zoom += .01f;

        camera.position.x = GameSettings.xPosition;
        back.setBounds((-Gdx.graphics.getWidth() / 2) * camera.zoom + camera.position.x, (-Gdx.graphics.getHeight() / 2) * camera.zoom, Gdx.graphics.getWidth() * camera.zoom, Gdx.graphics.getHeight() * camera.zoom);

        frameBuffer.begin();
        fb.setProjectionMatrix(camera.combined);
        fb.begin();
        back.draw(fb);
        lineAnimation.renderLineAnimation(fb);
        fb.end();
        frameBuffer.end();

        Sprite screenSprite = new Sprite(frameBuffer.getColorBufferTexture());
        screenSprite.setBounds((-Gdx.graphics.getWidth() / 2) * camera.zoom + camera.position.x, (-Gdx.graphics.getHeight() / 2) * camera.zoom, Gdx.graphics.getWidth() * camera.zoom, Gdx.graphics.getHeight() * camera.zoom);


        float newTime = (System.nanoTime() - start);


        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        rippleshader.setUniformf("resolution", Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        rippleshader.setUniformf("coords", x - x_offset + camera.position.x, y);
        rippleshader.setUniformf("time", newTime / 1000000000);

        arrow.setX(camera.position.x + GameSettings.xCharacterPosition);
        arrow.setY(GameSettings.notes[GameSettings.noteindex] - GameSettings.yCharacterOffset);

        batch.setShader(rippleshader);
        screenSprite.draw(batch);
        BarParticles.renderEffect(delta, batch);
        batch.end();

        fb.begin();
        arrow.draw(fb);
        TailParticles.renderEffect(delta, fb);
        fb.end();

        CameraHandler.updateCamera();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        mesh.dispose();
        texture.dispose();
        rippleshader.dispose();

    }
}
