package io.github.asteroids_ta.controllers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.asteroids_ta.constants.Constants;

public abstract class BaseMenuController {
    protected final SpriteBatch batch;
    protected final BitmapFont font;
    protected final GlyphLayout layout;

    public BaseMenuController(SpriteBatch batch) {
        this.batch = batch;
        this.font = new BitmapFont();
        this.font.getData().setScale(Constants.FONT_SCALE);
        this.font.setColor(Color.WHITE);
        this.layout = new GlyphLayout();
    }

    protected void renderCenteredText(String text, float yOffset) {
        layout.setText(font, text);
        font.draw(batch, layout,
            (Constants.WORLD_WIDTH - layout.width) / 2,
            (Constants.WORLD_HEIGHT + layout.height) / 2 + yOffset
        );
    }

    public void dispose() {
        font.dispose();
    }
}
