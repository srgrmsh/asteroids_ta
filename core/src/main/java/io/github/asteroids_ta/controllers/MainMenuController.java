package io.github.asteroids_ta.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.asteroids_ta.constants.Constants;
import io.github.asteroids_ta.managers.ScreenManager;

public class MainMenuController extends BaseMenuController {

    public MainMenuController(SpriteBatch batch) {
        super(batch);
    }

    public void update() {
        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            ScreenManager.getInstance().showGameScreen();
        }
    }

    public void render() {
        renderCenteredText(Constants.MENU_TITLE, Constants.MENU_TITLE_OFFSET);
        renderCenteredText(Constants.MENU_START_TEXT, Constants.MENU_TEXT_OFFSET);
    }
}

