package io.github.asteroids_ta.controllers;

import io.github.asteroids_ta.constants.Constants;
import io.github.asteroids_ta.factory.AsteroidFactory;
import io.github.asteroids_ta.factory.PlayerFactory;
import io.github.asteroids_ta.managers.GameStatManager;
import io.github.asteroids_ta.managers.ScreenManager;
import io.github.asteroids_ta.models.Player;
import io.github.asteroids_ta.systems.CollisionSystem;
import io.github.asteroids_ta.systems.InputHandler;

public class GameController {
    private final PlayerController playerController;
    private final AsteroidController asteroidController;
    private final BulletController bulletController;
    private final CollisionSystem collisionSystem;
    private final GameStatManager gameStatManager;
    private final PlayerFactory playerFactory;
    private final InputHandler inputHandler;
    private float timeElapsed = 0f;

    public GameController(GameStatManager gameStatManager) {
        this.gameStatManager = gameStatManager;

        this.playerFactory = new PlayerFactory();
        Player player = playerFactory.createAtPosition(Constants.WORLD_WIDTH / 2f, Constants.WORLD_HEIGHT / 2f);
        
        this.bulletController = new BulletController();
        this.playerController = new PlayerController(player, playerFactory, bulletController);

        AsteroidFactory asteroidFactory = new AsteroidFactory();
        this.asteroidController = new AsteroidController(asteroidFactory);

        this.collisionSystem = new CollisionSystem(playerController, asteroidController, bulletController, gameStatManager);
        this.inputHandler = new InputHandler(player, bulletController);
    }

    public void update(float delta) {
        inputHandler.handleInput(delta);
        playerController.update(delta);
        asteroidController.update(delta);
        bulletController.update(delta);
        collisionSystem.update();

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

    public BulletController getBulletController() {
        return bulletController;
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
        if (bulletController != null) {
            bulletController.dispose();
        }
        if (playerFactory != null) {
            playerFactory.dispose();
        }
    }

    public void reset() {
        playerController.getPlayer().reset();
        gameStatManager.reset();
        asteroidController.reset();
        bulletController.reset();
        timeElapsed = 0f;
    }
}
