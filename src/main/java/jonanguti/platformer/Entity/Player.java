package main.java.jonanguti.platformer.Entity;


import main.java.jonanguti.platformer.Map.TileMap;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends MapObject {

    private int hp;
    private int maxhp;
    private int attack;
    private int mattack;


    private boolean dead;
    private boolean flinching;
    private long flinchTime;

    //attacks
    private boolean firing;
    private int fireCost;
    private int fireDam;

    private boolean gliding;

    //Animations
    private ArrayList<BufferedImage[]> sprites;
    private final int[] numFrames = {
            2, 8, 1, 2, 4, 5
    };

    //Actions
    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;
    private static final int GLIDING = 4;
    private static final int FIRE = 5;


    public Player(TileMap tm) {

        super(tm);
    }
}
