package main.java.jonanguti.platformer.Entity;


import main.java.jonanguti.platformer.Map.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends MapObject {

    private int hp;
    private int maxhp;
    private int fire;
    private int maxFire;



    private boolean dead;
    private boolean flinching;
    private long flinchTimer;

    //attacks
    private boolean firing;
    private int fireCost;
    private int fireDam;

    private boolean melleAt;
    private int melee;
    private int meleeDamage;
    private int meleeRange;



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
    private static final int FIRE = 4;
    private static final int MELEAT = 5;


    public Player(TileMap tm) {
        super(tm);

        width = 30;
        height = 30;
        cwidth = 20;
        cheight = 20;

        moveSpeed = 0.3;
        maxSpeed = 1.6;
        stopSped = 0.4;
        fallSpeed = 0.15;
        maxFallSpeed = 4.0;
        jumpStart = -4.8;
        stopJumpSpeed = 0.3;

        facingRight = true;

        hp = maxhp = 5;
        fire = maxFire = 2500;

        fireCost = 200;
        fireDam = 5;

        melee = 8;
        meleeRange = 40;

        //Load Sprites

        try {
            BufferedImage spriteSheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/playersprites.gif"));

            sprites = new ArrayList<BufferedImage[]>();

            for (int i = 0; i < 7; i++) {
                BufferedImage[] bi = new BufferedImage[numFrames[i]];


                for (int j = 0; j < numFrames[i]; j++) {
                    if (i != 6) {
                        bi[j] = spriteSheet.getSubimage(j * width, i * height, width, height);


                    }
                    else {
                        bi[j] = spriteSheet.getSubimage(j * width * 2, i * height, width, height);
                    }
                }

                sprites.add(bi);
            }

        }
        catch (Exception e){
            e.printStackTrace();

        }

        animation = new Animation();
        currentAction = IDLE;
        animation.setFrames(sprites.get(IDLE));
        animation.setDelay(400);



    }

    public int getHp(){
        return hp;

    }

    public int getMaxhp(){
        return maxhp;

    }

    public int getFire(){
        return fire;

    }

    public int getMaxFire(){
        return maxFire;

    }

    public void setFiring(){
        firing = true;

    }

    public void setMelee(){
        melleAt = true;

    }

    private void getNextPosition(){
        //Movement
        if(left){
            dx -= moveSpeed;

            if (dx < -maxSpeed){
                dx = -maxSpeed;

            }
        }

        else if(right){
            dx += moveSpeed;

            if (dx > +maxSpeed){
                dx = +maxSpeed;

            }

        }

        else{
            if (dx > 0){
                dx -= stopSped;

                if (dx < 0){
                    dx = 0;

                }

            }

            else if (dx < 0){
                dx += stopSped;

                if (dx > 0){
                    dx = 0;

                }

            }

        }

        //Jumping
        if(jumping && !falling){
            dy = jumpStart;
            falling = true;

        }

        //Falling
        if (falling){

            if(dy > 0){
                dy += fallSpeed * 0.1;

            }
            else {
                dy += fallSpeed;
            }

            if(dy > 0){
                jumping = false;

            }

            if (dy < 0 && !jumping){
                dy +=stopJumpSpeed;

            }

            if (dy > maxFallSpeed){
                dy = maxFallSpeed;

            }
        }
    }

    public void update(){

        //Position
        getNextPosition();
        checkTileMapCollition();
        setPosition(xTemp, yTemp);

        //Animation
        if (melleAt){
            if(currentAction != MELEAT) {
                currentAction = MELEAT;
                animation.setFrames(sprites.get(MELEAT));
                animation.setDelay(50);
                width = 60;


            }

        }

        else if (firing){
            if (currentAction !=FIRE){
                currentAction = FIRE;
                animation.setFrames(sprites.get(FIRE));
                animation.setDelay(100);
                width = 30;

            }

        }

        else if (dy > 0){
            if (currentAction != FALLING){
                currentAction = FALLING;
                animation.setFrames(sprites.get(FALLING));
                animation.setDelay(100);
                width = 30;
            }

        }

        else  if (dy < 0){
            if (currentAction != JUMPING){
                currentAction = JUMPING;
                animation.setFrames(sprites.get(JUMPING));
                animation.setDelay(-1);
                width = 30;
            }

        }

        else if (left || right){
            if (currentAction != WALKING){
                currentAction = WALKING;
                animation.setFrames(sprites.get(WALKING));
                animation.setDelay(40);
                width = 30;
            }
        }
        else {
            if (currentAction != IDLE){
                currentAction = IDLE;
                animation.setFrames(sprites.get(IDLE));
                animation.setDelay(400);
                width = 30;
            }


        }

        animation.update();

        //Direction

        if (currentAction != MELEAT && currentAction != FIRE){
            if (right) facingRight = true;
            if (left) facingRight = false;

        }
    }

    public void draw(Graphics2D g){
        setMapPosition();

        //Player
        if (flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
            if (elapsed / 100 % 2 == 0){
                return;

            }
        }

        if (facingRight){
            g.drawImage(animation.getImage(),(int) (x + xmap - width / 2), (int) (y + ymap - height / 2), null);

        }
        else {
            g.drawImage(animation.getImage(),(int) (x + xmap - width / 2 + width), (int) (y + ymap - height / 2), -width, height, null);

        }

    }

}
