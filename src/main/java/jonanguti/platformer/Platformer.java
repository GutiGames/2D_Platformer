package main.java.jonanguti.platformer;


import java.awt.Canvas;

public class Platformer extends Canvas implements Runnable{

    private static final long serialVersionUID = -411619836947935590L;

    public static final int WIDTH = 640, HEIGHT = WIDTH /12 * 9;

    public Platformer(){
        new Window(WIDTH, HEIGHT, "Platformer",this);
    }

    public synchronized void start(){

    }

    public void run(){

    }

    public static void main (String[] args){
        new Platformer();

    }
}
