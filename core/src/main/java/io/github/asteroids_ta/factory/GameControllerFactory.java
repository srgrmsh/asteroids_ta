package io.github.asteroids_ta.factory;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.asteroids_ta.constants.Constants;
import io.github.asteroids_ta.controllers.GameController;
import io.github.asteroids_ta.managers.GameStatManager;

public class GameControllerFactory {
    private static GameControllerFactory instance;
    private GameController currentController;

    private GameControllerFactory() {}

    public static GameControllerFactory getInstance() {
        if (instance == null) {
            instance = new GameControllerFactory();
        }
        return instance;
    }

    public void createController() {
        GameStatManager gameStatManager = new GameStatManager(Constants.INITIAL_LIVES);
        currentController = new GameController(gameStatManager);
    }

    public GameController getCurrentController() {
        return currentController;
    }

    public void resetCurrentController() {
        if (currentController != null) {
            currentController.reset();
        }
    }

    public void disposeCurrentController() {
        if (currentController != null) {
            currentController.dispose();
            currentController = null;
        }
    }
}
