package com.eleanor.bogglesolver;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
public class BoardView extends View {
    private GameBoard gameBoard;
    private TextView[][] cells;

    public BoardView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public BoardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        createViews();
    }

    private void createViews() {
        // create textviews, position them, add them to parent (constraint) layout
//        cells = new TextView[this.boardState.BOARD_HEIGHT][this.boardState.BOARD_HEIGHT];
//        ConstraintLayout containerLayout = findViewById(R.id.containerLayout);
//        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.MATCH_CONSTRAINT);
//        for (int i = 0; i < this.boardState.BOARD_HEIGHT ; i++) {
//            for (int j = 0; j < this.boardState.BOARD_WIDTH; j++) {
//                String resourceName = "textView_" + String.format("%d%d", i, j);
//            }
//        }
    }
}