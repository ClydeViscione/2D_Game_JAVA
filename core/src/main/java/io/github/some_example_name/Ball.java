package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;
    Color color = Color.GREEN;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }
    public void update() {
        x += xSpeed;
        y += ySpeed;
        if (x < size || x > Gdx.graphics.getWidth() -size) {
            xSpeed = -xSpeed;
        }
        if (y < size || y > Gdx.graphics.getHeight() -size) {
            ySpeed = -ySpeed;
        }
    }
    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
        shape.circle(x, y, size);
    }
    public void checkCollisionPaddle(Paddle paddle) {
        if(collidesWithPaddle(paddle)){
            ySpeed = -ySpeed;
            color = Color.PURPLE;
        }
        else{
            color = Color.WHITE;
        }
    }

    public void checkCollisionBlock(Block block) {
        if (collidesWithBlock(block)) {
            ySpeed = -ySpeed;
            color = Color.RED;
        }
    }

    private boolean collidesWithPaddle(Paddle paddle) {
        return (x + size >= paddle.x && x - size <= paddle.x + paddle.width &&
            y + size >= paddle.y && y - size <= paddle.y + paddle.height);
    }
    private boolean collidesWithBlock(Block block) {
        return x + size >= block.x && x - size <= block.x + block.width &&
            y + size >= block.y && y - size <= block.y + block.height;
    }
}
