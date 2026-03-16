package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle {
    public int x, y;
    public int width = 100, height = 10;
    public Color color = Color.WHITE;
    private int normalSpeed = 7;
    private int speed = normalSpeed;

    public boolean inverted = false;
    private int normalWidth = 100;
    public float effectTimer = 0f;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        if (effectTimer > 0) {
            effectTimer -= Gdx.graphics.getDeltaTime();
            if (effectTimer <= 0) {
                inverted = false;
                width = normalWidth;
                speed = normalSpeed;
            }
        }

        if (!inverted) {
            if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.LEFT)) x -= speed;
            if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.RIGHT)) x += speed;
        } else {
            if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.LEFT)) x += speed;
            if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.RIGHT)) x -= speed;
        }

        if (x < 0) x = 0;
        if (x + width > Gdx.graphics.getWidth()) x = Gdx.graphics.getWidth() - width;
    }

    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
        shape.rect(x, y, width, height);
    }

    public void applyWidthEffect(int newWidth, float duration) {
        width = newWidth;
        effectTimer = duration;
    }

    public void applyInvertedEffect(float duration) {
        inverted = true;
        effectTimer = duration;
    }
}