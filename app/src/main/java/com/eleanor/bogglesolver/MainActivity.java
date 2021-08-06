package com.eleanor.bogglesolver;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.view.ViewCompat;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.eleanor.bogglesolver.Trie.Trie;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private BoardState boardState;
    Trie dictionaryTrie;
    ArrayList<ResultItem> resultItems;
    ResultsAdapter resultListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.boardState = new BoardState(getResources().getString(R.string.defaultText));
        createBoardView(boardState);

        //load dictionary
        this.dictionaryTrie = new Trie();
        InputStream dictionaryStream = this.getApplicationContext().getResources().openRawResource(R.raw.he);
        this.dictionaryTrie.loadDictionary(dictionaryStream);

        //bind results adapter to the view
        this.resultItems = new ArrayList<>();
        resultListAdapter = new ResultsAdapter(this, this.resultItems);
        ListView listView = (ListView) findViewById(R.id.main_results);
        listView.setAdapter(resultListAdapter);

        Button enterLettersButton = (Button) findViewById(R.id.enterLetters);
        enterLettersButton.setOnClickListener((View v) -> {
             this.showEnterLettersDialog();
        });

        Button solveButton = (Button) findViewById(R.id.solve);
        solveButton.setOnClickListener((View v) -> {
            ArrayList<ResultItem> results = BoardSearch.search(this.boardState, this.dictionaryTrie);
            results.sort(new Comparator<ResultItem>() {
                @Override
                public int compare(ResultItem lhs, ResultItem rhs) {
                    return -(Integer.compare(lhs.word.length(), rhs.word.length()));
                }
            });
            this.updateResults(results);
        });
    }


    private void showEnterLettersDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
        builder.setTitle(R.string.enterLetters);

        ViewGroup enterLetterView = findViewById(R.id.enterLettersLayout);
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.enter_letters_dialog, null);
        final EditText input = (EditText) viewInflated.findViewById(R.id.editTextBoardLetters);
        input.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(this.boardState.NUMBER_OF_BOARD_CELLS) });
        final TextView remainingLettersCounterView = (TextView) viewInflated.findViewById(R.id.remainingLettersCounterView);
        BoardState boardState = this.boardState;
        input.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void afterTextChanged(Editable s) {
                String remainingLettersText = String.format("%d / %2d", s.length(), boardState.NUMBER_OF_BOARD_CELLS );
                remainingLettersCounterView.setText(remainingLettersText);
                if (s.length() < boardState.NUMBER_OF_BOARD_CELLS)
                    remainingLettersCounterView.setTextColor(getColor(R.color.error));
                else
                    remainingLettersCounterView.setTextColor(getColor(R.color.ok));
            }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { }
        });

        input.setText(this.boardState.getLetters());
        input.setSelectAllOnFocus(true);
        builder.setView(viewInflated);

// Set up the buttons
        builder.setPositiveButton(R.string.okDialog, (dialog, which) -> {
            String letters = input.getText().toString();
            boardState.setLetters(letters);
            this.updateBoardView(this.boardState);
            this.clearResults();
            dialog.dismiss();
        });
        builder.setNegativeButton(R.string.cancelDialog, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void clearResults() {
        this.resultItems.clear();
        this.resultListAdapter.notifyDataSetChanged();
        TextView resultsLabel = findViewById(R.id.results_label);
        resultsLabel.setText("תוצאות");
    }

    private void updateResults(ArrayList<ResultItem> results) {
        this.clearResults();
        this.resultItems.addAll(results);
        this.resultListAdapter.notifyDataSetChanged();
        TextView resultsLabel = findViewById(R.id.results_label);
        String labelText = String.format("נמצאו %d תוצאות", results.size());
        resultsLabel.setText(labelText);
    }

    final int ID_SEED = View.generateViewId();

    private void createBoardView(BoardState boardState) {
        GridLayout lettersGrid = findViewById(R.id.board);
        lettersGrid.setColumnCount(boardState.BOARD_WIDTH);
        lettersGrid.setRowCount(boardState.BOARD_HEIGHT);
//        int cellWidth = lettersGrid.getWidth() / boardState.BOARD_WIDTH;
//        int cellHeight = lettersGrid.getHeight() / boardState.BOARD_HEIGHT;

        for (int i = 0; i < boardState.BOARD_HEIGHT ; i++) {
            for (int j = 0; j < boardState.BOARD_WIDTH ; j++) {
                TextView letterView = new TextView(this);
                letterView.setId(ID_SEED + boardState.getOffset(i, j));
                GridLayout.LayoutParams letterViewLayoutParams = new GridLayout.LayoutParams();
//                letterViewLayoutParams.width = cellWidth;
//                letterViewLayoutParams.height = cellHeight;
                letterView.setLayoutParams(letterViewLayoutParams);
                letterView.setGravity(Gravity.CENTER);
                lettersGrid.addView(letterView, letterViewLayoutParams);
            }
        }

        updateBoardView(boardState);
    }

    private void updateBoardView(BoardState boardState) {
        GridLayout lettersGrid = findViewById(R.id.board);
        for (int i = 0; i < boardState.BOARD_HEIGHT ; i++) {
            for (int j = 0; j < boardState.BOARD_WIDTH ; j++) {
                TextView letterView = lettersGrid.findViewById(ID_SEED + boardState.getOffset(i, j));
                letterView.setText(String.valueOf(boardState.getLetter(i, j)));
                letterView.setTextAppearance(R.style.BoardFontStyle);
            }
        }
    }
}