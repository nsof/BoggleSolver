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

        Button button = (Button) findViewById(R.id.enterLetters);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mBoardState.setLetters("0123456789abcdef");
                updateBoardView(mBoardState);
            }
        });
    }


    private void updateBoardView(BoardState boardState) {
        for (int i = 0; i < 1 ; i++) {
            for (int j = 0; j < boardState.BOARD_WIDTH ; j++) {
                String resourceName = "textView_" + String.format("%d%d", i, j);
                String packageName = getApplicationContext().getPackageName();
                int id = getResources().getIdentifier(resourceName, "id", packageName);
                if(id != 0) {
//                    TextView textView = (TextView) findViewById(R.id.textView_11);
                    TextView textView = (TextView) findViewById(id);
                    textView.setText(Character.toString(boardState.mLetters[i][j]));
                }
            }
        }
    }
}