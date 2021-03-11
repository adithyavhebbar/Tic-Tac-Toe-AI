package jFrame;

import javax.swing.*;
import java.awt.*;

public class HeadingPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HeadingPanel() {
        this.setPreferredSize(new Dimension(30, 30));
        this.setBounds(420, 0, 210, 100);
        this.add(new HeadingLabel());
    }
}
