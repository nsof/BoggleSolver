package com.eleanor.bogglesolver.Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Trie {
    public TrieNode root = new TrieNode();
    public int numberOfWords = 0;

    public Trie() {
    }


    public void loadDictionary(InputStream dictionary) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(dictionary));
        try {
            while(reader.ready()) {
                String word = reader.readLine();
                insert(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
