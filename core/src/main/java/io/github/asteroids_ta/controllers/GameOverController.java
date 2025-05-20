package io.github.asteroids_ta.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.asteroids_ta.constants.Constants;
import io.github.asteroids_ta.managers.ScreenManager;

public class GameOverController extends BaseMenuController {

    public GameOverController(SpriteBatch batch) {
        super(batch);
    }

    public void update() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            ScreenManager.getInstance().showMenuScreen();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            ScreenManager.getInstance().showGameScreen();
        }
    }

    public void render() {
        renderCenteredText(Constants.GAME_OVER_TITLE, Constants.MENU_TITLE_OFFSET);
        renderCenteredText(Constants.GAME_OVER_TEXT, Constants.MENU_TEXT_OFFSET);
    }
}

