package com.eleanor.bogglesolver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private BoardState mBoardState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBoardState = new BoardState();
        updateBoardView(mBoardState);

//        BoardView boardview = findViewById(R.id.boardView);
//        boardview.setBoardState(mBoardState);

        Button enterLettersButton = (Button) findViewById(R.id.enterLetters);
        enterLettersButton.setOnClickListener((View v) -> {
            mBoardState.setLetters("0123456789abcdef");
            updateBoardView(mBoardState);
        });

        Button solveButton = (Button) findViewById(R.id.solve);
        solveButton.setOnClickListener((View v) -> {
            mBoardState.setLetters("אבגדהוזחטיכלמנספ");
            updateBoardView(mBoardState);
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