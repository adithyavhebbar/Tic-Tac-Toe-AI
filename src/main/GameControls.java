package main;

import jFrame.BoardPanel;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class GameControls {
    public char ai = 'X';
    public char human = 'O';
    private char currentPlayer = ai;

    public char nextPlayer() {
        this.currentPlayer = (this.currentPlayer == ai) ? human : ai;
        return this.currentPlayer;
    }

    public char getCurrentPlayer() {
        return this.currentPlayer;
    }

    public Map<String, Boolean> checkWinner(JButton[][] buttons, int rowClicked, int colClicked) {
        Map<String, Boolean> resultMap = new HashMap<>();
        resultMap.put("won", false);
        resultMap.put("drawn", false);
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                System.out.print(buttons[i][j].getText() + "  ");
//            }
//            System.out.println();
//        }
        boolean hasWon = true;
        for (int i = 1; i < buttons.length; i++) {
            if (buttons[rowClicked][i].getText().isEmpty() || buttons[rowClicked][i].getText()
                    .compareTo(buttons[rowClicked][i - 1].getText()) != 0) {
                hasWon = false;
            }
        }
        if (hasWon) {
            resultMap.put("won", true);
            return resultMap;

        }
        hasWon = true;
        for (int j = 1; j < buttons.length; j++) {
            if (buttons[j][colClicked].getText().isEmpty() || buttons[j][colClicked].getText()
                    .compareTo(buttons[j - 1][colClicked].getText()) != 0) {
                hasWon = false;
            }
        }
        if (hasWon) {
            resultMap.put("won", true);
            return resultMap;
        }


        if (rowClicked == colClicked) {
            hasWon = true;
            for (int i = 1; i < buttons.length; i++) {
                if (buttons[i][i].getText().isEmpty() || buttons[i][i].getText()
                        .compareTo(buttons[i - 1][i - 1].getText()) != 0) {
                    hasWon = false;
                }
            }
        }
        if (hasWon) {
            resultMap.put("won", true);
            return resultMap;
        }


        if (rowClicked + colClicked == buttons.length - 1) {
            hasWon = true;
            for (int i = 1, j = buttons.length - 2; (i < buttons.length && j >= 0); i++, j--) {
                if (buttons[i][j].getText().isEmpty() || buttons[i - 1][j + 1].getText()
                        .compareTo(buttons[i][j].getText()) != 0) {
                    hasWon = false;
                }
            }
        }
        if (hasWon) {
            resultMap.put("won", true);
            return resultMap;
        }

        boolean hasDrawn = true;
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    hasDrawn = false;
                }
            }
        }
        if (hasDrawn) {
            resultMap.put("drawn", true);
        }
        return resultMap;
    }

    public void setCurrentPlayer(char currentPlayer) {
        this.currentPlayer = currentPlayer;
    }


    //    AI player move
    public Map<String, Integer> bestMove(JButton[][] buttons, GameControls gameControls) {
        int bestScore = Integer.MIN_VALUE;
        Map<String, Integer> move = new HashMap<>(2);
        move.put("row", Integer.MAX_VALUE);
        move.put("col", Integer.MAX_VALUE);
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                if (buttons[i][j].getText().compareTo("") == 0) {
                    buttons[i][j].setText(gameControls.ai + "");
                    int score = minimax(buttons, 0,
                            false, gameControls, i, j, gameControls.ai);

                    if (score > bestScore) {
                        bestScore = score;
                        move.put("row", i);
                        move.put("col", j);

                        System.out.println("move:" + move);
                    }
                    buttons[i][j].setText("");
                }
            }
        }
        return move;
    }

    private int minimax(JButton[][] buttons, int depth,
                        boolean isMaximizer, GameControls gameControls,
                        int row, int col, char turn) {

        int result = gameControls.checkWinner(buttons);
        if (result == 1 && turn == gameControls.human) {
            return -1;
        } else if (result == 1 && turn == gameControls.ai) {
            return 1;
        } else if (result == 0) {
            return 0;
        }
        int bestScore;
        if (isMaximizer) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons.length; j++) {
                    if (buttons[i][j].getText().compareTo("") == 0) {
                        buttons[i][j].setText(gameControls.ai + "");
//                        System.out.println("Inside maximizer");
                        int score = minimax(buttons, depth + 1,
                                false, gameControls, i, j, gameControls.ai);
//                        System.out.println("Score:" + score + " for { " + i + "," + j + " }");
                        if (score > bestScore) {
                            bestScore = score;
                        }
                        buttons[i][j].setText("");
                    }
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons.length; j++) {
                    if (buttons[i][j].getText().compareTo("") == 0) {
                        buttons[i][j].setText(gameControls.human + "");
//                        System.out.println("Inside minimizer");
                        int score = minimax(buttons, depth + 1,
                                true, gameControls, i, j, gameControls.human);

//                        System.out.println("Score:" + score + " for { " + i + "," + j + " }");
                        if (score < bestScore) {
                            bestScore = score;
                        }
                        buttons[i][j].setText("");
                    }
                }
            }
        }
        return bestScore;
    }

    public int checkWinner(JButton[][] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i][0].getText().compareTo("") != 0
                    && buttons[i][1].getText().compareTo("") != 0
                    && buttons[i][2].getText().compareTo("") != 0
                    && buttons[i][0].getText().compareTo(buttons[i][1].getText()) == 0
                    && buttons[i][0].getText().compareTo(buttons[i][2].getText()) == 0
                    && buttons[i][1].getText().compareTo(buttons[i][2].getText()) == 0) {
                return 1;
            }
        }

        for (int i = 0; i < buttons.length; i++) {
            if (buttons[0][i].getText().compareTo("") != 0
                    && buttons[1][i].getText().compareTo("") != 0
                    && buttons[2][i].getText().compareTo("") != 0
                    && buttons[0][i].getText().compareTo(buttons[1][i].getText()) == 0
                    && buttons[0][i].getText().compareTo(buttons[2][i].getText()) == 0
                    && buttons[1][i].getText().compareTo(buttons[2][i].getText()) == 0) {
                return 1;
            }
        }
        if (buttons[0][0].getText().compareTo("") != 0
                && buttons[1][1].getText().compareTo("") != 0
                && buttons[2][2].getText().compareTo("") != 0
                && buttons[0][0].getText().compareTo(buttons[1][1].getText()) == 0
                && buttons[1][1].getText().compareTo(buttons[2][2].getText()) == 0
                && buttons[2][2].getText().compareTo(buttons[0][0].getText()) == 0) {
            return 1;
        }

        if (buttons[0][2].getText().compareTo("") != 0
                && buttons[1][1].getText().compareTo("") != 0
                && buttons[2][0].getText().compareTo("") != 0
                && buttons[0][2].getText().compareTo(buttons[1][1].getText()) == 0
                && buttons[1][1].getText().compareTo(buttons[2][0].getText()) == 0
                && buttons[2][0].getText().compareTo(buttons[0][2].getText()) == 0) {
            return 1;
        }

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    return 2;
                }
            }
        }
        return 0;
    }
}
