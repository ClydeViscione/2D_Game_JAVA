package io.github.some_example_name;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;
import java.util.ArrayList;

public class Main extends ApplicationAdapter {
    ShapeRenderer shape;
    Ball ball;
    Paddle paddle;
    ArrayList<Block> blocks = new ArrayList<>();

    @Override
    public void create () {
        shape = new ShapeRenderer();
        ball = new Ball(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 30 , 5, 5 );
        paddle = new Paddle(250, 50);
        int blockWidth = 63;
        int blockHeight = 20;
        for (int y = Gdx.graphics.getHeight()/2; y < Gdx.graphics.getHeight(); y += blockHeight + 10) {
            for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                blocks.add(new Block(x, y, blockWidth, blockHeight));
            }
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ball.update(paddle);
        paddle.update();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.draw(shape);
        paddle.draw(shape);
        blocks.forEach(b -> b.draw(shape));
        shape.end();
    }
}
