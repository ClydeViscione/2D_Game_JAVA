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
    public void update(Paddle paddle) {
        x += xSpeed;
        y += ySpeed;
        if (x < size || x > Gdx.graphics.getWidth() -size) {
            xSpeed = -xSpeed;
        }
        if (y < size || y > Gdx.graphics.getHeight() -size) {
            ySpeed = -ySpeed;
        }
        checkCollision(paddle);
    }
    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
        shape.circle(x, y, size);
    }
    public void checkCollision(Paddle paddle) {
        if(collidesWith(paddle)){
            ySpeed = -ySpeed;
            color = Color.PURPLE;
        }
        else{
            color = Color.WHITE;
        }
    }
    private boolean collidesWith(Paddle paddle) {
        return x + size >= paddle.x && x - size <= paddle.x + paddle.width && y + size >= paddle.y && y - size <= paddle.y + paddle.height;


    }
}
