package com.example.lionortiger;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private ImageView img6;
    private ImageView img7;
    private ImageView img8;
    private ImageView img9;


    enum Player {

        ONE, TWO
    }

    Player currentPlayer = Player.ONE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void imageViewIsTapped(View imageView){
        ImageView tappedImageView = (ImageView)imageView;

        tappedImageView.setTranslationX(-2000);

        if (currentPlayer == Player.ONE){
            tappedImageView.setImageResource(R.drawable.lion);
            currentPlayer = Player.TWO;
        } else if(currentPlayer == Player.TWO){

            tappedImageView.setImageResource(R.drawable.tiger);
            currentPlayer = Player.ONE;
        }

        //tappedImageView.setImageResource(R.drawable.tiger);
        tappedImageView.animate().translationXBy(2000).
                alpha(1).rotation(3600).setDuration(1000);

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
