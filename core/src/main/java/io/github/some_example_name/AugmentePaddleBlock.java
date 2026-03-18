package io.github.some_example_name;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class AugmentePaddleBlock extends Block {

    public AugmentePaddleBlock(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.color = Color.RED;
    }

    public void draw(ShapeRenderer shape) {
        super.draw(shape);
    }

    public void applyEffect(Paddle paddle, int maxWidth) {
        paddle.width += 50;

        if (paddle.width > maxWidth) {
            paddle.width = maxWidth;
        }
    }
}
