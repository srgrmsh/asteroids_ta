package io.github.asteroids_ta.screens;

import io.github.asteroids_ta.AsteroidsGame;
import io.github.asteroids_ta.controllers.MainMenuController;

public class MainMenuScreen extends BaseScreen {
    private final MainMenuController controller;

    public MainMenuScreen(AsteroidsGame game) {
        super(game);
        this.controller = new MainMenuController(game.getBatch());
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
