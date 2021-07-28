package com.eleanor.bogglesolver.Trie;

import java.util.HashMap;

public class TrieNode {
    public HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
//    public String content;
    public boolean isWord;
    public int numberOfSuffixes;
//    public char prefix;


//    public HashMap<Character, TrieNode> getChildren() {
//        return children;
//    }

/*
    public void setChildren(HashMap<Character, TrieNode> children) {
        this.children = children;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setIsWord(boolean isWord) {
        this.isWord = isWord;
    }

    public int getNumberOfSuffixes() {
        return numberOfSuffixes;
    }

    public void setNumberOfSuffixes(int numberOfSuffixes) {
        this.numberOfSuffixes = numberOfSuffixes;
    }
*/
}