package io.github.some_example_name;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

public class DoubleBallBlock extends Block {

    public DoubleBallBlock(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.color = Color.BLUE;
    }
    public void draw(ShapeRenderer shape) {
        super.draw(shape);
    }
}