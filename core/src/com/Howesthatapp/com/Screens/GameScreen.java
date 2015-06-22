package com.Howesthatapp.com.Screens;

import com.Howesthatapp.com.CustomGestureDetector;
import com.Howesthatapp.com.ShadersClass;
import com.Howesthatapp.com.Tools.ParticleStuff;
import com.Howesthatapp.com.Tools.lineAnimation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import javax.xml.soap.Text;

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

    // Starting Conditions
    private static int START = 4;

    // Positions
    public static float yPosition = 0;

    Mesh mesh, mesh2;
    Texture texture;
    Sprite sprite, sprite2;
    ShaderProgram rippleshader;

    Matrix4 matrix = new Matrix4();


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


        back = new Sprite(new Texture("Graphics/stavetest.jpg"));
        back.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        arrow = new Sprite(new Texture("Graphics/arrow.png"));
        arrow.setX(Gdx.graphics.getWidth()/2);
        yPosition = Gdx.graphics.getHeight()/2;
        arrow.setY(yPosition);

        rippleshader = new ShaderProgram(ShadersClass.vertexShader, ShadersClass.fragmentShader);
        if (!rippleshader.isCompiled()) {
            Gdx.app.log("ShaderTest", rippleshader.getLog());
            Gdx.app.exit();
        }


        ShaderProgram.pedantic = false;

        texture = new Texture(Gdx.files.internal("Graphics/grid.png"));
        sprite = new Sprite(texture);
        sprite.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprite2 = new Sprite(texture);
        sprite2.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprite2.setX(Gdx.graphics.getWidth() / 2 + 10);


               // TODO: Check if Open GL ES 2.0 support exists


        // Animations
        Texture antex = new Texture(Gdx.files.internal("Graphics/bar.png"));
        lineAnimation.createAnimation(antex, 6, 1);

        // Particle Effects
        ParticleStuff.createEffect();


        // Frame Buffer
        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, 1366, 768, false);

        start = System.nanoTime();
    }

    public static long start = 0;
    public static float x = 0;
    public static float y = 0;
    public static float x_offset = 0;
    public static float y_offset = 0;
    public static float x_actual = 0;
    public static float y_actual = 0;


    /**
     * Called when the screen should render itself.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {

//        camera.position.x += 1;

        frameBuffer.begin();
        fb.setProjectionMatrix(camera.combined);
        fb.begin();
//        sprite.draw(fb);
        ParticleStuff.renderEffect(delta, fb);
        lineAnimation.renderLineAnimation(fb);
        fb.end();
        frameBuffer.end();

        Sprite screenSprite = new Sprite(frameBuffer.getColorBufferTexture());
        screenSprite.setBounds(-Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


//        System.out.println("Delta" + x + " " + y);
        float newTime = (System.nanoTime() - start);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        rippleshader.setUniformf("resolution", Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        rippleshader.setUniformf("coords",x - x_offset + camera.position.x, y);
        rippleshader.setUniformf("time", newTime/1000000000);


        batch.setShader(rippleshader);
        screenSprite.draw(batch);
        batch.end();

        camera.update();

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
