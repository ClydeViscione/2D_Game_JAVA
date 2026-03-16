package io.github.some_example_name;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class SmallBallBlock extends Block {

    public SmallBallBlock(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.color = Color.YELLOW;
    }

    public void applyEffect(Ball ball) {
        ball.applySmallEffect(7f);
    }

    public void draw(ShapeRenderer shape) {
        super.draw(shape);
    }
}