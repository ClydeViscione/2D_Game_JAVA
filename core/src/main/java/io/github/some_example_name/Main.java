package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;
import java.util.Iterator;
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

        ball = new Ball(
                Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() / 2,
                15,
                3,
                5);

        paddle = new Paddle(250, 50);

        // Création des blocs
        blocks = new ArrayList<>();
        int blockWidth = 63;
        int blockHeight = 20;

        for (int y = Gdx.graphics.getHeight() / 2; y < Gdx.graphics.getHeight(); y += blockHeight + 10) {
            for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                blocks.add(new Block(x, y, blockWidth, blockHeight));
            }
        }

        Random random = new Random();

        // Bloc rouge -> agrandir paddle
        int redIndex = random.nextInt(blocks.size());
        Block redBlock = blocks.get(redIndex);
        blocks.set(redIndex, new AugmentePaddleBlock(redBlock.x, redBlock.y, redBlock.width, redBlock.height));

        // Bloc jaune -> balle plus petite
        int yellowIndex;
        do {
            yellowIndex = random.nextInt(blocks.size());
        } while (yellowIndex == redIndex);
        Block yellowBlock = blocks.get(yellowIndex);
        blocks.set(yellowIndex, new SmallBallBlock(yellowBlock.x, yellowBlock.y, yellowBlock.width, yellowBlock.height));

        // Bloc bleu -> double balle (on laisse juste le bloc, pas de création de balle ici)
        int blueIndex;
        do {
            blueIndex = random.nextInt(blocks.size());
        } while (blueIndex == redIndex || blueIndex == yellowIndex);
        Block blueBlock = blocks.get(blueIndex);
        blocks.set(blueIndex, new DoubleBallBlock(blueBlock.x, blueBlock.y, blueBlock.width, blueBlock.height));

        // 5 blocs gris -> ResistantBlock
        for (int i = 0; i < 5; i++) {
            int grayIndex;
            do {
                grayIndex = random.nextInt(blocks.size());
            } while (blocks.get(grayIndex) instanceof AugmentePaddleBlock ||
                     blocks.get(grayIndex) instanceof SmallBallBlock ||
                     blocks.get(grayIndex) instanceof DoubleBallBlock ||
                     blocks.get(grayIndex) instanceof ResistantBlock);

            Block grayBlock = blocks.get(grayIndex);
            blocks.set(grayIndex, new ResistantBlock(grayBlock.x, grayBlock.y, grayBlock.width, grayBlock.height));
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Si le jeu n'a pas commencé, attendre ESPACE
        if (!gameStarted) {
            if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.SPACE)) {
                gameStarted = true;
            }
            batch.begin();
            font.draw(batch, "Appuyez sur ESPACE pour démarrer",
                      Gdx.graphics.getWidth() / 2f - 150,
                      Gdx.graphics.getHeight() / 2f);
            batch.end();
            return;
        }

        // Mise à jour paddle et balle
        paddle.update();
        ball.update();
        ball.checkCollisionPaddle(paddle);

        // Collisions avec les blocs (sans passer balls)
        for (Block b : blocks) {
            ball.checkCollisionBlock(b, paddle);
        }

        // Supprimer les blocs détruits (Iterator pour sécurité)
        Iterator<Block> blockIterator = blocks.iterator();
        while (blockIterator.hasNext()) {
            Block b = blockIterator.next();
            if (b.destroyed) {
                blockIterator.remove();
            }
        }

        // Vérifier victoire
        if (blocks.isEmpty()) {
            batch.begin();
            font.draw(batch, "YOU WIN!",
                      Gdx.graphics.getWidth() / 2f - 40,
                      Gdx.graphics.getHeight() / 2f);
            batch.end();
            return;
        }

        // Vérifier Game Over
        if (ball.y - ball.size <= 0) {
            batch.begin();
            font.draw(batch, "GAME OVER",
                      Gdx.graphics.getWidth() / 2f - 50,
                      Gdx.graphics.getHeight() / 2f);
            batch.end();
            gameStarted = false; // possibilité de relancer avec espace
            return;
        }

        // Dessiner tout
        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.draw(shape);
        paddle.draw(shape);
        for (Block b : blocks) {
            b.draw(shape);
        }
        shape.end();
    }
}