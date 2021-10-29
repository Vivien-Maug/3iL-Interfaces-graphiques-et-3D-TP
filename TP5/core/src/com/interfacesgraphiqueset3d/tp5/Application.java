package com.interfacesgraphiqueset3d.tp5;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Application extends ApplicationAdapter {
    private FitViewport viewport;
    private PerspectiveCamera camera;
    private Pixmap pixels;
    private Texture textureWithPixels;
    private SpriteBatch spriteBatch;
    private Vector2 currentScreen;
    private Vector3 currentScene;
    private Scene scene;
    private int antiAlia = 0;

    @Override
    public void create() {
        // Get screen dimensions, in pixels :
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        // Create a camera with perspective view :
        camera = new PerspectiveCamera(50.0f, screenWidth, screenHeight);
        camera.position.set(0f, 0f, -10f);
        camera.lookAt(0, 0, 0);
        camera.near = 1f;
        camera.far = 500f;
        camera.update();

        // Create a viewport to convert coords of screen space into coords of scene
        // space.
        viewport = new FitViewport(screenWidth, screenHeight, camera);

        // Create an array of pixels, initialized with grey color :
        pixels = Pixmap.createFromFrameBuffer(0, 0, screenWidth, screenHeight);
        for (int y = 0; y < screenHeight; y++) {
            for (int x = 0; x < screenWidth; x++) {
                pixels.setColor(0.1f, 0.1f, 0.1f, 1f);
                pixels.drawPixel(x, y);
            }
        }

        // Add pixels in a Texture in order to render them :
        spriteBatch = new SpriteBatch();
        textureWithPixels = new Texture(pixels);

        // Initialize coords of the first pixel, in screen space :
        currentScreen = new Vector2(0, 0);

        // Others initializations :
        currentScene = new Vector3();
        scene = new Scene();
    }

    @Override
    public void render() {
        // If "ctrl + s" is pressed :
        if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyPressed(Keys.S)) {
            // Save the pixels into a png file :
            savePixelsInPngFile();
        }

        // Same as before, but for me there is a bug and ctrl is not trigger
        if (Gdx.input.isKeyPressed(Keys.ALT_LEFT) && Gdx.input.isKeyPressed(Keys.S)) {
            // Save the pixels into a png file :
            savePixelsInPngFile();
        }

        // If "escape" is pressed :
        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            // Close th application :
            Gdx.app.exit();
        }

        // Reset the screen buffer colors :
        ScreenUtils.clear(0, 0, 0, 1);

        // Process pixels color :
        processPixel();
        textureWithPixels.draw(pixels, 0, 0);
        // Render the texture with pixels :
        spriteBatch.begin();
        spriteBatch.draw(textureWithPixels, 0, 0);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        textureWithPixels.dispose();
        pixels.dispose();
    }

    /**
     * Compute the color of each screen pixels and store the results in the pixels
     * map.
     */
    private boolean processPixel() {
        boolean isOk = true;

        // Get color of current pixel :
        Color color = Color.BLACK;

        for (int y = 0; y < pixels.getHeight(); y++) {
            for (int x = 0; x < pixels.getWidth(); x++) {
                color = getColor(x, y);
                pixels.setColor(color);
                pixels.drawPixel(x, y);
            }
        }

        // scene.drawImage(pixels, camera, 1);

        return isOk;
    }

    /**
     * Wrire pixels in the png file "core/assets/render.png". If the file already
     * exists it will be overrided.
     */
    private boolean savePixelsInPngFile() {
        boolean isOk = true;

        // Create file :
        FileHandle file = Gdx.files.local("render.png");

        // Write pixels in file :
        PixmapIO.writePNG(file, pixels);

        return isOk;
    }

    /**
     * Return the color processed with path tracing and Phong method for the given
     * pixel.
     */
    private Color getColor(int xScreen, int yScreen) {
        Color color = new Color(0.0f, 0.0f, 0.0f, 1.0f);

        Ray ray = new Ray();
        for (int i = 0; i <= antiAlia; i++) {

            currentScene = camera.unproject(new Vector3(xScreen, yScreen, 1));

            ray = new Ray(camera.position, currentScene);

            // Color Found :
            color = color.add(scene.lauchnRay(ray, 1));
        }
        color = color.mul(1 / (antiAlia + 1.0f));

        return color;
    }
}