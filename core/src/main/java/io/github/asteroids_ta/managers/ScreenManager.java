package io.github.asteroids_ta.managers;

import io.github.asteroids_ta.AsteroidsGame;
import io.github.asteroids_ta.controllers.GameController;
import io.github.asteroids_ta.screens.GameOverScreen;
import io.github.asteroids_ta.screens.GameScreen;
import io.github.asteroids_ta.screens.MainMenuScreen;

public class ScreenManager {

    private static ScreenManager instance;
    private AsteroidsGame game;
    private GameController gameController;

    private ScreenManager() {}

    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    public void initialize(AsteroidsGame game) {
        this.game = game;
    }

    public void showMenuScreen() {
        game.setScreen(new MainMenuScreen(game));
    }

    public void showGameScreen() {
        if (gameController == null) {
            gameController = new GameController(
                game.getBatch(),
                new GameStatManager(Constants.INITIAL_LIVES)
            );
        } else {
            gameController.reset();
        }
        game.setScreen(new GameScreen(game, gameController));
    }

    public void showGameOverScreen() {
        game.setScreen(new GameOverScreen(game));
    }

    public void dispose() {
        if (gameController != null) {
            gameController.dispose();
            gameController = null;
        }
    }
}
