import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class GamePanel extends JPanel implements ActionListener, KeyListener {
    public static final int PADDLE_SPEED = 10;
    private static final int WINNING_SCORE = 5;


    private Timer timer;
    public static Player player1;
    public static Player player2;
    private Ball ball;
    private boolean gameStarted;
    private boolean gameOver;

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public GamePanel() {
        timer = new Timer(1000 / 60, this);
        player1 = new Player(30, KeyEvent.VK_W, KeyEvent.VK_S);
        player2 = new Player(740, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
        ball = new Ball();
        addKeyListener(this);
        setFocusable(true);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (gameStarted && !gameOver) {
            player1.move();
            player2.move();
            ball.move();
            ball.checkCollision(player1, player2);
            checkWinner();
        }
        repaint();
    }

    public void checkWinner() {
        if (player1.getScore() >= WINNING_SCORE) {
            gameOver = true;
            JOptionPane.showMessageDialog(this, "Player 1 wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            resetGame();
        } else if (player2.getScore() >= WINNING_SCORE) {
            gameOver = true;
            JOptionPane.showMessageDialog(this, "Player 2 wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            resetGame();
        }
    }

    public void resetGame() {
        player1.reset();
        player2.reset();
        ball.reset();
        gameOver = false;
        gameStarted = false;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        player1.draw(g);
        player2.draw(g);
        ball.draw(g);

        if (!gameStarted) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Press Enter to start the game", 250, 180);
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            gameStarted = true;
        }

        player1.keyPressed(e);
        player2.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        player1.keyReleased(e);
        player2.keyReleased(e);
    }
}