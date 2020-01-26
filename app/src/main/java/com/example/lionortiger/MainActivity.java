package com.example.lionortiger;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
//
//    private ImageView img1;
//    private ImageView img2;
//    private ImageView img3;
//    private ImageView img4;
//    private ImageView img5;
//    private ImageView img6;
//    private ImageView img7;
//    private ImageView img8;
//    private ImageView img9;


    enum Player {

        ONE, TWO, No
    }


    Player currentPlayer = Player.ONE;

    Player[] playerChoices = new Player[9];

    int[][] winnerRowsColumn = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    private boolean gameOver = false;

    private Button btnReset;

    private GridLayout gridLayout;

    private int tappedNumberOfGrid;

    private int[] playerScores = new int[2];

    private TextView playerOneScore;
    private TextView playerTwoScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initPlayerChoices();

        btnReset = findViewById(R.id.btnReset);
        gridLayout = findViewById(R.id.gridLayout);

        btnReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                resetTheGame();
            }
        });

        tappedNumberOfGrid = 0;
        playerOneScore = findViewById(R.id.txtPlayerOneScore);
        playerTwoScore = findViewById(R.id.txtPlayerTwoScore);
    }

    public void imageViewIsTapped(View imageView){
        ImageView tappedImageView = (ImageView)imageView;
        int tiTag = Integer.parseInt(tappedImageView.getTag().toString());

        if(playerChoices[tiTag] == Player.No && gameOver == false) {
            tappedNumberOfGrid +=1;
            tappedImageView.setTranslationX(-2000);

            playerChoices[tiTag] = currentPlayer;
            //tappedImageView.setImageResource(R.drawable.tiger);
            tappedImageView.animate().translationXBy(2000).
                    alpha(1).rotation(3600).setDuration(1000);

//        Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();

            for (int[] winnerColumns : winnerRowsColumn) {
                if ((playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]] &&
                        playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]] && playerChoices[winnerColumns[0]] != Player.No)
                        || tappedNumberOfGrid == gridLayout.getChildCount()) {

                    btnReset.setVisibility(View.VISIBLE);
                    gameOver = true;
                    String winnerOfGame = "";

                    if (tappedNumberOfGrid == gridLayout.getChildCount()){
                        gameEndedByDraw();
                        break;
                    }

                    if (currentPlayer == Player.ONE) {
                        gameEndedByWinningThwGame(Player.ONE);
                    } else if (currentPlayer == Player.TWO) {
                        gameEndedByWinningThwGame(Player.TWO);
                    }

                }
            }
            //Set the image and the current Player

            if (currentPlayer == Player.ONE) {
                tappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = Player.TWO;
            } else if (currentPlayer == Player.TWO) {

                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.ONE;
            }

        }

    }

    //Winner
    private void gameEndedByWinningThwGame(Player player){
        if (player == Player.ONE){
            playerScores[0] += 1;
            playerOneScore.setText(playerScores[0]  + "");
        } else if (player == Player.TWO){
            playerScores[1] += 1;
            playerTwoScore.setText(playerScores[1] + "");
        }
        Toast.makeText(this, "Player " + player + " is the winner", Toast.LENGTH_LONG).show();
    }
    private void gameEndedByDraw(){
        Toast.makeText(this,  "The game is draw!", Toast.LENGTH_LONG).show();
    }

    //Reset Gmae Function
    private void resetTheGame(){

        for (int index = 0; index < gridLayout.getChildCount(); index++){

            ImageView imageView = (ImageView) gridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);
        }

        currentPlayer = Player.ONE;

        initPlayerChoices();

        gameOver = false;
        tappedNumberOfGrid = 0;
        btnReset.setVisibility(View.GONE);

    }

    private void initPlayerChoices(){
        for (int i = 0; i < playerChoices.length; i++)
            playerChoices[i] = Player.No;
    }


//    private void init(){
//        img1 = findViewById(R.id.img1);
//        img2 = findViewById(R.id.img2);
//        img3 = findViewById(R.id.img3);
//        img4 = findViewById(R.id.img4);
//        img5 = findViewById(R.id.img5);
//        img6 = findViewById(R.id.img6);
//        img7 = findViewById(R.id.img7);
//        img8 = findViewById(R.id.img8);
//        img9 = findViewById(R.id.img9);
//
//    }
}
