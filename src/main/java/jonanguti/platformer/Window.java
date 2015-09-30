package main.java.jonanguti.platformer;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

    private static final long serialVersionUID = 3480711530667422306L;

    public Window (int w, int h, String title, Platformer platformer){

        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(w, h));
        frame.setMaximumSize(new Dimension(w, h));
        frame.setMinimumSize(new Dimension(w, h));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(platformer);
        frame.setVisible(true);
        platformer.start();

    }
}
