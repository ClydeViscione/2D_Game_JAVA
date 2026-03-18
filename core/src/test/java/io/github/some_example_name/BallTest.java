package io.github.some_example_name;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BallTest{

    Ball ball;
    Paddle paddle;

    int xPosition = 1;
    int yPosition = 1;

    @BeforeEach
    void initialize(){
        paddle = new Paddle(4,4);
        ball = new Ball(300, 300, 5,150, 40);
    }

    @Test @Order(1)
    void checkCollisionBlock_detruit(){
        Block block = new Block(xPosition, yPosition, 150, 40);
        ball.x = xPosition;
        ball.y = yPosition;
        ball.checkCollisionBlock(block, paddle,800);
        assertTrue(block.destroyed);
    }

    @Test @Order(2)
    void checkCollisionResistantBlock(){
        ResistantBlock block = new ResistantBlock(xPosition, yPosition, 150, 40);
        ball.x = xPosition;
        ball.y = yPosition;
        ball.checkCollisionBlock(block, paddle, 800);
        if(block.health ==1){
            ball.x = 300;
            ball.y = 300;
            ball.x = xPosition;
            ball.y = yPosition;

            ball.checkCollisionBlock(block, paddle, 800);
            assertTrue(block.destroyed);
        }
        else{
            fail("Aucun pv à été enlever");
        }
    }

    @Test @Order(3)
    void checkCollisionAugmentePaddleBlock(){
        AugmentePaddleBlock block = new AugmentePaddleBlock(xPosition, yPosition, 150, 75);
        ball.x = xPosition;
        ball.y = yPosition;
        ball.checkCollisionBlock(block, paddle, 800);
        assertTrue(paddle.width != 100);
    }
    }
