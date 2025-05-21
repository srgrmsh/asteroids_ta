package io.github.asteroids_ta.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.asteroids_ta.AsteroidsGame;
import io.github.asteroids_ta.constants.Constants;
import io.github.asteroids_ta.controllers.GameController;
import io.github.asteroids_ta.factory.GameControllerFactory;
import io.github.asteroids_ta.managers.GameStatManager;

public class GameScreen extends BaseScreen {
    private final GameController controller;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final BitmapFont font;
    private final Texture lifeIcon;

    public GameScreen(AsteroidsGame game, GameController controller) {
        super(game);
        this.controller = controller;
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera);
        this.font = new BitmapFont();
        this.font.getData().setScale(Constants.FONT_SCALE);
        this.font.setColor(Color.WHITE);
        this.lifeIcon = new Texture(Gdx.files.internal(Constants.LIFE_ICON_PATH));
    }

    @Override
    public void show() {
        if (disposed) {
            throw new IllegalStateException("GameScreen has been disposed");
        }
        font.setColor(Color.WHITE);
    }

    @Override
    protected void renderScreen(float delta) {
        controller.update(delta);

        camera.update();
        SpriteBatch batch = game.getBatch();
        batch.setProjectionMatrix(camera.combined);

        renderGameWorld(batch);

        renderUI(batch);
    }

    private void renderGameWorld(SpriteBatch batch) {
        controller.getPlayerController().render(batch);
        controller.getAsteroidController().render(batch);
        controller.getBulletController().render(batch);
    }

    private void renderUI(SpriteBatch batch) {
        int lives = controller.getGameStatManager().getLifeManager().getLives();
        for (int i = 0; i < lives; i++) {
            batch.draw(lifeIcon,
                Constants.UI_X_PADDING + i * Constants.LIFE_ICON_SPACING,
                Constants.WORLD_HEIGHT - Constants.LIFE_ICON_Y_OFFSET,
                Constants.LIFE_ICON_SIZE,
                Constants.LIFE_ICON_SIZE);
        }

        int score = controller.getGameStatManager().getScoreManager().getScore();
        font.draw(batch, "Score: " + score,
            Constants.UI_X_PADDING,
            Constants.WORLD_HEIGHT - Constants.SCORE_Y_OFFSET);
    }

    @Override
    public void resize(int width, int height) {
        if (disposed) {
            throw new IllegalStateException("GameScreen has been disposed");
        }
        viewport.update(width, height, true);
    }

    @Override
    protected void disposeScreen() {
        if (font != null) {
            font.dispose();
        }
        if (lifeIcon != null) {
            lifeIcon.dispose();
        }
    }
}
