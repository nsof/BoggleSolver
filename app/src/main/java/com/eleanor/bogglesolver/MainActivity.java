package com.eleanor.bogglesolver;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        updateBoardView(boardState);


        //load dictionary
        this.dictionaryTrie = new Trie();
        InputStream dictionaryStream = this.getApplicationContext().getResources().openRawResource(R.raw.he);
        this.dictionaryTrie.loadDictionary(dictionaryStream);

        //bind results adapter to the view
        this.resultItems = new ArrayList<>();
        resultListAdapter = new ResultsAdapter(this, this.resultItems);
        ListView listView = (ListView) findViewById(R.id.main_results);
        listView.setAdapter(resultListAdapter);

//        BoardView boardview = findViewById(R.id.boardView);
//        boardview.setBoardState(mBoardState);

        Button enterLettersButton = (Button) findViewById(R.id.enterLetters);
        enterLettersButton.setOnClickListener((View v) -> {
             this.showEnterLettersDialog();
//            boardState.setLetters("אבגדהוזחטיכלמנספ");
//            updateBoardView(boardState);
        });

        Button solveButton = (Button) findViewById(R.id.solve);
        solveButton.setOnClickListener((View v) -> {
            this.resultItems.clear();
            this.resultListAdapter.notifyDataSetChanged();
//            resultListAdapter.addAll(Arrays.asList("א", "ב", "ג", "ד", "ה", "ו" ).stream().map(item-> new ResultItem(item)).collect(Collectors.toList()));
            this.resultItems.addAll(BoardSearch.search(this.boardState, this.dictionaryTrie));
            this.resultListAdapter.notifyDataSetChanged();
        });
    }


    private void showEnterLettersDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("הכנס אותיות");

        ViewGroup enterLetterView = findViewById(R.id.enterLettersLayout);
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.enter_letters_dialog, null);
        final EditText input = (EditText) viewInflated.findViewById(R.id.editTextBoardLetters);
        input.setHint(this.boardState.getLetters());

        builder.setView(viewInflated);

// Set up the buttons
        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    dialog.dismiss();
                    String letters = input.getText().toString();
                    boardState.setLetters(letters);
            });
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.show();
    }


    private void updateBoardView(BoardState boardState) {
        TextView lettersTextView = findViewById(R.id.lettersTextView);
        String letters = boardState.getLetters();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < boardState.BOARD_HEIGHT; i++) {
            sb.append(letters.substring(i*boardState.BOARD_WIDTH, (i+1)*boardState.BOARD_WIDTH));
            sb.append("\n");
        }
        lettersTextView.setText(sb.toString());
/*
        for (int i = 0; i < boardState.BOARD_HEIGHT ; i++) {
            for (int j = 0; j < boardState.BOARD_WIDTH ; j++) {
                String resourceName = "textView_" + String.format("%d%d", i, j);
                String packageName = getApplicationContext().getPackageName();
                int id = getResources().getIdentifier(resourceName, "id", packageName);
                if(id != 0) {
                    TextView textView = (TextView) findViewById(id);
                    textView.setText(Character.toString(boardState.mLetters[i][j]));
                }
            }
        }
*/
    }
}