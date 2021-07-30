package com.eleanor.bogglesolver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.eleanor.bogglesolver.Trie.Trie;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private BoardState boardState;
    Trie dictionaryTrie;
    ArrayList<ResultItem> resultItems;
    ResultsAdapter resultListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.boardState = new BoardState();
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
            boardState.setLetters("אבגדהוזחטיכלמנספ");
            updateBoardView(boardState);
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


    private void updateBoardView(BoardState boardState) {
        TextView lettersTextView = findViewById(R.id.lettersTextView);
        String letters = boardState.getAsString();
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