package io.github.asteroids_ta.screens;

import io.github.asteroids_ta.AsteroidsGame;

public class GameOverScreen extends BaseScreen {
    private final GameOverController controller;

    public GameOverScreen(AsteroidsGame game) {
        super(game);
        this.controller = new GameOverController(game.getBatch());
    }

    @Override
    protected void renderScreen(float delta) {

    }

    @Override
    protected void disposeScreen() {

    }
}
