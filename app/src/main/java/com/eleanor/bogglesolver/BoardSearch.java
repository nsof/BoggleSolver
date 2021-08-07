package com.eleanor.bogglesolver;

import androidx.core.util.Pair;

import com.eleanor.bogglesolver.Trie.Trie;
import com.eleanor.bogglesolver.Trie.TrieNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//TODO: limit the search to find words of certain length or bigger
//TODO: Add path (on the board) of word found
//TODO: ?Allow duplicates of different paths?
public class BoardSearch {

    static public ArrayList<ResultItem> search(GameBoard gameBoard, Trie dictionary) {
        ArrayList<ResultItem> resultItems = new ArrayList<>();
        boolean [][]visited = new boolean[gameBoard.BOARD_HEIGHT][gameBoard.BOARD_WIDTH];
        ArrayList<Pair<Integer,Integer>> path = new ArrayList<>();
        for (int i = 0; i < gameBoard.BOARD_HEIGHT; i++) {
            for (int j = 0; j < gameBoard.BOARD_WIDTH; j++) {
                search(gameBoard, visited, dictionary.root, i, j, "", path, resultItems);
            }
        }

        //remove duplicates
        Set<String> set = new HashSet<>(resultItems.size());
        resultItems.removeIf(p -> !set.add(p.word));
        return resultItems;
    }

    static private void search(GameBoard gameBoard, boolean [][]visited,
                               TrieNode prefixLetterNode,
                               int currentI, int currentJ,
                               String prefix, ArrayList<Pair<Integer, Integer>> path,
                               ArrayList<ResultItem> resultItems) {
        if (visited[currentI][currentJ])
            return;

        char currentChar = gameBoard.getLetter(currentI, currentJ);
        TrieNode currentLetterNode = prefixLetterNode.getSuffixes(currentChar);
        if (currentLetterNode == null)
            return;


        //TODO there's a way to improve memory/perf by not copying the array
        // and pushing and popping the current location to path rather than
        // allocating a new array, adding current and letting the stack take care of the removal
        ArrayList<Pair<Integer, Integer>> pathToCurrent = new ArrayList<>(path); // this is a shallow copy which is fine
        pathToCurrent.add(new Pair<>(currentI, currentJ));

        String word = prefix + currentChar;
        if (currentLetterNode.isWord) {
            resultItems.add(new ResultItem(word, pathToCurrent));
        }

        if (!currentLetterNode.hasSuffixes())
            return;

        visited[currentI][currentJ] = true;

        for (int i = Math.max(0, currentI-1); i <= Math.min(gameBoard.BOARD_HEIGHT-1, currentI+1); i++) {
            for (int j = Math.max(0, currentJ-1); j <= Math.min(gameBoard.BOARD_WIDTH-1, currentJ+1); j++) {
                search(gameBoard, visited, currentLetterNode, i, j, word, pathToCurrent, resultItems);
            }
        }

        visited[currentI][currentJ] = false;
    }

}
