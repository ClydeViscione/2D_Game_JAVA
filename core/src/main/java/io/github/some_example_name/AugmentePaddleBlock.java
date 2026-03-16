package io.github.some_example_name;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class AugmentePaddleBlock extends Block {

    public AugmentePaddleBlock(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.color = Color.RED;
    }

    public void applyEffect(Paddle paddle) {
        paddle.applyWidthEffect(paddle.width + 50, 7f);
    }

    public void draw(ShapeRenderer shape) {
        super.draw(shape);
    }
}