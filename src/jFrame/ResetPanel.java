package jFrame;

import javax.swing.*;
import java.awt.*;

public class ResetPanel extends JPanel {
    public JButton resetButton;
    public ResetPanel() {
        this.resetButton.setName("reset");
        this.resetButton.setText("Reset");
        this.resetButton.setFont(new Font("Helvitica", Font.BOLD, 25));
//        this.resetButton.addActionListener(new );
        this.add(resetButton);
    }
}
