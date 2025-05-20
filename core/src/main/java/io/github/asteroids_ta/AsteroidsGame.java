package io.github.asteroids_ta;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.asteroids_ta.managers.ScreenManager;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class AsteroidsGame extends Game {
    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();

        ScreenManager.getInstance().initialize(this);
        ScreenManager.getInstance().showMenuScreen();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
