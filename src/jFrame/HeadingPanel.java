package jFrame;

import javax.swing.*;
import java.awt.*;

public class HeadingPanel extends JPanel {
    public HeadingPanel() {
        this.setPreferredSize(new Dimension(30, 30));
        this.setBounds(420, 0, 110, 30);
        this.add(new HeadingLabel());
    }
}
