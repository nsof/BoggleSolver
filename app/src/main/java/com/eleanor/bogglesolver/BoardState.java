package com.eleanor.bogglesolver;

public class BoardState {
    public int BOARD_WIDTH;
    public int BOARD_HEIGHT;
    public int NUMBER_OF_BOARD_CELLS;
    public char[][] letters;

    public BoardState(String input) {
        BOARD_WIDTH = 4;
        BOARD_HEIGHT = 4;
        NUMBER_OF_BOARD_CELLS = BOARD_WIDTH * BOARD_HEIGHT;
        letters = new char[this.BOARD_WIDTH][this.BOARD_HEIGHT];
        setLetters(input);
    }

    public void setLetters(String lettersAsString) {
        lettersAsString = String.format("%-" + NUMBER_OF_BOARD_CELLS + "." + NUMBER_OF_BOARD_CELLS + "s", lettersAsString);

        int idx = 0;
        for (int i = 0; i < this.BOARD_HEIGHT ; i++) {
            for (int j = 0; j < this.BOARD_WIDTH ; j++) {
                letters[i][j] = lettersAsString.charAt(idx);
                idx++;
            }
        }
    }

    public String getLetters() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.BOARD_HEIGHT ; i++) {
            for (int j = 0; j < this.BOARD_WIDTH ; j++) {
                sb.append(letters[i][j]);
            }
        }

        return sb.toString();
    }
}
