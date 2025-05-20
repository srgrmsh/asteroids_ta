package io.github.asteroids_ta.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.asteroids_ta.constants.Constants;

public class Asteroid implements IGameObject {
    private final Sprite sprite;
    private final Vector2 position;
    private final Vector2 velocity;
    private final float radius;
    private final Circle bounds;
    private boolean destroyed;
    private boolean disposed;

    public Asteroid(Texture texture, float startX, float startY, float speed, float angleDeg) {
        if (texture == null) {
            throw new IllegalArgumentException("Texture cannot be null");
        }

        this.sprite = new Sprite(texture);
        this.position = new Vector2(startX, startY);
        this.velocity = calculateVelocity(speed, angleDeg);
        this.sprite.setPosition(startX, startY);
        this.radius = calculateRadius();
        this.bounds = new Circle(position.x + radius, position.y + radius, radius);
        this.destroyed = false;
        this.disposed = false;
    }

    private Vector2 calculateVelocity(float speed, float angleDeg) {
        float radians = angleDeg * MathUtils.degreesToRadians;
        return new Vector2(MathUtils.cos(radians), MathUtils.sin(radians)).scl(speed);
    }

    private float calculateRadius() {
        return (sprite.getWidth() + sprite.getHeight()) / Constants.ASTEROID_RADIUS_DIVISOR;
    }

    @Override
    public void reset() {
    }

    @Override
    public void update(float delta) {
        updatePosition(delta);
        updateBounds();
    }

    private void updatePosition(float delta) {
        position.mulAdd(velocity, delta);
        sprite.setPosition(position.x, position.y);
    }

    private void updateBounds() {
        bounds.setPosition(position.x + radius, position.y + radius);
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public void dispose() {
        if (!disposed) {
            if (sprite != null && sprite.getTexture() != null) {
                sprite.getTexture().dispose();
            }
            disposed = true;
        }
    }

    @Override
    public Circle getBounds() {
        return bounds;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    public float getRadius() {
        return radius;
    }

    public Sprite getSprite() {
        return sprite;
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
    public Circle getBoundingCircle() {

        return new Circle(position.x + radius, position.y + radius, radius);
    }

    @Override
    public boolean isDisposed() {
        return disposed;
    }
}
