package main.java.jonanguti.platformer;


import java.awt.*;
import java.awt.image.BufferStrategy;

public class Platformer extends Canvas implements Runnable{

    private static final long serialVersionUID = -411619836947935590L;

    public static final int WIDTH = 640, HEIGHT = WIDTH /12 * 9;

    private Thread thread;
    private boolean running = false;

    public Platformer(){
        new Window(WIDTH, HEIGHT, "Platformer",this);
    }

    public synchronized void start(){

        thread = new Thread(this);
        thread.start();
        running = true;

    }

    public synchronized void stop(){

        try {
            thread.join();
            running = false;

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void run(){

        long lastTime = System.nanoTime();
        double amoutOfTicks = 60.0;
        double ns = 1000000000 / amoutOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running){

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1){
                tick();
                delta --;
            }
            if (running)
                render();
            frames ++;

            if (System.currentTimeMillis() - timer > 1000){

                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;

            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.green);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.dispose();
        bs.show();
    }

    private void tick() {

    }

    public static void main (String[] args){
        new Platformer();

    }
}
