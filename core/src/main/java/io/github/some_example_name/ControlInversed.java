package io.github.some_example_name;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

public class ControlInversed extends Block {

    public ControlInversed(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.color = Color.BLUE;
    }

    @Override
    public void draw(ShapeRenderer shape) {
        super.draw(shape);
    }

    public void applyEffect(Paddle paddle) {
        paddle.inverted = true;
    }
}