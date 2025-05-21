package io.github.asteroids_ta.screens;

import io.github.asteroids_ta.AsteroidsGame;
import io.github.asteroids_ta.controllers.GameOverController;
import io.github.asteroids_ta.factory.GameControllerFactory;

public class GameOverScreen extends BaseScreen {
    private final GameOverController controller;

    public GameOverScreen(AsteroidsGame game) {
        super(game);
        int finalScore = GameControllerFactory.getInstance().getCurrentController().getGameStatManager().getScoreManager().getScore();
        this.controller = new GameOverController(game.getBatch(), finalScore);
    }

    @Override
    protected void renderScreen(float delta) {
        controller.update();
        controller.render();
    }

    @Override
    protected void disposeScreen() {
        if (controller != null) {
            controller.dispose();
        }
    }
}
