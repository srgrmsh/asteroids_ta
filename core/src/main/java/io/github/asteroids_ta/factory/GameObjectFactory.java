package io.github.asteroids_ta.factory;

import io.github.asteroids_ta.models.IGameObject;

public interface GameObjectFactory<T extends IGameObject> {
    T create();

    void dispose();

    boolean isDisposed();
}
