package io.github.asteroids_ta.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.asteroids_ta.constants.Constants;
import io.github.asteroids_ta.factory.AsteroidFactory;
import io.github.asteroids_ta.managers.GameStatManager;
import io.github.asteroids_ta.managers.ScreenManager;
import io.github.asteroids_ta.models.Player;
import io.github.asteroids_ta.systems.CollisionSystem;

public class GameController {
    private final PlayerController playerController;
    private final AsteroidController asteroidController;
    private final GameStatManager gameStatManager;
    private float timeElapsed = 0f;

    public GameController(SpriteBatch batch, GameStatManager gameStatManager) {
        this.gameStatManager = gameStatManager;

        Player player = new Player();
        this.playerController = new PlayerController(player);

        AsteroidFactory asteroidFactory = new AsteroidFactory(Gdx.files.internal(Constants.METEORS_PATH));
        this.asteroidController = new AsteroidController(asteroidFactory);

    }

    public void update(float delta) {
        playerController.update(delta);
        asteroidController.update(delta);

        // Update time-based score
        timeElapsed += delta;
        if (timeElapsed >= Constants.SCORE_INTERVAL) {
            gameStatManager.getScoreManager().addScore(Constants.SCORE_PER_INTERVAL);
            timeElapsed = 0f;
        }

        if (gameStatManager.getLifeManager().isDead()) {
            ScreenManager.getInstance().showGameOverScreen();
        }
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public AsteroidController getAsteroidController() {
        return asteroidController;
    }

    public GameStatManager getGameStatManager() {
        return gameStatManager;
    }

    public void dispose() {
        if (playerController != null) {
            playerController.dispose();
        }
        if (asteroidController != null) {
            asteroidController.dispose();
        }
    }

    public void reset() {
        playerController.getPlayer().reset();

        gameStatManager.reset();

        asteroidController.reset();

        timeElapsed = 0f;
    }
}
