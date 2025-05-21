package io.github.asteroids_ta.factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import io.github.asteroids_ta.constants.Constants;
import io.github.asteroids_ta.models.Asteroid;

import static io.github.asteroids_ta.constants.Constants.SPAWN_OFFSET;

public class AsteroidFactory implements GameObjectFactory<Asteroid> {
    private final Array<Texture> asteroidTextures;
    private boolean disposed = false;

    public AsteroidFactory() {
        if (Constants.METEOR_TEXTURES == null || Constants.METEOR_TEXTURES.length == 0) {
            throw new IllegalStateException("METEOR_TEXTURES array is null or empty");
        }

        asteroidTextures = new Array<>();

        for (String texturePath : Constants.METEOR_TEXTURES) {
            if (texturePath == null || texturePath.trim().isEmpty()) {
                throw new IllegalStateException("Invalid texture path found in METEOR_TEXTURES array");
            }

            try {
                Texture texture = new Texture(Gdx.files.internal(texturePath));
                if (texture == null) {
                    throw new IllegalStateException("Failed to load texture: " + texturePath);
                }
                asteroidTextures.add(texture);
            } catch (Exception e) {
                throw new IllegalStateException("Failed to load texture: " + texturePath, e);
            }
        }

        if (asteroidTextures.size == 0) {
            throw new IllegalStateException("No valid asteroid textures found");
        }
    }

    @Override
    public Asteroid create() {
        return createRandomAsteroid();
    }

    public Asteroid createRandomAsteroid() {
        if (disposed) {
            throw new IllegalStateException("AsteroidFactory has been disposed");
        }

        if (asteroidTextures.size == 0) {
            throw new IllegalStateException("No textures available for asteroid creation");
        }

        Texture texture = asteroidTextures.random();
        if (texture == null) {
            throw new IllegalStateException("Failed to get random texture");
        }

        float startX, startY;

        switch (MathUtils.random(3)) {
            case 0:
                startX = -texture.getWidth() - SPAWN_OFFSET;
                startY = MathUtils.random(SPAWN_OFFSET, Constants.WORLD_HEIGHT - SPAWN_OFFSET);
                break;
            case 1:
                startX = Constants.WORLD_WIDTH + texture.getWidth() + SPAWN_OFFSET;
                startY = MathUtils.random(SPAWN_OFFSET, Constants.WORLD_HEIGHT - SPAWN_OFFSET);
                break;
            case 2:
                startX = MathUtils.random(SPAWN_OFFSET, Constants.WORLD_WIDTH - SPAWN_OFFSET);
                startY = Constants.WORLD_HEIGHT + texture.getHeight() + SPAWN_OFFSET;
                break;
            default:
                startX = MathUtils.random(SPAWN_OFFSET, Constants.WORLD_WIDTH - SPAWN_OFFSET);
                startY = -texture.getHeight() - SPAWN_OFFSET;
                break;
        }

        float angle = MathUtils.random(Constants.ASTEROID_MIN_ANGLE, Constants.ASTEROID_MAX_ANGLE);
        float speed = MathUtils.random(Constants.ASTEROID_MIN_SPEED, Constants.ASTEROID_MAX_SPEED);

        try {
            return new Asteroid(texture, startX, startY, speed, angle);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to create asteroid", e);
        }
    }

    @Override
    public void dispose() {
        if (!disposed) {
            for (Texture texture : asteroidTextures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
            asteroidTextures.clear();
            disposed = true;
        }
    }

    @Override
    public boolean isDisposed() {
        return disposed;
    }
}
