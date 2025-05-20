package io.github.asteroids_ta.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.asteroids_ta.AsteroidsGame;
import io.github.asteroids_ta.constants.Constants;
import io.github.asteroids_ta.controllers.GameController;

public class GameScreen extends BaseScreen {
    private final GameController controller;
    private final OrthographicCamera camera;
    private final FitViewport viewport;
    private Texture lifeIcon;
    private BitmapFont font;

    public GameScreen(AsteroidsGame game, GameController controller) {
        super(game);
        if (controller == null) {
            throw new IllegalArgumentException("Controller cannot be null");
        }
        this.controller = controller;
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera);
    }

    @Override
    public void show() {
        if (disposed) {
            throw new IllegalStateException("GameScreen has been disposed");
        }

        font = new BitmapFont();
        font.getData().setScale(Constants.FONT_SCALE);
        font.setColor(Color.WHITE);

        lifeIcon = new Texture(Gdx.files.internal(Constants.LIFE_ICON_PATH));
    }

    @Override
    protected void renderScreen(float delta) {
        controller.update(delta);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        // Render game elements
        controller.getPlayerController().render(game.getBatch());
        controller.getAsteroidController().render(game.getBatch());

        // Render lives
        int lives = controller.getGameStatManager().getLifeManager().getLives();
        for (int i = 0; i < lives; i++) {
            game.getBatch().draw(lifeIcon,
                Constants.UI_X_PADDING + i * Constants.LIFE_ICON_SPACING,
                Constants.WORLD_HEIGHT - Constants.LIFE_ICON_Y_OFFSET,
                Constants.LIFE_ICON_SIZE,
                Constants.LIFE_ICON_SIZE);
        }

        // Render score
        int score = controller.getGameStatManager().getScoreManager().getScore();
        font.draw(game.getBatch(), "Score: " + score,
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
