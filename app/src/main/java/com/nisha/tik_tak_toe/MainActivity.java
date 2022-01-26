package com.nisha.tik_tak_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0: yellow
    //1: red
    //-1:empty
    int[] gameState = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int activePlayer = 0;
    boolean gameActive = true;
    int[][] winningPositions = {{0,1,2},{2,3,4},{5,6,7},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    public void dropIn (View view){
        ImageView counter = (ImageView) view;
        TextView textViewWinnerAnnouncer = (TextView) findViewById(R.id.textViewWinner);
        Button buttonPlayAgain = (Button) findViewById(R.id.buttonPlayAgain);

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]== -1 && gameActive) { // else if the user taps again the next color coin changes

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                //check if 3 positions of the gamestate is of the same person
                String gameResultTextView="";
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]]  && gameState[winningPosition[0]] != -1) {
                    //someone has won;
                    gameActive = false;
                    if (activePlayer == 1) {
                        gameResultTextView = "YELLOW IS THE WINNER";
                    }
                    else{
                        gameResultTextView = "RED IS THE WINNER";
                    }
                    textViewWinnerAnnouncer.setText(gameResultTextView);
                    buttonPlayAgain.setVisibility(View.VISIBLE);
                    textViewWinnerAnnouncer.setVisibility(View.VISIBLE);

                }//end if
            }// end for
        }
    }

    public void playAgain(View view){
        TextView textViewWinnerAnnouncer = (TextView) findViewById(R.id.textViewWinner);
        Button buttonPlayAgain = (Button) findViewById(R.id.buttonPlayAgain);

        buttonPlayAgain.setVisibility(View.INVISIBLE);
        //textViewWinnerAnnouncer.setText("");
        textViewWinnerAnnouncer.setVisibility(View.INVISIBLE);

        GridLayout gridLayoutBoard = (GridLayout) findViewById(R.id.gridLayout);
        //loop through all objects in the layout
        for(int i=0;i<gridLayoutBoard.getChildCount();i++){
            ImageView counter = (ImageView) gridLayoutBoard.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i=0;i<gameState.length;i++){
                gameState[i]=-1;
        }// now gameState = {-1,-1,-1,-1,-1,-1,-1,-1,-1};

        activePlayer = 0;
        gameActive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}