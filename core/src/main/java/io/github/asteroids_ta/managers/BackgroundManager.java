package io.github.asteroids_ta.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import io.github.asteroids_ta.constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class BackgroundManager {
    private final Texture backgroundTile;
    private final TextureRegion tileRegion;
    private final List<Vector2> tilePositions;
    private final float tileSize;
    private boolean disposed = false;

    public BackgroundManager(String tilePath, float tileSize) {
        this.backgroundTile = new Texture(Gdx.files.internal(tilePath));
        this.backgroundTile.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        this.backgroundTile.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        this.tileRegion = new TextureRegion(backgroundTile);
        this.tileSize = tileSize;
        this.tilePositions = new ArrayList<>();
        initializeTiles();
    }

    private void initializeTiles() {
        int tilesX = (int) Math.ceil(Constants.WORLD_WIDTH / tileSize) + 1;
        int tilesY = (int) Math.ceil(Constants.WORLD_HEIGHT / tileSize) + 1;

        for (int x = 0; x < tilesX; x++) {
            for (int y = 0; y < tilesY; y++) {
                tilePositions.add(new Vector2(x * tileSize, y * tileSize));
            }
        }
    }

    public void render(SpriteBatch batch) {
        if (disposed) {
            throw new IllegalStateException("BackgroundManager has been disposed");
        }

        for (Vector2 position : tilePositions) {
            batch.draw(tileRegion,
                position.x,
                position.y,
                tileSize,
                tileSize);
        }
    }

    public void dispose() {
        if (!disposed) {
            if (backgroundTile != null) {
                backgroundTile.dispose();
            }
            disposed = true;
        }
    }

    public boolean isDisposed() {
        return disposed;
    }
}
