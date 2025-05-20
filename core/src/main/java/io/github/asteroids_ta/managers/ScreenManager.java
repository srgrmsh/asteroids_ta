package io.github.asteroids_ta.managers;

import io.github.asteroids_ta.AsteroidsGame;
import io.github.asteroids_ta.factory.GameControllerFactory;
import io.github.asteroids_ta.screens.GameOverScreen;
import io.github.asteroids_ta.screens.GameScreen;
import io.github.asteroids_ta.screens.MainMenuScreen;

public class ScreenManager {
    private static volatile ScreenManager instance;
    private AsteroidsGame game;

    private ScreenManager() {}

    public static ScreenManager getInstance() {
        if (instance == null) {
            synchronized (ScreenManager.class) {
                if (instance == null) {
                    instance = new ScreenManager();
                }
            }
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

        GameControllerFactory factory = GameControllerFactory.getInstance();
        if (factory.getCurrentController() == null) {
            factory.createController(game.getBatch());
        } else {
            factory.resetCurrentController();
        }

        game.setScreen(new GameScreen(game, factory.getCurrentController()));
    }

    public void showGameOverScreen() {
        game.setScreen(new GameOverScreen(game));
    }

    public void dispose() {
        GameControllerFactory.getInstance().disposeCurrentController();
    }
}
