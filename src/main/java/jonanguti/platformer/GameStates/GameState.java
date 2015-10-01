package main.java.jonanguti.platformer.GameStates;

public abstract class GameState {

    protected GSManager gsm;


    public void init(){}
    public void update(){}
    public void draw(java.awt.Graphics2D g){}
    public void keyPressed(int k){}
    public void keyReleased(int k){}


}
