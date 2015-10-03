package main.java.jonanguti.platformer.GameStates;

import main.java.jonanguti.platformer.GamePanel;
import main.java.jonanguti.platformer.Map.TileMap;

import java.awt.*;

public class Lv1State extends GameState {

    private TileMap tilemap;

    public Lv1State(GSManager gsManager) {
        this.gsm = gsManager;
        init();

    }

    public void init() {

        tilemap = new TileMap(30);
        tilemap.loadTiles("/Tilesets/grasstileset.gif");
        tilemap.loadMap("/Maps/level1-1.map");
        tilemap.setPosition(0, 0);


    }
    public void update() {}
    public void draw(Graphics2D g){
        //Clear screen
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        //Draw map
        tilemap.draw(g);
    }
    public void keyPressed(int k){}
    public void keyReleased(int k){}
}
