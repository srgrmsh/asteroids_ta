package io.github.asteroids_ta.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.asteroids_ta.AsteroidsGame;
import io.github.asteroids_ta.constants.Constants;
import io.github.asteroids_ta.controllers.GameController;

public class GameScreen extends BaseScreen{
    private final GameController controller;
    private final OrthographicCamera camera;
    private final FitViewport viewport;
    private BitmapFont font;

    public GameScreen(AsteroidsGame game, GameController controller) {
        super(game);

        this.controller = controller;
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera);
    }

    @Override
    public void show() {
        font = new BitmapFont();
        font.getData().setScale(Constants.FONT_SCALE);
        font.setColor(Color.WHITE);
    }

    @Override
    protected void renderScreen(float delta) {
        controller.update(delta);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    protected void disposeScreen() {
        if (font != null) {
            font.dispose();
        }
    }
}
