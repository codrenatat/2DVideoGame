import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int TileSize = 16; //16x16
    final int scale = 3;

    public final int Tiles = TileSize * scale; //48x48
    final int Col = 16;
    final int Row = 12;
    final int Width = Tiles * Col; //768 pix
    final int Height = Tiles * Row; //576 pix

    //FPS
    int FPS = 60;

    Keys key = new Keys();
    Thread gameThread; //60 fps
    Player player = new Player(this,key);

    //Default Player position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(Width, Height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){

        double draw = 1000000000/FPS; //0.016sec
        double delta = 0;
        long lastTime = System.nanoTime();
        long current;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            current = System.nanoTime();
            delta += (current - lastTime) / draw;
            timer += (current - lastTime);
            lastTime = current;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " +drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        g2.dispose();
    }
}
