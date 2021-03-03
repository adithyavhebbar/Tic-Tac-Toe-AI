package listeners;

import jFrame.BoardPanel;
import jFrame.MakeFrame;
import jFrame.WinnerPanel;
import main.GameControls;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Map;

public class ButtonClickedListener implements ActionListener {
    public GameControls gameControls;
    private BoardPanel boardPanel;

    public ButtonClickedListener(BoardPanel boardPanel, GameControls gameControls) {
        this.gameControls = gameControls;
        this.boardPanel = boardPanel;
    }

    public ButtonClickedListener() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        int rowClicked = Integer.parseInt(button.getName()) / 10;
        int colClicked = Integer.parseInt(button.getName()) % 10;
        if (gameControls.getCurrentPlayer() == gameControls.ai) {
            System.out.println("ai turn:" + gameControls.getCurrentPlayer());
            this.boardPanel.buttons[rowClicked][colClicked].setText(gameControls.ai + "");
            Map<String, Boolean> result = gameControls.checkWinner(this.boardPanel.buttons, rowClicked, colClicked);
            if (result.get("won")) {
                System.out.println("Player " + this.gameControls.getCurrentPlayer() + " has won");
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        boardPanel.buttons[i][j].setEnabled(false);
                    }
                }
                boardPanel.displayWinner("Player " + gameControls.ai + " has won the game");
            } else if (result.get("drawn")) {
                System.out.println("Match drawn");
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        boardPanel.buttons[i][j].setEnabled(false);
                    }
                }
                boardPanel.displayWinner("Match has been drawn");
            }
            this.boardPanel.buttons[rowClicked][colClicked].setEnabled(false);
            gameControls.setCurrentPlayer(gameControls.human);
            System.out.println("ai turn after:" + gameControls.getCurrentPlayer());
        } else {
            this.boardPanel.buttons[rowClicked][colClicked].setText(gameControls.human + "");
            System.out.println("human turn:" + gameControls.getCurrentPlayer());
            Map<String, Boolean> result = gameControls.checkWinner(this.boardPanel.buttons, rowClicked, colClicked);
            if (result.get("won")) {
                System.out.println("Player " + this.gameControls.getCurrentPlayer() + " has won");
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        boardPanel.buttons[i][j].setEnabled(false);
                    }
                }
                boardPanel.displayWinner("Player " + gameControls.human + " has won the game");
            } else if (result.get("drawn")) {
                System.out.println("Match drawn");
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        boardPanel.buttons[i][j].setEnabled(false);
                    }
                }
                boardPanel.displayWinner("Match has been drawn");
            }
            this.boardPanel.buttons[rowClicked][colClicked].setEnabled(false);
            gameControls.setCurrentPlayer(gameControls.ai);
            System.out.println("human turn after:" + gameControls.getCurrentPlayer());

//            Call AI player moves

//            Map<String, Boolean> aiResult1 = gameControls.checkWinner(this.boardPanel.buttons, move.get("row"), move.get("col"));
            if (!result.get("won") && !result.get("drawn")) {
                Map<String, Integer> move = gameControls.bestMove(this.boardPanel.buttons, gameControls);
                this.boardPanel.buttons[move.get("row")][move.get("col")].setText(gameControls.ai + "");
                this.boardPanel.buttons[move.get("row")][move.get("col")].setEnabled(false);

                Map<String, Boolean> aiResult = gameControls.checkWinner(this.boardPanel.buttons, move.get("row"), move.get("col"));

                if (aiResult.get("won")) {
                    System.out.println("Player " + this.gameControls.getCurrentPlayer() + " has won");
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            boardPanel.buttons[i][j].setEnabled(false);
                        }
                    }
                    boardPanel.displayWinner("Player " + gameControls.getCurrentPlayer() + " has won the game");
                } else if (aiResult.get("drawn")) {
                    System.out.println("Match drawn");
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            boardPanel.buttons[i][j].setEnabled(false);
                        }
                    }
                    boardPanel.displayWinner("Match has been drawn");
                }
                this.gameControls.setCurrentPlayer(gameControls.human);
            }

        }
    }
}
