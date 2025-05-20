package io.github.asteroids_ta.constants;

public class Constants {
    public static final float WORLD_WIDTH = 1280;
    public static final float WORLD_HEIGHT = 720;

    public static final float PLAYER_RADIUS = 37.5f;
    public static final float PLAYER_INITIAL_ROTATION = 90f;
    public static final float PLAYER_SPEED = 400f;
    public static final float PLAYER_DAMPING = 0.985f;
    public static final float PLAYER_THRUST = 30f;

    public static final int INITIAL_LIVES = 3;
    public static final float ASTEROID_RADIUS_DIVISOR = 4f;

    public static final float FONT_SCALE = 2f;

    public static final String BACKGROUND_TILE_PATH = "assets/Backgrounds/black.png";
    public static final float BACKGROUND_TILE_SIZE = 256f;

    public static final String MENU_TITLE = "ASTEROIDS";
    public static final String MENU_START_TEXT = "Press SPACE to Start Game";
    public static final String GAME_OVER_TITLE = "Game Over";
    public static final String GAME_OVER_TEXT = "Press SPACE to return to Menu or R to rerun";
    public static final float MENU_TITLE_OFFSET = 50f;
    public static final float MENU_TEXT_OFFSET = -50f;
}
