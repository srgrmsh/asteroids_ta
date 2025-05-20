package io.github.asteroids_ta.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.asteroids_ta.constants.Constants;
import io.github.asteroids_ta.models.Player;
import io.github.asteroids_ta.systems.InputHandler;

public class PlayerController {
    private final Player player;
    private final Texture playerTexture;
    private final InputHandler inputHandler;
    private final TextureRegion playerTextureRegion;
    private boolean disposed;

    public PlayerController(Player player) {
        this.player = player;
        this.playerTexture = new Texture(Gdx.files.internal(Constants.PLAYER_SHIP_PATH));
        this.playerTextureRegion = new TextureRegion(playerTexture);
        this.inputHandler = new InputHandler(player);
        this.disposed = false;
    }

    public void update(float delta) {
        if (disposed) {
            throw new IllegalStateException("PlayerController has been disposed");
        }
        inputHandler.handleInput(delta);
        player.update(delta);
    }

    public void render(SpriteBatch batch) {
        if (disposed) {
            throw new IllegalStateException("PlayerController has been disposed");
        }
        float originX = playerTexture.getWidth() / 2f;
        float originY = playerTexture.getHeight() / 2f;
        batch.draw(
            playerTextureRegion,
            player.getPosition().x - originX,
            player.getPosition().y - originY,
            originX,
            originY,
            playerTexture.getWidth(),
            playerTexture.getHeight(),
            Constants.PLAYER_SCALE,
            Constants.PLAYER_SCALE,
            player.getRotation() - Constants.PLAYER_ROTATION_OFFSET
        );
    }

    public Player getPlayer() {
        return player;
    }

    public void reset() {
        player.reset();
    }

    public void dispose() {
        if (!disposed) {
            player.dispose();
            if (playerTexture != null) {
                playerTexture.dispose();
            }
            disposed = true;
        }
    }

    public boolean isDisposed() {
        return disposed;
    }
}
