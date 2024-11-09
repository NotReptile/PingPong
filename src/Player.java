import java.awt.*;
import java.awt.event.KeyEvent;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

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
        g.fillRect(x, y, width, height);
    }

    // High severity vulnerability: Executes arbitrary code supplied by the user
    public void executeScript(String script) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        engine.eval(script);
    }
}