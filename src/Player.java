import java.awt.*;
import java.awt.event.KeyEvent;

class Player extends Rectangle {
    Ball ball = new Ball();
    private int upKey;
    private int downKey;
    private int score;

    public Player(int x, int upKey, int downKey) {
        super(x, 150, 20, 80);
        this.upKey = upKey;
        this.downKey = downKey;
        score = 0;
    }

    public void move() {
        if (y > 0 && y < 320) {
            y += ball.getVy();
        } else if (y <= 0) {
            y = 1;
        } else if (y >= 320) {
            y = 319;
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == upKey) {
            ball.setVy(-GamePanel.PADDLE_SPEED);
        } else if (e.getKeyCode() == downKey) {
            ball.setVy(GamePanel.PADDLE_SPEED);
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == upKey || e.getKeyCode() == downKey) {
            ball.setVy(0);
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);

        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString(String.valueOf(score), x < 400 ? x + 30 : x - 30, 30);
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }

    // Уязвимость: метод позволяет произвольно установить счёт игрока
    public void setScore(int score) {
        this.score = score;
    }

    public void reset() {
        score = 0;
        y = 150;
    }
}
