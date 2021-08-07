package com.eleanor.bogglesolver;

import com.eleanor.bogglesolver.Trie.Trie;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class BoardSearchTest {

    private void insertSomeHebrewElements(Trie t) {
        t.insert("אב");
        t.insert("אבג");
        t.insert("אבי");
        t.insert("אהב");
        t.insert("אני");
        t.insert("אנחנו");
        t.insert("בגד");
    }

    @Test
    public void testSearchWords() {
        GameBoard gameBoard = new GameBoard("אבגדהוזחטיכלמנספ");
        Trie dictionary = new Trie();
        insertSomeHebrewElements(dictionary);
        ArrayList<ResultItem> results = BoardSearch.search(gameBoard, dictionary);
        assertNotNull(results);
    }
}
