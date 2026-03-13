package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    public int x, y, size;
    public int xSpeed, ySpeed;
    public Color color = Color.GREEN; // couleur fixe
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
        if (x < size || x > Gdx.graphics.getWidth() - size) xSpeed = -xSpeed;
        if (y > Gdx.graphics.getHeight() - size) ySpeed = -ySpeed;
    }
    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
        shape.circle(x, y, size);
    }
    public void checkCollisionPaddle(Paddle paddle) {
        if (x + size >= paddle.x && x - size <= paddle.x + paddle.width &&
            y - size <= paddle.y + paddle.height && y + size >= paddle.y) {
            ySpeed = -ySpeed;
        }
    }
    public void checkCollisionBlock(Block block, Paddle paddle, int widthGraphics ) {

        if (x + size >= block.x && x - size <= block.x + block.width &&
            y + size >= block.y && y - size <= block.y + block.height) {

            ySpeed = -ySpeed;

            if (block instanceof ResistantBlock) {

                ResistantBlock rb = (ResistantBlock) block;
                rb.health--;

                if (rb.health <= 0) {
                    block.destroyed = true;
                }

            } else {
                block.destroyed = true;
            }

            if (block instanceof AugmentePaddleBlock) {
                ((AugmentePaddleBlock) block).applyEffect(paddle,  widthGraphics);
            }

            if (block instanceof SmallBallBlock) {
                ((SmallBallBlock) block).applyEffect(this);
            }

        }
    }
}
