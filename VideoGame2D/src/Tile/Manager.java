package Tile;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Manager {
    GamePanel gamePanel;
    tile[] tiles;
    int Map[][];

    public Manager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tiles = new tile[10];
        Map = new int[gamePanel.Col][gamePanel.Row];
        getTileImage();
        LoadMaps("/Map/1Map.txt");
    }

    public void getTileImage(){
        try {
            tiles[0] = new tile();
            tiles[0].Image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass.png"));

            tiles[1] = new tile();
            tiles[1].Image = ImageIO.read(getClass().getResourceAsStream("/tiles/Wall.png"));

            tiles[2] = new tile();
            tiles[2].Image = ImageIO.read(getClass().getResourceAsStream("/tiles/Water.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void LoadMaps(String Path){
        try {
            InputStream is = getClass().getResourceAsStream(Path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gamePanel.Col && row < gamePanel.Row){
                String line = br.readLine();

                while (col < gamePanel.Col){
                    String numbers[] = line.split("");
                    int num = Integer.parseInt(numbers[col]);

                    Map[col][row] = num;
                    col++;
                }
                if (col == gamePanel.Col){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void Draw(Graphics2D graphics2D){
        int column = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (column < gamePanel.Col && row < gamePanel.Row){
            int Tile_Number = Map[column][row];

            graphics2D.drawImage(tiles[Tile_Number].Image,x,y,gamePanel.Tiles, gamePanel.Tiles, null);
            column++;
            x += gamePanel.Tiles;

            if(column == gamePanel.Col){
                column = 0;
                x = 0;
                row++;
                y += gamePanel.Tiles;
            }
        }
    }
}
