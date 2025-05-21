package io.github.asteroids_ta.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.asteroids_ta.models.IGameObject;

public interface Spawner<T extends IGameObject> {

    void update(float delta);

    void render(SpriteBatch batch);

    void reset();

    void dispose();

    boolean isDisposed();
}
