package com.eleanor.bogglesolver.Trie;

import java.util.HashMap;

public class TrieNode {
    private final HashMap<Character, TrieNode> suffixes;
    private final char content;
    public boolean isWord;
    public int numberOfSuffixes;

    public TrieNode(char c) {
        this.content = c;
        this.suffixes = new HashMap<>();
        this.isWord = false;
        this.numberOfSuffixes = 0;
    }

    public boolean hasSuffixes() {
        return this.suffixes.size() > 0;
    }

    public TrieNode insertSuffix(char c) {
        this.numberOfSuffixes++;
        TrieNode suffix = this.suffixes.get(c);
        if (suffix == null) {
            suffix = new TrieNode(c);
            this.suffixes.put(c, suffix);
        }
        return suffix;
    }

    public TrieNode getSuffixes(char c) {
        return this.suffixes.get(c);
    }
}