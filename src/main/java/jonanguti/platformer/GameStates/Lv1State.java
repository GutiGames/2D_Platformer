package main.java.jonanguti.platformer.GameStates;

import main.java.jonanguti.platformer.Entity.Player;
import main.java.jonanguti.platformer.GamePanel;
import main.java.jonanguti.platformer.Map.Background;
import main.java.jonanguti.platformer.Map.TileMap;

import java.awt.*;

public class Lv1State extends GameState {

    private TileMap tilemap;

    private Background bg;

    private Player player;

    public Lv1State(GSManager gsManager) {
        this.gsm = gsManager;
        init();

    }

    public void init() {

        tilemap = new TileMap(30);
        tilemap.loadTiles("/Tilesets/grasstileset.gif");
        tilemap.loadMap("/Maps/level1-1.map");
        tilemap.setPosition(0, 0);

        bg = new Background("/Backgrounds/grassbg1.gif", 0.1);

        player = new Player(tilemap);
        player.setPosition(100, 100);




    }
    public void update() {
        player.update();

    }
    public void draw(Graphics2D g){
        //Draw bg
        bg.draw(g);

        //Draw map
        tilemap.draw(g);

        //Draw player
        player.draw(g);
    }
    public void keyPressed(int k){}
    public void keyReleased(int k){}
}
