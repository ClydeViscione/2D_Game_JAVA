package io.github.some_example_name;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ControlInversed extends Block {

    public ControlInversed(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.color = Color.BLUE;
    }

    public void applyEffect(Paddle paddle) {
        paddle.applyInvertedEffect(7f);
    }

    public void draw(ShapeRenderer shape) {
        super.draw(shape);
    }
}