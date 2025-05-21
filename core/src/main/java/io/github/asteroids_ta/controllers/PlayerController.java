package io.github.asteroids_ta.controllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.asteroids_ta.constants.Constants;
import io.github.asteroids_ta.factory.PlayerFactory;
import io.github.asteroids_ta.models.Player;
import io.github.asteroids_ta.systems.InputHandler;

public class PlayerController {
    private final Player player;
    private final PlayerFactory playerFactory;
    private final TextureRegion playerTextureRegion;
    private final InputHandler inputHandler;
    private boolean disposed;

    public PlayerController(Player player, PlayerFactory playerFactory, BulletController bulletController) {
        this.player = player;
        this.playerFactory = playerFactory;
        this.playerTextureRegion = new TextureRegion(playerFactory.getPlayerTexture());
        this.inputHandler = new InputHandler(player, bulletController);
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
        float originX = playerFactory.getPlayerTexture().getWidth() / 2f;
        float originY = playerFactory.getPlayerTexture().getHeight() / 2f;
        batch.draw(
                playerTextureRegion,
                player.getPosition().x - originX,
                player.getPosition().y - originY,
                originX,
                originY,
                playerFactory.getPlayerTexture().getWidth(),
                playerFactory.getPlayerTexture().getHeight(),
                Constants.PLAYER_SCALE,
                Constants.PLAYER_SCALE,
                player.getRotation() - Constants.PLAYER_ROTATION_OFFSET
        );
    }

    public Player getPlayer() {
        if (disposed) {
            throw new IllegalStateException("PlayerController has been disposed");
        }
        return player;
    }

    public void reset() {
        if (disposed) {
            throw new IllegalStateException("PlayerController has been disposed");
        }
        player.reset();
    }

    public void dispose() {
        if (!disposed) {
            player.dispose();
            disposed = true;
        }
    }
}
