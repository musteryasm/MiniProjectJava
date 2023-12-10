package com.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class DontTouchTheWhiteTile extends JPanel implements ActionListener, MouseListener {

    public final static int COLUMNS = 3, ROWS = 3, TILE_WIDTH = 250, TILE_HEIGHT = 300;

    public static DontTouchTheWhiteTile dttwt;

    public ArrayList<Tile> tiles;

    public Random random;

    public int score, milSecDelay;

    public boolean gameOver;

    public DontTouchTheWhiteTile() {
        JFrame frame = new JFrame("Don't Touch The White Tile!");
        Timer timer = new Timer(20, this);

        random = new Random();

        frame.setSize(TILE_WIDTH * COLUMNS, TILE_HEIGHT * ROWS);
        frame.add(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addMouseListener(this);
        frame.setResizable(false);

        start();

        timer.start();
    }

    public void start() {
        score = 0;
        gameOver = false;
        tiles = new ArrayList<Tile>();

        for (int x = 0; x < COLUMNS; x++) {
            for (int y = 0; y < ROWS; y++) {
                boolean canBeBlack = true;

                for (Tile tile : tiles) {
                    if (tile.y == y && tile.black) {
                        canBeBlack = false;
                    }
                }

                if (!canBeBlack) {
                    tiles.add(new Tile(x, y, false));
                } else {
                    tiles.add(new Tile(x, y, random.nextInt(2) == 0 || x == 2));
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);

            if (tile.animateY < 0) {
                tile.animateY += TILE_HEIGHT / 5;
            }
        }

        milSecDelay++;
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, TILE_WIDTH * COLUMNS, TILE_HEIGHT * ROWS);

        g.setFont(new Font("Times New Roman", 1, 100));

        if (!gameOver) {
            for (Tile tile : tiles) {
                g.setColor(tile.black ? Color.BLACK : Color.WHITE);
                g.fillRect(tile.x * TILE_WIDTH, tile.y * TILE_HEIGHT + tile.animateY, TILE_WIDTH, TILE_HEIGHT);
                g.setColor(tile.black ? Color.WHITE : Color.BLACK);
                g.drawRect(tile.x * TILE_WIDTH, tile.y * TILE_HEIGHT + tile.animateY, TILE_WIDTH, TILE_HEIGHT);
            }

            g.setColor(Color.RED);
            g.drawString(String.valueOf(score), TILE_WIDTH, 100);
        } else {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("init 0", 50, 100);

            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Game Over!", 50, TILE_HEIGHT + 100);

            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Final Score: " + score, 50, TILE_HEIGHT + 150);
        }
    }

    public static void main(String[] args) {
        dttwt = new DontTouchTheWhiteTile();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        boolean clicked = false;

        if (!gameOver) {
            for (int i = 0; i < tiles.size(); i++) {
                Tile tile = tiles.get(i);

                if (tile.pointInTile(e.getX(), e.getY()) && !clicked) {
                    if (e.getY() > TILE_HEIGHT * (ROWS - 1)) {
                        if (tile.black) {
                            for (int j = 0; j < tiles.size(); j++) {
                                if (tiles.get(j).y == ROWS) {
                                    tiles.remove(j);
                                }

                                tiles.get(j).y++;
                                tiles.get(j).animateY -= TILE_HEIGHT;
                            }

                            score += Math.max(100 - milSecDelay, 10);

                            System.out.println("You've scored " + Math.max(100 - milSecDelay, 10) + " points!");

                            milSecDelay = 0;

                            boolean canBeBlack = true;

                            for (int x = 0; x < COLUMNS; x++) {
                                boolean black = random.nextInt(2) == 0 || x == COLUMNS - 1;

                                Tile newTile = null;

                                if (canBeBlack && black) {
                                    newTile = new Tile(x, 0, true);
                                    canBeBlack = false;
                                } else {
                                    newTile = new Tile(x, 0, false);
                                }

                                newTile.animateY -= TILE_HEIGHT;

                                tiles.add(newTile);
                            }
                        } else {
                            gameOver = true;
                        }

                        clicked = true;
                    } else {
                        gameOver = true;
                    }
                }
            }
        } else {
            start();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (dttwt != null) {
            dttwt.render(g);
        }
    }
}

class Tile {
    public int x, y;
    public int animateY;
    public boolean black;

    public Tile(int x, int y, boolean black) {
        this.x = x;
        this.y = y;
        this.black = black;
    }

    public boolean pointInTile(int x, int y) {
        int width = DontTouchTheWhiteTile.TILE_WIDTH;
        int height = DontTouchTheWhiteTile.TILE_HEIGHT;

        return x > this.x * width && x < this.x * width + width && y > this.y * height && y < this.y * height + height;
    }
}
