package io.github.asteroids_ta.systems;

import com.badlogic.gdx.math.Vector2;
import io.github.asteroids_ta.constants.Constants;
import io.github.asteroids_ta.models.IGameObject;

public class ScreenWrapper {

    public static void wrapObject(IGameObject object) {
        Vector2 pos = object.getPosition();
        float width = object.getBounds().radius * 2;
        float height = object.getBounds().radius * 2;

        if (pos.x < -width) pos.x = Constants.WORLD_WIDTH;
        if (pos.x > Constants.WORLD_WIDTH) pos.x = -width;
        if (pos.y < -height) pos.y = Constants.WORLD_HEIGHT;
        if (pos.y > Constants.WORLD_HEIGHT) pos.y = -height;
    }
}
