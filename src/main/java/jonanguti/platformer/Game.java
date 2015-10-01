package main.java.jonanguti.platformer;


import javax.swing.*;

public class Game {

    public static void main(String[] args){

        JFrame frame = new JFrame("Platformer");
        frame.setContentPane(new GamePanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

    }
}
