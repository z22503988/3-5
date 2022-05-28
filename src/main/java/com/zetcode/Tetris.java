package com.zetcode;

import java.awt.*;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Tetris extends JFrame {

    private JLabel statusbar;
    private Board board;

    public Tetris() {
        initUI();
    }

    public Tetris(Board board) {
        board.setStatusbar(this.getStatusBar());
        this.board = board;
        initUI();
    }

    private void initUI() {
        statusbar = new JLabel(" 0");
        statusbar.setFont(new Font("Serif", Font.PLAIN, 32));
        add(statusbar, BorderLayout.NORTH);

        if (board == null)
            board = new Board(this);
        add(board);
        board.start();

        setTitle("Tetris");
        setSize(400, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public JLabel getStatusBar() {
        return statusbar;
    }

    public void dropDown() {
        board.dropDown();
    }

    public boolean isGameOver() {
        return board.getGameOver();
    }

    public int getLinesRemoved() {
        return board.getLinesRemoved();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Tetris game = new Tetris();
            game.setVisible(true);
        });
    }
}