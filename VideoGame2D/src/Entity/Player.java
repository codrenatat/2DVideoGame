package Entity;

import main.GamePanel;
import main.Keys;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Sprites {
    GamePanel gamePanel;
    Keys keys;


    public Player(GamePanel gamePanel, Keys keys){
        this.gamePanel = gamePanel;
        this.keys = keys;
        Default();
        getPlayerImage();
    }

    public void Default(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/girl-up-1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/girl-up-2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/girl-down-1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/girl-down-2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/girl-left-1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/girl-left-2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/girl-right-1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/girl-right-2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keys.up == true){
            direction = "up";
            y = y - speed;
        } else if (keys.down == true) {
            direction = "down";
            y = y + speed;
        } else if (keys.left == true) {
            direction = "left";
            x = x - speed;
        } else if(keys.right == true){
            direction = "right";
            x = x + speed;
        }
        Counter++;
        if(Counter > 10){
            if(number == 1){
                number = 2;
            }
            else if (number == 2){
                number = 1;
            }
            Counter = 0;
        }
    }

    public void draw(Graphics2D g){
        BufferedImage image = null;
        switch (direction){
            case "up":
                if(number == 1){
                    image = up1;
                }
                if(number == 2){
                    image = up2;
                }
                break;
            case "down":
                if(number == 1) {
                    image = down1;
                }
                if(number == 2){
                    image = down2;
                }
                break;
            case "right":
                if(number == 1) {
                    image = right1;
                }
                if(number == 2){
                    image = right2;
                }
                break;
            case "left":
                if(number == 1){
                    image = left1;
                }
                if(number == 2){
                    image = left2;
                }
                break;
            default:
                System.out.println("Error");
        }
        g.drawImage(image,x,y,gamePanel.Tiles, gamePanel.Tiles, null);
    }

}
