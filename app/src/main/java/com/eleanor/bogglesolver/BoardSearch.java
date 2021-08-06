package com.eleanor.bogglesolver;

import com.eleanor.bogglesolver.Trie.Trie;
import com.eleanor.bogglesolver.Trie.TrieNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//TODO: limit the search to find words of certain length or bigger
//TODO: Add path (on the board) of word found
//TODO: ?Allow duplicates of different paths?
public class BoardSearch {

    static public ArrayList<ResultItem> search(BoardState boardState, Trie dictionary) {
        ArrayList<ResultItem> resultItems = new ArrayList<>();
        boolean [][]visited = new boolean[boardState.BOARD_HEIGHT][boardState.BOARD_WIDTH];
        for (int i = 0; i < boardState.BOARD_HEIGHT; i++) {
            for (int j = 0; j < boardState.BOARD_WIDTH; j++) {
                search(boardState, visited, dictionary.root, "", i, j, resultItems);
            }
        }

        //remove duplicates
        Set<String> set = new HashSet<>(resultItems.size());
        resultItems.removeIf(p -> !set.add(p.word));
        return resultItems;
    }

    static private void search(BoardState boardState, boolean [][]visited,
                       TrieNode prefixLetterNode, String prefix,
                       int currentI, int currentJ,
                       ArrayList<ResultItem> resultItems) {
        if (visited[currentI][currentJ])
            return;

        char currentChar = boardState.getLetter(currentI, currentJ);
        TrieNode currentLetterNode = prefixLetterNode.getSuffixes(currentChar);
        if (currentLetterNode == null)
            return;

        String word = prefix + currentChar;
        if (currentLetterNode.isWord) {
            resultItems.add(new ResultItem(word));
        }

        if (!currentLetterNode.hasSuffixes())
            return;

        visited[currentI][currentJ] = true;

        for (int i = Math.max(0, currentI-1); i <= Math.min(boardState.BOARD_HEIGHT-1, currentI+1); i++) {
            for (int j = Math.max(0, currentJ-1); j <= Math.min(boardState.BOARD_WIDTH-1, currentJ+1); j++) {
                search(boardState, visited, currentLetterNode, word, i, j, resultItems);
            }
        }

        visited[currentI][currentJ] = false;
    }

}
