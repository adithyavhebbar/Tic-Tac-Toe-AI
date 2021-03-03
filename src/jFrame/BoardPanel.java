package jFrame;

import listeners.ButtonClickedListener;
import main.GameControls;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BoardPanel extends JPanel {
    GameControls gameControls = new GameControls();
    public JButton[][] buttons = new JButton[3][3];

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
                this.add(button);
            }
        }
        gameControls.setCurrentPlayer(gameControls.ai);
        this.setBounds(300, 200, 300, 300);
        this.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        this.setAlignmentY(JPanel.CENTER_ALIGNMENT);

        Map<String, Integer> move = gameControls.bestMove(buttons, this.gameControls);
        buttons[move.get("row")][move.get("col")].setText(gameControls.ai+"");
        buttons[move.get("row")][move.get("col")].setEnabled(false);
        gameControls.setCurrentPlayer(gameControls.human);
    }


    public void displayWinner(String message) {
        JOptionPane.showMessageDialog(this, message, "Alert", JOptionPane.WARNING_MESSAGE);
    }
}
