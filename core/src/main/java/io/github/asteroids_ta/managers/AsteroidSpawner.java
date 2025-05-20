package io.github.asteroids_ta.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import io.github.asteroids_ta.constants.Constants;
import io.github.asteroids_ta.factory.AsteroidFactory;
import io.github.asteroids_ta.models.Asteroid;
import io.github.asteroids_ta.systems.ScreenWrapper;

public class AsteroidSpawner implements Spawner<Asteroid> {
    private final Array<Asteroid> asteroids = new Array<>();
    private final AsteroidFactory asteroidFactory;
    private float spawnTimer = 0f;
    private boolean disposed = false;

    public AsteroidSpawner(AsteroidFactory asteroidFactory) {
        this.asteroidFactory = asteroidFactory;
    }

    @Override
    public void update(float delta) {
        spawnTimer += delta;

        if (spawnTimer >= Constants.ASTEROID_SPAWN_INTERVAL && asteroids.size < Constants.MAX_ASTEROIDS) {
            asteroids.add(asteroidFactory.create());
            spawnTimer = 0;
        }

        for (Asteroid asteroid : asteroids) {
            asteroid.update(delta);
            ScreenWrapper.wrapObject(asteroid);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        for (Asteroid asteroid : asteroids) {
            asteroid.render(batch);
        }
    }

    @Override
    public void reset() {
        asteroids.clear();
        spawnTimer = 0f;
    }

    @Override
    public void dispose() {
        if (!disposed) {
            for (Asteroid asteroid : asteroids) {
                asteroid.dispose();
            }
            asteroids.clear();
            disposed = true;
        }
    }

    public Array<Asteroid> getAsteroids() {
        return asteroids;
    }

    @Override
    public boolean isDisposed() {
        return disposed;
    }
}
