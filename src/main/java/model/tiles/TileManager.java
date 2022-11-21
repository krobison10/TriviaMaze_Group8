package model.tiles;

import view.TriviaMazeUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    TriviaMazeUI myTUI;
    Tile[] tile;
    int[][] mapData;

    public TileManager(TriviaMazeUI TUI) {
        myTUI = TUI;
        tile = new Tile[5];

        getTileImage();
    }

    public void getTileImage() {
        try {
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/wall.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            FileReader fr = new FileReader("map.txt");
            mapData = new int[31][31];
            int val;
            for(int i = 0; i < 31; i++) {
                for(int j = 0; j < 32; j++) {
                    char s = (char) fr.read();
                    if(s == ' ') s = '0';
                    if(s == '\n' || s == '\uFFFF') continue;
                    mapData[i][j] = Integer.parseInt(String.valueOf(s));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int bound = 31;
        int ts = TriviaMazeUI.TILE_SIZE;
        int tileNum;
        for(int col = 0; col < bound; col++)
        {
            for(int row = 0; row < bound; row++)
            {
                tileNum = mapData[row][col];
                if(tileNum != 1) continue;
                g2.drawImage(tile[tileNum].image, col*ts, row*ts, ts, ts, null);
            }
        }
    }
}
