package com.eleanor.bogglesolver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultsAdapter extends ArrayAdapter<ResultItem> {
    public ResultsAdapter(Context context, ArrayList<ResultItem> results) {
        super(context, 0, results);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ResultItem resultItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_result, parent, false);
        }
        // Lookup view for data population
        TextView resultWord = (TextView) convertView.findViewById(R.id.result_word);
        TextView resultLength = (TextView) convertView.findViewById(R.id.result_length);
        // Populate the data into the template view using the data object
        resultWord.setText(resultItem.word);
        resultLength.setText(String.valueOf(resultItem.word.length()));
        // Return the completed view to render on screen
        return convertView;
    }

}