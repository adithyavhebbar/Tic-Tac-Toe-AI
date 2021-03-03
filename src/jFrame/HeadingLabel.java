package jFrame;

import javax.swing.*;
import java.awt.*;

public class HeadingLabel extends JLabel {
    public HeadingLabel() {
        this.setText("Tic-Tac-Toe");
        this.setVerticalAlignment(JLabel.TOP);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setFont(new Font("Futura", Font.PLAIN, 20));
        this.setBackground(new Color(0x444497));
        this.setForeground(new Color(0x0E0E0C));
    }
}
