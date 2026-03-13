package io.github.some_example_name;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

public class ResistantBlock extends Block {

    public int health = 2;

    public ResistantBlock(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.color = Color.GRAY;
    }

    public void draw(ShapeRenderer shape) {
        super.draw(shape);
    }
}