package com.eleanor.bogglesolver;

import androidx.core.util.Pair;

import java.util.ArrayList;

public class ResultItem {
    public String word;
    public ArrayList<Pair<Integer,Integer>> path;

    public ResultItem(String word, ArrayList<Pair<Integer, Integer>> path) {
        this.word = word;
        this.path = new ArrayList<>(path); // this is a shallow copy which is fine
    }
}
