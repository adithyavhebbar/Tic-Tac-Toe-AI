package jFrame;

import javax.swing.*;
import java.awt.*;

public class MakeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 900;
	public static final int HEIGHT = 700;

	public MakeFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setBackground(new Color(0x76b4bd));
		this.setResizable(false);
		this.setBackground(new Color(220, 27, 27));
		this.setResizable(false);
		this.setLayout(null);
//		this.add(new HeadddingPanel());
		this.setVisible(true);
		this.add(new BoardPanel());
		this.setVisible(true);
		this.setTitle("Tic-Tac-Toe");
		this.setLocationRelativeTo(null);
	}
}
