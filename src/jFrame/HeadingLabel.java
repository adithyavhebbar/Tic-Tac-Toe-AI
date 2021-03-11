package jFrame;

import javax.swing.*;
import java.awt.*;

public class HeadingLabel extends JLabel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HeadingLabel() {
        this.setText("Tic Tac Toe");
        this.setVerticalAlignment(JLabel.TOP);
        this.setLocation(WIDTH, HEIGHT);
        this.setFont(new Font("Helvetica", Font.PLAIN, 40));
        this.setBackground(new Color(0x444497));
        this.setForeground(new Color(0x0E0E0C));
    }
}
