package io.github.asteroids_ta.managers;

import io.github.asteroids_ta.constants.Constants;

public class GameStatManager {
    private final ScoreManager scoreManager;
    private final LifeManager lifeManager;

    public GameStatManager(int startingLives) {
        this.scoreManager = new ScoreManager();
        this.lifeManager = new LifeManager(startingLives);
    }

    public ScoreManager getScoreManager() {
        return scoreManager;
    }

    public LifeManager getLifeManager() {
        return lifeManager;
    }

    public void reset() {
        scoreManager.reset();
        lifeManager.reset(Constants.INITIAL_LIVES);
    }

    public boolean isGameOver() {
        return lifeManager.isDead();
    }
}
