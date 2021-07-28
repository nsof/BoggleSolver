package com.eleanor.bogglesolver;

public class BoardState {
    public int BOARD_WIDTH;
    public int BOARD_HEIGHT;
    public int NUMBER_OF_BOARD_CELLS;
    public char[][] letters;

    public BoardState() {
        BOARD_WIDTH = 4;
        BOARD_HEIGHT = 4;
        NUMBER_OF_BOARD_CELLS = BOARD_WIDTH * BOARD_HEIGHT;
        letters = new char[this.BOARD_WIDTH][this.BOARD_HEIGHT];
        setLetters("");
    }

    public void setLetters(String lettersAsString) {
        if (lettersAsString.length() != NUMBER_OF_BOARD_CELLS) {
            lettersAsString = new String(new char[NUMBER_OF_BOARD_CELLS]).replace('\0', '*');
        }

        int idx = 0;
        for (int i = 0; i < this.BOARD_HEIGHT ; i++) {
            for (int j = 0; j < this.BOARD_WIDTH ; j++) {
                letters[i][j] = lettersAsString.charAt(idx);
                idx++;
            }
        }
    }

    public String getAsString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.BOARD_HEIGHT ; i++) {
            for (int j = 0; j < this.BOARD_WIDTH ; j++) {
                sb.append(letters[i][j]);
            }
        }

        return sb.toString();
    }
}
