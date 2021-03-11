package jFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import listeners.ButtonClickedListener;
import main.GameControls;

public class BoardPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GameControls gameControls = new GameControls();
	public JButton[][] buttons = new JButton[3][3];
	
	public JButton resetButton = new JButton();

	public BoardPanel() {
		ButtonClickedListener bcl = new ButtonClickedListener(this, gameControls);
		this.setLayout(new GridLayout(3, 3));
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JButton button = new JButton();
				String s = i + "" + j;
				button.setText("");
				button.setName(s);
				button.setFont(new Font("Helvetica", Font.BOLD, 25));
				button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				this.buttons[i][j] = button;
				this.buttons[i][j].addActionListener(bcl);
				this.buttons[i][j].setBackground(new Color(0xffc425));
				this.add(button);
			}
		}
		
//		resetButton.setName("reset");
//		resetButton.setText("Reset");
//		resetButton.setFont(new Font("Helvetica", Font.BOLD, 30));
//		resetButton.setBorder(BorderFactory.createLineBorder(Color.CYAN));
//		resetButton.setBackground(new Color(0xffc425));
//		this.add(resetButton, WIDTH);
		gameControls.setCurrentPlayer(gameControls.ai);
		this.setBounds(300, 200, 300, 300);
//		this.setAlignmentX(JPanel.CENTER_ALIGNMENT);
//		this.setAlignmentY(JPanel.CENTER_ALIGNMENT);

		Map<String, Integer> move = gameControls.bestMove(buttons, this.gameControls);
		buttons[move.get("row")][move.get("col")].setText(gameControls.ai + "");
//		buttons[move.get("row")][move.get("col")].setEnabled(false);
		buttons[move.get("row")][move.get("col")].setBackground(new Color(0x009688));
		buttons[move.get("row")][move.get("col")].setForeground(new Color(0xffffff));
		gameControls.setCurrentPlayer(gameControls.human);
	}

	public void displayWinner(String message) {
		JOptionPane.showMessageDialog(this, message, "Alert", JOptionPane.WARNING_MESSAGE);
	}
}
