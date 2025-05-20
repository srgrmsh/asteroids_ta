package io.github.asteroids_ta.systems;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Array;
import io.github.asteroids_ta.controllers.AsteroidController;
import io.github.asteroids_ta.controllers.PlayerController;
import io.github.asteroids_ta.managers.GameStatManager;
import io.github.asteroids_ta.models.Asteroid;
import io.github.asteroids_ta.models.Player;

public class CollisionSystem {
    private final PlayerController playerController;
    private final AsteroidController asteroidController;
    private final GameStatManager statManager;

    public CollisionSystem(PlayerController playerController,
                           AsteroidController asteroidController,
                           GameStatManager statManager) {
        this.playerController = playerController;
        this.asteroidController = asteroidController;
        this.statManager = statManager;
    }

    public void update() {
        checkPlayerAsteroidCollisions();
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

    private boolean checkCollision(Circle player, Circle asteroid) {
        return player.overlaps(asteroid);
    }

    private void handlePlayerAsteroidCollision(Player player, Asteroid asteroid) {
        asteroid.setDestroyed(true);
        statManager.getLifeManager().loseLife();

        if (statManager.getLifeManager().getLives() <= 0) {
            player.setDestroyed(true);
        }
    }
}
