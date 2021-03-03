package jFrame;

import javax.swing.*;
import java.awt.*;

public class MakeFrame extends JFrame {
    public static final int WIDTH = 900;
    public static final int HEIGHT = 700;

    public MakeFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setBackground(new Color(220, 27, 27));
        this.setResizable(false);
        this.setLayout(null);
        this.add(new HeadingPanel());
        this.setVisible(true);
        this.add(new BoardPanel());
        this.setVisible(true);
    }
}
