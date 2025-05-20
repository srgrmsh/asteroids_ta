package io.github.asteroids_ta.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.asteroids_ta.constants.Constants;
import io.github.asteroids_ta.models.Player;

public class InputHandler {
    private final Player player;

    public InputHandler(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        this.player = player;
    }

    public void handleInput(float delta) {
        handleRotationInput(delta);
        handleThrustInput(delta);
    }

    private void handleRotationInput(float delta) {
        if (isRotatingLeft()) {
            rotateLeft(delta);
        }
        if (isRotatingRight()) {
            rotateRight(delta);
        }
    }

    private void handleThrustInput(float delta) {
        if (isThrusting()) {
            applyThrust(delta);
        }
    }

    private boolean isRotatingLeft() {
        return Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A);
    }

    private boolean isRotatingRight() {
        return Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D);
    }

    private boolean isThrusting() {
        return Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W);
    }

    private void rotateLeft(float delta) {
        player.rotate(Constants.PLAYER_ROTATION_SPEED * delta);
    }

    private void rotateRight(float delta) {
        player.rotate(-Constants.PLAYER_ROTATION_SPEED * delta);
    }

    private void applyThrust(float delta) {
        player.thrust(Constants.PLAYER_THRUST * delta);
    }
}
