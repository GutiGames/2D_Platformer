package main.java.jonanguti.platformer.Entity;

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



}
