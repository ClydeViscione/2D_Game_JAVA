package io.github.some_example_name;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;

public class Main extends ApplicationAdapter {
    ShapeRenderer shape;
    Ball ball;
    Paddle paddle;


    @Override
    public void create () {
        shape = new ShapeRenderer();
        ball = new Ball(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 30 , 5, 5 );
        paddle = new Paddle(250, 50);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ball.update(paddle);
        paddle.update();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.draw(shape);
        paddle.draw(shape);
        shape.end();
    }
}
