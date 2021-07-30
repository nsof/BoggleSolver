package com.eleanor.bogglesolver;

import com.eleanor.bogglesolver.Trie.Trie;

import org.junit.Test;

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
        BoardState boardState = new BoardState();
        boardState.setLetters("אבגדהוזחטיכלמנספ");
        Trie dictionary = new Trie();
        insertSomeHebrewElements(dictionary);
        BoardSearch.search(boardState, dictionary);
    }
}
