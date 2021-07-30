package com.eleanor.bogglesolver.Trie;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrieTest {
    public void insertSomeEnglishElements(Trie t) {
        t.insert("dog");
        t.insert("panda");
        t.insert("cat");
        t.insert("door");
        t.insert("pokemon");
        t.insert("caterpillar");
    }

    @Test
    public void testInsertElementsToTrie() {
        Trie trie = new Trie();
        insertSomeEnglishElements(trie);

        assertEquals(trie.numberOfWords, 6);


        TrieNode node = trie.containsPrefix("dog");
        assertNotNull(node);
        assertTrue(node.isWord);
        node = trie.containsPrefix("do");
        assertNotNull(node);
        assertFalse(node.isWord);
        assertEquals(node.numberOfSuffixes, 2);
    }
}
