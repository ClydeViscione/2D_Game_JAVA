package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {
        int x;
        int y;
        int width = 100;
        int height = 10;

        public Paddle (int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void update(){
            x=Gdx.input.getX();
        }

        public void draw(ShapeRenderer shape) {
            shape.rect(x, y, width, height);
    }
}
