package io.github.asteroids_ta.factory;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import io.github.asteroids_ta.constants.Constants;
import io.github.asteroids_ta.models.Asteroid;

public class AsteroidFactory implements GameObjectFactory<Asteroid> {
    private final Array<Texture> asteroidTextures;
    private boolean disposed = false;
    private static final float SPAWN_OFFSET = 100f; // Safe distance from screen edges

    public AsteroidFactory(FileHandle textureDirectory) {
        if (textureDirectory == null || !textureDirectory.exists() || !textureDirectory.isDirectory()) {
            throw new IllegalArgumentException("Invalid texture directory: " + textureDirectory);
        }

        asteroidTextures = new Array<>();
        FileHandle[] files = textureDirectory.list();

        for (FileHandle file : files) {
            if (file.extension().equals("png")) {
                asteroidTextures.add(new Texture(file));
            }
        }

        if (asteroidTextures.size == 0) {
            throw new IllegalStateException("No valid asteroid textures found in directory: " + textureDirectory);
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

        Texture texture = asteroidTextures.random();
        float startX, startY;

        switch (MathUtils.random(3)) {
            case 0: // Left edge
                startX = -texture.getWidth() - SPAWN_OFFSET;
                startY = MathUtils.random(SPAWN_OFFSET, Constants.WORLD_HEIGHT - SPAWN_OFFSET);
                break;
            case 1: // Right edge
                startX = Constants.WORLD_WIDTH + texture.getWidth() + SPAWN_OFFSET;
                startY = MathUtils.random(SPAWN_OFFSET, Constants.WORLD_HEIGHT - SPAWN_OFFSET);
                break;
            case 2: // Top edge
                startX = MathUtils.random(SPAWN_OFFSET, Constants.WORLD_WIDTH - SPAWN_OFFSET);
                startY = Constants.WORLD_HEIGHT + texture.getHeight() + SPAWN_OFFSET;
                break;
            default: // Bottom edge
                startX = MathUtils.random(SPAWN_OFFSET, Constants.WORLD_WIDTH - SPAWN_OFFSET);
                startY = -texture.getHeight() - SPAWN_OFFSET;
                break;
        }

        float angle = MathUtils.random(0f, 360f);
        float speed = MathUtils.random(50f, 150f);

        return new Asteroid(texture, startX, startY, speed, angle);
    }

    @Override
    public void dispose() {
        if (!disposed) {
            for (Texture texture : asteroidTextures) {
                texture.dispose();
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
