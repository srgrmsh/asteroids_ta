package io.github.asteroids_ta.systems;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Array;
import io.github.asteroids_ta.constants.Constants;
import io.github.asteroids_ta.controllers.AsteroidController;
import io.github.asteroids_ta.controllers.BulletController;
import io.github.asteroids_ta.controllers.PlayerController;
import io.github.asteroids_ta.managers.GameStatManager;
import io.github.asteroids_ta.models.Asteroid;
import io.github.asteroids_ta.models.Bullet;
import io.github.asteroids_ta.models.Player;

public class CollisionSystem {
    private final PlayerController playerController;
    private final AsteroidController asteroidController;
    private final BulletController bulletController;
    private final GameStatManager statManager;

    public CollisionSystem(PlayerController playerController,
                           AsteroidController asteroidController,
                           BulletController bulletController,
                           GameStatManager statManager) {
        this.playerController = playerController;
        this.asteroidController = asteroidController;
        this.bulletController = bulletController;
        this.statManager = statManager;
    }

    public void update() {
        checkPlayerAsteroidCollisions();
        checkBulletAsteroidCollisions();
    }

    private void checkPlayerAsteroidCollisions() {
        Player player = playerController.getPlayer();
        if (player == null || player.isDestroyed()) {
            return;
        }

        Circle playerBounds = player.getBoundingCircle();
        Array<Asteroid> asteroids = asteroidController.getAsteroids();

        for (Asteroid asteroid : asteroids) {
            if (asteroid == null || asteroid.isDestroyed()) {
                continue;
            }

            if (checkCollision(playerBounds, asteroid.getBounds())) {
                handlePlayerAsteroidCollision(player, asteroid);
                break;
            }
        }

        asteroidController.removeDestroyedAsteroids();
    }

    private void checkBulletAsteroidCollisions() {
        Array<Bullet> bullets = bulletController.getBullets();
        Array<Asteroid> asteroids = asteroidController.getAsteroids();

        for (Bullet bullet : bullets) {
            if (bullet == null || bullet.isDestroyed()) {
                continue;
            }

            for (Asteroid asteroid : asteroids) {
                if (asteroid == null || asteroid.isDestroyed()) {
                    continue;
                }

                if (checkCollision(bullet.getBounds(), asteroid.getBounds())) {
                    handleBulletAsteroidCollision(bullet, asteroid);
                    break;
                }
            }
        }

        asteroidController.removeDestroyedAsteroids();
    }

    private boolean checkCollision(Circle circle1, Circle circle2) {
        return circle1.overlaps(circle2);
    }

    private void handlePlayerAsteroidCollision(Player player, Asteroid asteroid) {
        asteroid.setDestroyed(true);
        statManager.getLifeManager().loseLife();

        if (statManager.getLifeManager().getLives() <= 0) {
            player.setDestroyed(true);
        }
    }

    private void handleBulletAsteroidCollision(Bullet bullet, Asteroid asteroid) {
        bullet.setDestroyed(true);
        asteroid.setDestroyed(true);
        statManager.getScoreManager().addScore(Constants.SCORE_PER_ASTEROID);
    }
}
