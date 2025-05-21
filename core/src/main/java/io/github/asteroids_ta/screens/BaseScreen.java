package io.github.asteroids_ta.screens;

import com.badlogic.gdx.Screen;
import io.github.asteroids_ta.AsteroidsGame;
import io.github.asteroids_ta.constants.Constants;
import io.github.asteroids_ta.managers.BackgroundManager;

public abstract class BaseScreen implements Screen {
    protected final AsteroidsGame game;
    protected final BackgroundManager backgroundManager;
    protected boolean disposed = false;

    public BaseScreen(AsteroidsGame game) {
        if (game == null) {
            throw new IllegalArgumentException("Game cannot be null");
        }
        this.game = game;
        this.backgroundManager = new BackgroundManager(Constants.BACKGROUND_TILE_PATH, Constants.BACKGROUND_TILE_SIZE);
    }

    @Override
    public void show() {
        // Default empty implementation
    }

    @Override
    public void render(float delta) {
        if (disposed) {
            throw new IllegalStateException(getClass().getSimpleName() + " has been disposed");
        }

        game.getBatch().begin();
        backgroundManager.render(game.getBatch());
        renderScreen(delta);
        game.getBatch().end();
    }

    protected abstract void renderScreen(float delta);

    @Override
    public void dispose() {
        if (!disposed) {
            disposeScreen();
            if (backgroundManager != null) {
                backgroundManager.dispose();
            }
            disposed = true;
        }
    }

    protected abstract void disposeScreen();

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    public boolean isDisposed() {
        return disposed;
    }
}
