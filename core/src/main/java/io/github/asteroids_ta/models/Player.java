package io.github.asteroids_ta.models;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import io.github.asteroids_ta.constants.Constants;

public class Player implements IGameObject {
    private Vector2 position;
    private final Vector2 velocity;
    private float rotation;
    private final Circle bounds;
    private boolean destroyed;
    private boolean disposed;

    public Player() {
        this(Constants.WORLD_WIDTH / 2f, Constants.WORLD_HEIGHT / 2f);
    }

    public Player(float startX, float startY) {
        this.position = new Vector2(startX, startY);
        this.velocity = new Vector2(0, 0);
        this.rotation = Constants.PLAYER_INITIAL_ROTATION;
        this.bounds = new Circle(position.x + Constants.PLAYER_RADIUS, position.y + Constants.PLAYER_RADIUS, Constants.PLAYER_RADIUS);
        this.destroyed = false;
        this.disposed = false;
    }

    @Override
    public void reset() {
        position = new Vector2(Constants.WORLD_WIDTH / 2f, Constants.WORLD_HEIGHT / 2f);
        velocity.set(0, 0);
        rotation = Constants.PLAYER_INITIAL_ROTATION;
        destroyed = false;
        bounds.setPosition(position.x + Constants.PLAYER_RADIUS, position.y + Constants.PLAYER_RADIUS);
    }

    @Override
    public void update(float delta) {
        updatePhysics(delta);
        updateBounds();
    }

    private void updatePhysics(float delta) {
        float currentSpeed = velocity.len();
        if (currentSpeed > Constants.PLAYER_SPEED) {
            velocity.scl(Constants.PLAYER_SPEED / currentSpeed);
        }

        position.add(velocity.x * delta, velocity.y * delta);

        wrapAroundScreen();

        velocity.scl(Constants.PLAYER_DAMPING);
    }

    private void wrapAroundScreen() {
        if (position.x < 0) position.x = Constants.WORLD_WIDTH;
        if (position.x > Constants.WORLD_WIDTH) position.x = 0;
        if (position.y < 0) position.y = Constants.WORLD_HEIGHT;
        if (position.y > Constants.WORLD_HEIGHT) position.y = 0;
    }

    private void updateBounds() {
        bounds.setPosition(position.x + Constants.PLAYER_RADIUS, position.y + Constants.PLAYER_RADIUS);
    }

    public void rotate(float degrees) {
        rotation += degrees;
    }

    public void thrust(float delta) {
        applyThrust(delta);
        limitSpeed();
    }

    private void applyThrust(float delta) {
        float radians = (float) Math.toRadians(rotation);
        float thrustX = (float) Math.cos(radians) * Constants.PLAYER_THRUST * delta;
        float thrustY = (float) Math.sin(radians) * Constants.PLAYER_THRUST * delta;
        velocity.add(thrustX, thrustY);
    }

    private void limitSpeed() {
        float currentSpeed = velocity.len();
        if (currentSpeed > Constants.PLAYER_SPEED) {
            velocity.scl(Constants.PLAYER_SPEED / currentSpeed);
        }
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    public float getRotation() {
        return rotation;
    }

    @Override
    public Circle getBoundingCircle() {
        return new Circle(position.x, position.y, Constants.PLAYER_RADIUS);
    }

    @Override
    public Circle getBounds() {
        return bounds;
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    @Override
    public void dispose() {
        if (!disposed) {
            disposed = true;
        }
    }

    @Override
    public boolean isDisposed() {
        return disposed;
    }
}
