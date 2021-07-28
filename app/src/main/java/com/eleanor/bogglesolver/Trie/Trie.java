package com.eleanor.bogglesolver.Trie;

public class Trie {
    public TrieNode root = new TrieNode();
    public int numberOfWords = 0;

    public Trie() {
    }

    public void insert(String word) {
        TrieNode current = root;

        for (char l: word.toCharArray()) {
            current.numberOfSuffixes++;
            current = current.children.computeIfAbsent(l, c -> new TrieNode());
        }

        current.isWord = true;

        this.numberOfWords++;
    }

    public TrieNode containsPrefix(String word) {
        TrieNode current = root;
        for (char c: word.toCharArray()) {
            current = current.children.get(c);
            if (current == null)
                break;
        }
        return current;
    }
}
