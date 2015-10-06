package main.java.jonanguti.platformer.Entity;

import main.java.jonanguti.platformer.GamePanel;
import main.java.jonanguti.platformer.Map.Tile;
import main.java.jonanguti.platformer.Map.TileMap;

import java.awt.*;

public abstract class MapObject {

    protected TileMap tileMap;
    protected int tileSize;
    protected double xmap;
    protected double ymap;

    //Position
    protected double x;
    protected double y;
    protected double dx;
    protected double dy;


    //Dimensions
    protected int width;
    protected int height;

    //C Box
    protected int cwidth;
    protected int cheight;

    //Collision
    protected int currRow;
    protected int currCol;
    protected double xDest;
    protected double yDest;
    protected double xTemp;
    protected double yTemp;
    protected boolean topLeft;
    protected boolean topRight;
    protected boolean bottomLeft;
    protected boolean bottomRight;

    //Animation
    protected Animation animation;
    protected int currentAction;
    protected int previousAcion;
    protected int facingRight;

    //Movement
    protected boolean left;
    protected boolean right;
    protected boolean up;
    protected boolean down;
    protected boolean jumping;
    protected boolean falling;

    //M Attributes
    protected double moveSpeed;
    protected double maxSpeed;
    protected double stopSped;
    protected double fallSpeed;
    protected double maxFallSpeed;
    protected double jumpStart;
    protected double stopJumpSpeed;

    public MapObject(TileMap tm){
        tileMap = tm;
        tileSize = tm.getTileSize();

    }

    public boolean intersects(MapObject o) {
        Rectangle r1 = getRectangle();
        Rectangle r2 = o.getRectangle();
        return r1.intersects(r2);

    }

    public Rectangle getRectangle(){
        return new Rectangle((int) x - cwidth, (int) y - cheight, cwidth, cheight);

    }

    public void calculateCorners(double x, double y){

        int leftTile = (int)( x - cwidth / 2) / tileSize;
        int rightTile = (int)( x + cwidth / 2 - 1) / tileSize;
        int topTile = (int)( y - cwidth / 2) / tileSize;
        int bottomTile = (int)( x + cwidth / 2 -1) / tileSize;

        int tl = tileMap.getype(topTile, leftTile);
        int tr = tileMap.getype(topTile, rightTile);
        int bl = tileMap.getype(bottomTile, leftTile);
        int br = tileMap.getype(bottomTile, rightTile);

        topLeft = tl == Tile.BLOCKED;
        topRight = tr == Tile.BLOCKED;
        bottomLeft = bl == Tile.BLOCKED;
        bottomRight = br == Tile.BLOCKED;

    }

    public void checkTileMapCollition(){
        currCol = (int) x / tileSize;
        currRow = (int) y / tileSize;

        xDest = x + dx;
        yDest = y + dy;

        xTemp = x;
        yTemp = y;

        calculateCorners(x, yDest);

        if (dy < 0 ){
            if (topLeft || topRight){
                dy = 0;
                yTemp = currRow * tileSize + cheight / 2;

            }
            else {
                yTemp += dy;
            }
        }

        if (dy > 0){
            if (bottomLeft || bottomRight){
                dy = 0;
                falling = false;
                yTemp = (currRow + 1) * tileSize - cheight / 2;

            }

            else {
                yTemp += dy;
            }

        }

        calculateCorners(xDest, y);

        if (dx < 0 ){
            if (topLeft || bottomLeft){
                dx = 0;
                xTemp = currRow * tileSize + cwidth / 2;

            }
            else {
                xTemp += dx;
            }
        }

        if (dx > 0){
            if (topRight || bottomRight){
                dx = 0;
                xTemp = (currCol + 1) * tileSize - cwidth / 2;

            }

            else {
                xTemp += dx;
            }

        }

        if (!falling){
            calculateCorners(x, yDest + 1);
            if (!bottomLeft && !bottomRight){
                falling = true;

            }
        }
    }

    public int getx(){
        return (int) x;
    }

    public int gety(){
        return (int) y;

    }

    public int getWidth(){
        return width;

    }
    public int getHeight(){
        return height;

    }
    public int getCHeight(){
        return cheight;

    }
    public int getCWidth(){
        return cwidth;

    }

    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;

    }

    public void setVector(double dx, double dy){
        this.dx = dx;
        this.dy = dy;

    }

    public void setMapPosition(){
        xmap = tileMap.getx();
        ymap = tileMap.gety();

    }

    public void setLeft(boolean b) {
        left = b;
    }

    public void setRight(boolean b) {
        right = b;
    }

    public void setUp(boolean b) {
        up = b;
    }

    public void setDown(boolean b) {
        down = b;
    }

    public void setJumping(boolean b) {
        jumping = b;
    }

    public boolean notOnScreen(){
        return x + xmap + width < 0 || x + xmap - width > GamePanel.WIDTH || y + ymap + height < 0 || y - ymap - height > GamePanel.HEIGHT;
    }





}
