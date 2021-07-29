package com.eleanor.bogglesolver;

import com.eleanor.bogglesolver.Trie.Trie;
import com.eleanor.bogglesolver.Trie.TrieNode;

import java.util.ArrayList;

public class BoardSearch {

    public ArrayList<ResultItem> search(BoardState boardState, Trie dictionary) {
        ArrayList<ResultItem> resultItems = new ArrayList<>();
        Boolean [][]visited = new Boolean[boardState.BOARD_HEIGHT][boardState.BOARD_WIDTH];
        for (int i = 0; i < boardState.BOARD_HEIGHT; i++) {
            for (int j = 0; j < boardState.BOARD_WIDTH; j++) {
                search(boardState, visited, dictionary.root, "", i, j, resultItems);
            }
        }

        return resultItems;
    }

    public void search(BoardState boardState, Boolean [][]visited,
                       TrieNode prefixLetterNode, String prefix,
                       int currentI, int currentJ,
                       ArrayList<ResultItem> resultItems) {
        if (visited[currentI][currentJ])
            return;

        char currentChar = boardState.letters[currentI][currentJ];
        TrieNode currentLetterNode = prefixLetterNode.children.get(currentChar);
        if (currentLetterNode == null)
            return;

        String word = prefix + currentChar;
        if (currentLetterNode.isWord) {
            resultItems.add(new ResultItem(word));
        }

        if (currentLetterNode.children.size() == 0)
            return;

        visited[currentI][currentJ] = true;

        for (int i = Math.max(0, currentI-1); i < Math.min(boardState.BOARD_HEIGHT, currentI+1); i++) {
            for (int j = Math.max(0, currentJ-1); j < Math.min(boardState.BOARD_WIDTH, currentJ+1); j++) {
                search(boardState, visited, currentLetterNode, word, i, j, resultItems);
            }
        }

        visited[currentI][currentJ] = false;
    }

}
