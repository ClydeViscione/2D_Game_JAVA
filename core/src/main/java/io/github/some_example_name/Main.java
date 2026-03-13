package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;
import java.util.Random;

public class Main extends ApplicationAdapter {
    ShapeRenderer shape;
    SpriteBatch batch;
    BitmapFont font;
    Ball ball;
    Paddle paddle;
    ArrayList<Block> blocks;

    @Override
    public void create() {
        shape = new ShapeRenderer();
        batch = new SpriteBatch();
        font = new BitmapFont();
        ball = new Ball(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 15, 3, 5);
        paddle = new Paddle(250, 50);

        blocks = new ArrayList<>();
        int blockWidth = 63;
        int blockHeight = 20;

        for (int y = Gdx.graphics.getHeight() / 2; y < Gdx.graphics.getHeight(); y += blockHeight + 10) {
            for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                blocks.add(new Block(x, y, blockWidth, blockHeight));
            }
        }

        Random random = new Random();

        int redIndex = random.nextInt(blocks.size());
        Block redBlock = blocks.get(redIndex);
        blocks.set(redIndex, new AugmentePaddleBlock(redBlock.x, redBlock.y, redBlock.width, redBlock.height));

        int yellowIndex;
        do {
            yellowIndex = random.nextInt(blocks.size());
        } while (yellowIndex == redIndex);
        Block yellowBlock = blocks.get(yellowIndex);
        blocks.set(yellowIndex, new SmallBallBlock(yellowBlock.x, yellowBlock.y, yellowBlock.width, yellowBlock.height));

        int blueIndex;
        do {
            blueIndex = random.nextInt(blocks.size());
        } while (blueIndex == redIndex || blueIndex == yellowIndex);
        Block blueBlock = blocks.get(blueIndex);
        blocks.set(blueIndex, new DoubleBallBlock(blueBlock.x, blueBlock.y, blueBlock.width, blueBlock.height));

        for (int i = 0; i < 5; i++) {

    int grayIndex;
    do {
        grayIndex = random.nextInt(blocks.size());
    } while (blocks.get(grayIndex) instanceof AugmentePaddleBlock ||
             blocks.get(grayIndex) instanceof SmallBallBlock ||
             blocks.get(grayIndex) instanceof DoubleBallBlock ||
             blocks.get(grayIndex) instanceof ResistantBlock);

    Block grayBlock = blocks.get(grayIndex);
    blocks.set(grayIndex,
        new ResistantBlock(grayBlock.x, grayBlock.y, grayBlock.width, grayBlock.height));
}
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ball.update();
        paddle.update();
        ball.checkCollisionPaddle(paddle);

        for (Block b : blocks) {
    ball.checkCollisionBlock(b, paddle);
}

        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i).destroyed) {
                blocks.remove(i);
                i--;
            }
        }

        if (blocks.isEmpty()) {
            batch.begin();
            font.draw(batch, "YOU WIN!", Gdx.graphics.getWidth() / 2f - 40, Gdx.graphics.getHeight() / 2f);
            batch.end();
            return;
        }


        if (ball.y - ball.size <= 0) {
            batch.begin();
            font.draw(batch, "GAME OVER", Gdx.graphics.getWidth() / 2f - 50, Gdx.graphics.getHeight() / 2f);
            batch.end();
            return;
        }

        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.draw(shape);
        paddle.draw(shape);
        for (Block b : blocks) {
            b.draw(shape);
        }
        shape.end();
    }
}