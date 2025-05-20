package io.github.asteroids_ta.models;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public interface IGameObject {

    void reset();

    void update(float delta);

    Vector2 getPosition();

    Circle getBoundingCircle();

    Circle getBounds();

    boolean isDestroyed();

    void setDestroyed(boolean destroyed);

    void dispose();

    boolean isDisposed();
}
