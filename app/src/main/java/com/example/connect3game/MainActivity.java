package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//  0: empty   1: circle ; 2: cross
    int[] gameState = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    int activePlayer = 1;
    boolean isGameActive = true;

    public void dropIn(View v){
        ImageView counter = (ImageView) v;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 0 && isGameActive) {
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-2000);

            if (activePlayer == 1) {
                counter.setImageResource(R.drawable.red_cross);
                activePlayer = 2;
            } else {
                counter.setImageResource(R.drawable.circle);
                activePlayer = 1;
            }
            counter.animate().translationYBy(2000).rotation(3600).setDuration(100);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 0) {

                    isGameActive = false;

                    String msg = "";

                    if (activePlayer == 1) {
                        msg = "Circles";
                    } else if (activePlayer == 2) {
                        msg = "Crosses";
                    }

                    TextView tv = (TextView) findViewById(R.id.textView);
                    Button playAgainButton = (Button) findViewById(R.id.button);
                    tv.setText(msg + " has won!");
                    tv.setVisibility(v.VISIBLE);
                    playAgainButton.setVisibility(v.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View v){

        TextView tv = (TextView) findViewById(R.id.textView);
        Button playAgainButton = (Button) findViewById(R.id.button);

        tv.setVisibility(v.INVISIBLE);
        playAgainButton.setVisibility(v.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0;i<gridLayout.getChildCount();i++){

            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i=0;i<gameState.length;i++){
            gameState[i] = 0;
        }

        activePlayer = 1;
        isGameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}