package io.github.asteroids_ta.factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import io.github.asteroids_ta.constants.Constants;
import io.github.asteroids_ta.models.Player;

public class PlayerFactory implements GameObjectFactory<Player> {
    private final Texture playerTexture;
    private boolean disposed = false;

    public PlayerFactory() {
        this.playerTexture = new Texture(Gdx.files.internal(Constants.PLAYER_SHIP_PATH));
    }

    @Override
    public Player create() {
        if (disposed) {
            throw new IllegalStateException("PlayerFactory has been disposed");
        }
        return new Player();
    }

    public Player createAtPosition(float startX, float startY) {
        if (disposed) {
            throw new IllegalStateException("PlayerFactory has been disposed");
        }
        Player player = new Player();
        player.getPosition().set(startX, startY);
        return player;
    }


    public Texture getPlayerTexture() {
        if (disposed) {
            throw new IllegalStateException("PlayerFactory has been disposed");
        }
        return playerTexture;
    }

    @Override
    public void dispose() {
        if (!disposed) {
            if (playerTexture != null) {
                playerTexture.dispose();
            }
            disposed = true;
        }
    }

    @Override
    public boolean isDisposed() {
        return disposed;
    }
}
