package com.eleanor.bogglesolver;

public class BoardState {
    public int BOARD_WIDTH;
    public int BOARD_HEIGHT;
    public int NUMBER_OF_BOARD_CELLS;
    public char[][] mLetters;

    public BoardState() {
        BOARD_WIDTH = 4;
        BOARD_HEIGHT = 4;
        NUMBER_OF_BOARD_CELLS = BOARD_WIDTH * BOARD_HEIGHT;
        mLetters = new char[this.BOARD_WIDTH][this.BOARD_HEIGHT];
        setLetters("");
    }

    public void setLetters(String lettersAsString) {
        if (lettersAsString.length() != NUMBER_OF_BOARD_CELLS) {
            lettersAsString = new String(new char[NUMBER_OF_BOARD_CELLS]).replace('\0', '*');
        }
/* (Letter order and positing in state)

+-----------+-----------+-----------+-----------+
| L00 (0,0) | L01 (0,1) | L02 (0,2) | L03 (0,3) |
+-----------+-----------+-----------+-----------+
| L04 (1,0) | L05 (1,1) | L06 (1,2) | L07 (1,3) |
+-----------+-----------+-----------+-----------+
| L08 (2,0) | L09 (2,1) | L10 (2,2) | L11 (2,3) |
+-----------+-----------+-----------+-----------+
| L12 (3,0) | L13 (3,2) | L14 (3,2) | L15 (3,3) |
+-----------+-----------+-----------+-----------+

 */
        int idx = 0;
        for (int i = 0; i < this.BOARD_HEIGHT ; i++) {
            for (int j = 0; j < this.BOARD_WIDTH ; j++) {
                mLetters[i][j] = lettersAsString.charAt(idx);
                idx++;
            }
        }
    }
}
