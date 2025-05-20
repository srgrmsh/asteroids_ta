package io.github.asteroids_ta.managers;

public class ScoreManager {
    private int score;

    public int getScore() {
        return score;
    }

    public void addScore(int amount) {
        score += amount;
    }

    public void reset() {
        score = 0;
    }
}
