package io.github.some_example_name;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

public class SmallBallBlock extends Block {

    public SmallBallBlock(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.color = Color.YELLOW;
    }

    public void draw(ShapeRenderer shape) {
        super.draw(shape);
    }

    public void applyEffect(Ball ball) {

        ball.size -= 5;

        if (ball.size < 5) {
            ball.size = 5;
        }

    }
}