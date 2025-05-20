package io.github.asteroids_ta.constants;

public class Constants {
    public static final float WORLD_WIDTH = 1280;
    public static final float WORLD_HEIGHT = 720;

    public static final float PLAYER_RADIUS = 37.5f;
    public static final float PLAYER_INITIAL_ROTATION = 90f;
    public static final float PLAYER_SPEED = 400f;
    public static final float PLAYER_DAMPING = 0.985f;
    public static final float PLAYER_THRUST = 30f;
    public static final float PLAYER_SCALE = 1f;
    public static final float PLAYER_ROTATION_OFFSET = 90f;
    public static final float PLAYER_ROTATION_SPEED = 220f;

    public static final int INITIAL_LIVES = 3;
    public static final float ASTEROID_RADIUS_DIVISOR = 4f;
    public static final float ASTEROID_SPAWN_INTERVAL = 2f;
    public static final int MAX_ASTEROIDS = 10;
    public static final float SCORE_INTERVAL = 1f;
    public static final int SCORE_PER_INTERVAL = 10;

    public static final float FONT_SCALE = 2f;
    public static final float UI_X_PADDING = 20f;
    public static final float LIFE_ICON_SPACING = 40f;
    public static final float LIFE_ICON_Y_OFFSET = 50f;
    public static final float LIFE_ICON_SIZE = 32f;
    public static final float SCORE_Y_OFFSET = 90f;

    public static final String BACKGROUND_TILE_PATH = "assets/Backgrounds/black.png";
    public static final float BACKGROUND_TILE_SIZE = 256f;

    public static final String MENU_TITLE = "ASTEROIDS";
    public static final String MENU_START_TEXT = "Press SPACE to Start Game";
    public static final String GAME_OVER_TITLE = "Game Over";
    public static final String GAME_OVER_TEXT = "Press SPACE to return to Menu or R to Rerun";
    public static final String FINAL_SCORE_TEXT = "Final Score: %d";
    public static final float MENU_TITLE_OFFSET = 50f;
    public static final float MENU_TEXT_OFFSET = -50f;
    public static final float FINAL_SCORE_OFFSET = 0f;

    public static final String ASSETS_PATH = "assets/PNG/";
    public static final String PLAYER_SHIP_PATH = ASSETS_PATH + "playerShip1_blue.png";
    public static final String METEORS_PATH = ASSETS_PATH + "Meteors/";
    public static final String LIFE_ICON_PATH = ASSETS_PATH + "UI/playerLife1_blue.png";
}
