package io.github.asteroids_ta.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.asteroids_ta.constants.Constants;

public class Bullet implements IGameObject {
    private final Sprite sprite;
    private final Vector2 position;
    private final Vector2 velocity;
    private final float radius;
    private final Circle bounds;
    private boolean destroyed;
    private boolean disposed;

    public Bullet(Texture texture, float startX, float startY, float angleDeg) {
        if (texture == null) {
            throw new IllegalArgumentException("Texture cannot be null");
        }

        this.sprite = new Sprite(texture);
        this.position = new Vector2(startX, startY);
        this.velocity = calculateVelocity(angleDeg);

        float originX = texture.getWidth() / 2f;
        float originY = texture.getHeight() / 2f;
        this.sprite.setOrigin(originX, originY);
        this.sprite.setPosition(startX - originX, startY - originY);
        this.sprite.setRotation(angleDeg + 90f);
        this.sprite.setScale(Constants.BULLET_SCALE);

        this.radius = calculateRadius();
        this.bounds = new Circle(position.x + radius, position.y + radius, radius);
        this.destroyed = false;
        this.disposed = false;
    }

    private Vector2 calculateVelocity(float angleDeg) {
        float radians = angleDeg * MathUtils.degreesToRadians;
        return new Vector2(MathUtils.cos(radians), MathUtils.sin(radians)).scl(Constants.BULLET_SPEED);
    }

    private float calculateRadius() {
        return (sprite.getWidth() + sprite.getHeight()) / Constants.BULLET_RADIUS_DIVISOR;
    }

    @Override
    public void reset() {
    }

    @Override
    public void update(float delta) {
        updatePosition(delta);
        updateBounds();
        checkScreenBounds();
    }

    private void updatePosition(float delta) {
        position.mulAdd(velocity, delta);
        float originX = sprite.getWidth() / 2f;
        float originY = sprite.getHeight() / 2f;
        sprite.setPosition(position.x - originX, position.y - originY);
    }

    private void updateBounds() {
        bounds.setPosition(position.x + radius, position.y + radius);
    }

    private void checkScreenBounds() {
        if (position.x < 0 || position.x > Constants.WORLD_WIDTH ||
            position.y < 0 || position.y > Constants.WORLD_HEIGHT) {
            destroyed = true;
        }
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public void dispose() {
        disposed = true;
    }

    @Override
    public Circle getBounds() {
        return bounds;
    }

    @Override
    public Vector2 getPosition() {
        return position;
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
