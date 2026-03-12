package io.github.some_example_name;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

public class AugmentePaddleBlock extends Block {

    public AugmentePaddleBlock(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.color = Color.RED;
    }

    public void draw(ShapeRenderer shape) {
        super.draw(shape);
    }

    public void applyEffect(Paddle paddle) {
        paddle.width += 50;

        if (paddle.width > com.badlogic.gdx.Gdx.graphics.getWidth()) {
            paddle.width = com.badlogic.gdx.Gdx.graphics.getWidth();
        }
    }
}