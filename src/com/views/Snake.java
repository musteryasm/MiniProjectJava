package com.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener {

    public static Snake snake;

    public JFrame jframe;

    public RenderPanel renderPanel;

    public Timer timer = new Timer(20, this);

    public ArrayList<Point> snakeParts = new ArrayList<Point>();

    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;

    public int ticks = 0, direction = DOWN, score, tailLength = 10, time;

    public Point head, cherry;

    public Random random;

    public boolean over = false, paused;

    public Dimension dim;

    public Snake() {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jframe = new JFrame("Snake");
        jframe.setVisible(true);
        jframe.setSize(990, 680);
        jframe.setResizable(false);
        jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2);
        jframe.add(renderPanel = new RenderPanel());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.addKeyListener(this);
        startGame();
    }

    public void startGame() {
        over = false;
        paused = false;
        time = 0;
        score = 0;
        tailLength = 14;
        ticks = 0;
        direction = DOWN;
        head = new Point(0, -1);
        random = new Random();
        snakeParts.clear();
        cherry = new Point(random.nextInt(79), random.nextInt(66));
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        renderPanel.repaint();
        ticks++;

        if (ticks % 2 == 0 && head != null && !over && !paused) {
            time++;

            snakeParts.add(new Point(head.x, head.y));

            if (direction == UP) {
                if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1)) {
                    head = new Point(head.x, head.y - 1);
                } else {
                    over = true;
                }
            }

            if (direction == DOWN) {
                if (head.y + 1 < 67 && noTailAt(head.x, head.y + 1)) {
                    head = new Point(head.x, head.y + 1);
                } else {
                    over = true;
                }
            }

            if (direction == LEFT) {
                if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y)) {
                    head = new Point(head.x - 1, head.y);
                } else {
                    over = true;
                }
            }

            if (direction == RIGHT) {
                if (head.x + 1 < 80 && noTailAt(head.x + 1, head.y)) {
                    head = new Point(head.x + 1, head.y);
                } else {
                    over = true;
                }
            }

            if (snakeParts.size() > tailLength) {
                snakeParts.remove(0);
            }

            if (cherry != null) {
                if (head.equals(cherry)) {
                    score += 10;
                    tailLength++;
                    cherry.setLocation(random.nextInt(79), random.nextInt(66));
                }
            }
        }
    }

    public boolean noTailAt(int x, int y) {
        for (Point point : snakeParts) {
            if (point.equals(new Point(x, y))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        snake = new Snake();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();

        if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) && direction != RIGHT) {
            direction = LEFT;
        }

        if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && direction != LEFT) {
            direction = RIGHT;
        }

        if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP) && direction != DOWN) {
            direction = UP;
        }

        if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && direction != UP) {
            direction = DOWN;
        }

        if (i == KeyEvent.VK_SPACE) {
            if (over) {
                startGame();
            } else {
                paused = !paused;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}

class RenderPanel extends JPanel {

    public static final Color BLACK = new Color(0, 0, 0); // Black color

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Snake snake = Snake.snake;

        // Set background to black
        g.setColor(BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.YELLOW);

        for (Point point : snake.snakeParts) {
            g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
        }

        g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);

        g.setColor(Color.GREEN);

        // Increase the size of the food rectangle
        int foodSize = Snake.SCALE + 5; // Adjust the size as needed
        g.fillRect(snake.cherry.x * Snake.SCALE, snake.cherry.y * Snake.SCALE, foodSize, foodSize);

        String string = "Score: " + snake.score + ", Length: " + snake.tailLength + ", Time: " + snake.time / 20;

        g.setColor(Color.white);

        g.drawString(string, 10, 20); // Display the score information at the top left corner

        string = "Game Over!";

        if (snake.over) {
            g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) snake.dim.getHeight() / 4);
        }

        string = "Paused!";

        if (snake.paused && !snake.over) {
            g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) snake.dim.getHeight() / 4);
        }

        // Display "init 0" at the top left corner
        g.drawString("init 0", 10, 40);
    }
}