package io.github.asteroids_ta.controllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import io.github.asteroids_ta.constants.Constants;
import io.github.asteroids_ta.models.Bullet;
import io.github.asteroids_ta.models.Player;

public class BulletController {
    private final Array<Bullet> bullets;
    private final Texture bulletTexture;
    private float lastFireTime;
    private boolean disposed;

    public BulletController() {
        this.bullets = new Array<>();
        this.bulletTexture = new Texture(Constants.BULLET_TEXTURE_PATH);
        this.lastFireTime = Constants.BULLET_FIRE_RATE;
        this.disposed = false;
    }

    public void update(float delta) {
        lastFireTime += delta;
        
        for (int i = bullets.size - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
            bullet.update(delta);
            
            if (bullet.isDestroyed()) {
                bullet.dispose();
                bullets.removeIndex(i);
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Bullet bullet : bullets) {
            bullet.render(batch);
        }
    }

    public boolean canFire() {
        return lastFireTime >= Constants.BULLET_FIRE_RATE;
    }

    public void fire(Player player) {
        if (!canFire()) {
            return;
        }

        Vector2 position = player.getPosition();
        float rotation = player.getRotation();
        
        Bullet bullet = new Bullet(bulletTexture, position.x, position.y, rotation);
        bullets.add(bullet);
        lastFireTime = 0f;
    }

    public Array<Bullet> getBullets() {
        return bullets;
    }

    public void reset() {
        for (Bullet bullet : bullets) {
            bullet.dispose();
        }
        bullets.clear();
        lastFireTime = 0f;
    }

    public void dispose() {
        if (!disposed) {
            for (Bullet bullet : bullets) {
                bullet.dispose();
            }
            bullets.clear();
            if (bulletTexture != null) {
                bulletTexture.dispose();
            }
            disposed = true;
        }
    }
} 