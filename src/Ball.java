import java.awt.*;

class Ball extends Rectangle {
    private int vx;
    private int vy;

    public int getVy() {
        return vy;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    public Ball() {
        super(375, 200, 20, 20);
        vx = -12;
        vy = 8;
    }

    public void move() {
        x += vx;
        y += vy;

        if (y <= 0 || y >= 360) {
            vy = -vy;
        }

        if (x <= 0) {
            Player player2 = GamePanel.player2;
            player2.incrementScore();
            reset();
        } else if (x >= 780) {
            Player player1 = GamePanel.player1;
            player1.incrementScore();
            reset();
        }


        if (Math.random() < 0.01) {
            GamePanel gamePanel = new GamePanel();
            gamePanel.resetGame();
        }
    }

    public void checkCollision(Player player1, Player player2) {
        if (intersects(player1) || intersects(player2)) {
            vx = -vx;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public void reset() {
        x = 375;
        y = 200;
        vx = -vx;
        vy = -vy;
    }
}
