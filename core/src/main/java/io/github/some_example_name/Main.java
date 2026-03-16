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
    private boolean gameStarted = false;

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

        // Création des blocs
        for (int y = Gdx.graphics.getHeight() / 2; y < Gdx.graphics.getHeight(); y += blockHeight + 10) {
            for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                blocks.add(new Block(x, y, blockWidth, blockHeight));
            }
        }

        Random random = new Random();

        // Bloc rouge → Augmente Paddle
        int redIndex = random.nextInt(blocks.size());
        Block redBlock = blocks.get(redIndex);
        blocks.set(redIndex, new AugmentePaddleBlock(redBlock.x, redBlock.y, redBlock.width, redBlock.height));

        // Bloc jaune → Rétrécit la balle
        int yellowIndex;
        do {
            yellowIndex = random.nextInt(blocks.size());
        } while (yellowIndex == redIndex);
        Block yellowBlock = blocks.get(yellowIndex);
        blocks.set(yellowIndex, new SmallBallBlock(yellowBlock.x, yellowBlock.y, yellowBlock.width, yellowBlock.height));

        // Bloc bleu → Inverse contrôle
        int blueIndex;
        do {
            blueIndex = random.nextInt(blocks.size());
        } while (blueIndex == redIndex || blueIndex == yellowIndex);
        Block blueBlock = blocks.get(blueIndex);
        blocks.set(blueIndex, new ControlInversed(blueBlock.x, blueBlock.y, blueBlock.width, blueBlock.height));

        // 5 blocs gris → Resistant
        for (int i = 0; i < 5; i++) {
            int grayIndex;
            do {
                grayIndex = random.nextInt(blocks.size());
            } while (blocks.get(grayIndex) instanceof AugmentePaddleBlock ||
                     blocks.get(grayIndex) instanceof SmallBallBlock ||
                     blocks.get(grayIndex) instanceof ControlInversed ||
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

        // Début du jeu uniquement après appui sur espace
        if (!gameStarted) {
            if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.SPACE)) {
                gameStarted = true;
            } else {
                batch.begin();
                font.draw(batch, "APPUIE SUR ESPACE POUR COMMENCER", Gdx.graphics.getWidth()/2f - 120, Gdx.graphics.getHeight()/2f);
                batch.end();
                return;
            }
        }

        ball.update();
        paddle.update();
        ball.checkCollisionPaddle(paddle);

        for (Block b : blocks) {
            ball.checkCollisionBlock(b, paddle);
        }

        // Supprimer les blocs détruits
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i).destroyed) {
                blocks.remove(i);
                i--;
            }
        }

        // Vérification de victoire
        if (blocks.isEmpty()) {
            batch.begin();
            font.draw(batch, "YOU WIN!", Gdx.graphics.getWidth() / 2f - 40, Gdx.graphics.getHeight() / 2f);
            batch.end();
            return;
        }

        // Vérification de perte
        if (ball.y - ball.size <= 0) {
            batch.begin();
            font.draw(batch, "GAME OVER - APPUIE SUR ESPACE POUR REJOUER", Gdx.graphics.getWidth()/2f - 150, Gdx.graphics.getHeight()/2f);
            batch.end();

            if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.SPACE)) {
                create(); // relance la partie
                gameStarted = true;
            }
            return;
        }

        // Dessin
        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.draw(shape);
        paddle.draw(shape);
        for (Block b : blocks) {
            b.draw(shape);
        }
        shape.end();
    }
}