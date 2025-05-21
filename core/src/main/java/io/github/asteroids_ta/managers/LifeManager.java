package io.github.asteroids_ta.managers;

public class LifeManager {
    private int lives;

    public LifeManager(int initLives) {
        this.lives = initLives;
    }

    public void reset(int initialLives) {
        this.lives = initialLives;
    }

    public void loseLife(){
        if (lives > 0){
            lives--;
        }
    }

    public int getLives() {
        return lives;
    }

    public boolean isDead(){
        return lives <= 0;
    }
}
