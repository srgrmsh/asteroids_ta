package io.github.asteroids_ta.controllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import io.github.asteroids_ta.factory.AsteroidFactory;
import io.github.asteroids_ta.managers.AsteroidSpawner;
import io.github.asteroids_ta.models.Asteroid;

public class AsteroidController {

    private final AsteroidSpawner asteroidSpawner;

    public AsteroidController(AsteroidFactory factory) {
        this.asteroidSpawner = new AsteroidSpawner(factory);
    }

    public void update(float delta) {
        asteroidSpawner.update(delta);
    }

    public void render(SpriteBatch batch) {
        asteroidSpawner.render(batch);
    }

    public void reset() {
        asteroidSpawner.reset();
    }

    public Array<Asteroid> getAsteroids() {
        return asteroidSpawner.getAsteroids();
    }

    public void dispose() {
        asteroidSpawner.dispose();
    }

    public void removeDestroyedAsteroids() {
        for (int i = asteroidSpawner.getAsteroids().size - 1; i >= 0; i--) {
            if (asteroidSpawner.getAsteroids().get(i).isDestroyed()) {
                asteroidSpawner.getAsteroids().removeIndex(i);
            }
        }
    }
}
