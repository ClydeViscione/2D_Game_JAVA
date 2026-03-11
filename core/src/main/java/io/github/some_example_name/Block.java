package io.github.some_example_name;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Block {
    public int x, y, width, height;
    public boolean destroyed = false;
    public Color color;

    public Block(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = Color.WHITE;
    }

    public void draw(ShapeRenderer shape){
        if (!destroyed) {
            shape.setColor(color);
            shape.rect(x, y, width, height);
        }
    }
}