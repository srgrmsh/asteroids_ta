package io.github.asteroids_ta.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.asteroids_ta.constants.Constants;
import io.github.asteroids_ta.controllers.BulletController;
import io.github.asteroids_ta.models.Player;

public class InputHandler {
    private final Player player;
    private final BulletController bulletController;

    public InputHandler(Player player, BulletController bulletController) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (bulletController == null) {
            throw new IllegalArgumentException("BulletController cannot be null");
        }
        this.player = player;
        this.bulletController = bulletController;
    }

    public void handleInput(float delta) {
        handleRotationInput(delta);
        handleThrustInput(delta);
        handleFireInput();
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

    private void handleFireInput() {
        if (isFiring()) {
            bulletController.fire(player);
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

    private boolean isFiring() {
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
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
