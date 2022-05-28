package com.zetcode;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TetrisTest {
    static private Tetris tetris;
    static private Board tetris_board;

    private boolean tetris_move(int x_val) {
        return tetris_board.tryMove(tetris_board.getCurPiece(),
                tetris_board.curX + x_val, tetris_board.curY);
    }

    private boolean tetris_rotate(boolean clockwise) {
        Shape piece;
        if (clockwise)
            piece = tetris_board.getCurPiece().rotateLeft();
        else
            piece = tetris_board.getCurPiece().rotateRight();
        return tetris_board.tryMove(piece, tetris_board.curX, tetris_board.curY);
    }

    @BeforeAll
    public static void beforeTest() {
        //System.setProperty("java.awt.headless", "false");
        tetris_board = new Board();

        boolean isHeadless = java.awt.
                GraphicsEnvironment.getLocalGraphicsEnvironment().isHeadless();

        if (isHeadless == false) {
            tetris = new Tetris(tetris_board);
            tetris.setVisible(true);
        }
    }

    @Test
    public void testRandomMove() {
        tetris_board.start();
        // Random move
        int t = 0;
        try {
            while (t < 100) {
                if (Math.random() > 0.5)
                    tetris_move(1);
                else
                    tetris_move(-1);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                if (Math.random() > 0.5)
                    tetris_rotate(false);
                else
                    tetris_rotate(true);
                t++;
            }
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testGameOver() {
        tetris_board.start();
        for (int i=0; i<10; i++)
            tetris_board.dropDown();
        boolean ret = tetris_board.getGameOver();
        assertTrue(ret);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void testSquareShapes() {
        tetris_board.start();
        for (int x=-6; x<=4; x += 2) {
            tetris_board.newPiece(Shape.Tetrominoe.SquareShape);
            tetris_move(x);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            tetris_board.dropDown();
        }
        int lines = tetris_board.getLinesRemoved();
        assertTrue(lines == 2);
    }

    @Test
    public void testLineShapes() {
        tetris_board.start();
        tetris_board.newPiece(Shape.Tetrominoe.LineShape);
        tetris_move(-6);
        tetris_board.dropDown();

        tetris_board.newPiece(Shape.Tetrominoe.LineShape);
        tetris_move(-5);
        tetris_board.dropDown();

        tetris_board.newPiece(Shape.Tetrominoe.LineShape);
        tetris_rotate(true);
        tetris_move(-3);
        tetris_board.dropDown();

        tetris_board.newPiece(Shape.Tetrominoe.LineShape);
        tetris_rotate(true);
        tetris_move(1);
        tetris_board.dropDown();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        int lines = tetris_board.getLinesRemoved();
        assertTrue(lines == 1);
    }
}