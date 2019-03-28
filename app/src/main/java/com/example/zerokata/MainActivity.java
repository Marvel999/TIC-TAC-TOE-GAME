package com.example.zerokata;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    MediaPlayer mPlayer;
    MediaPlayer nPlayer;
    MediaPlayer dPlayer;
    CardView layout;

    public void playAudio(View view){
        mPlayer.prepareAsync();
        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

    }


    public void puseAudio(View view){

        mPlayer.stop();
    }

    // 0=blue,1=red
    int activePlayer=0;

    boolean gameIsActive=true;

    //2.means unpalyed
    int[] gameState={2,2,2,2,2,2,2,2,2};

    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{1,4,7},{2,5,8},{0,4,8},{2,4,6},{0,3,6}};




    public void dropIn(View view){


        ImageView counter=(ImageView) view;


        System.out.println(counter.getTag().toString());

        int tappedCounter= Integer.parseInt(counter.getTag().toString());




        if(gameState[tappedCounter]==2 && gameIsActive) {


            gameState[tappedCounter] = activePlayer;




        counter.setTranslationY(-1000f);





           if (activePlayer == 0) {
               counter.setImageResource(R.drawable.blue);
               activePlayer = 1;
           }
           else {

               counter.setImageResource(R.drawable.red);
               activePlayer = 0;
           }
           counter.animate().translationYBy(1000f).setDuration(100);
           for(int[] winningPosition: winningPositions) {

               if(gameState[winningPosition[0]]==gameState[winningPosition[1]]&&
                       gameState[winningPosition[1]]==gameState[winningPosition[2]]&&
                       gameState[winningPosition[0]]!=2){

                   gameIsActive=false;


                   System.out.println(gameState[winningPosition[0]]);


                   String winner="Red";

                   if(gameState[winningPosition[0]]==0) {
                       winner = "Blue";

                   }
                       TextView winnerMassage=(TextView) findViewById(R.id.massage);

                       winnerMassage.setText(winner + " has won.");

                       CardView layout=(CardView) findViewById(R.id.card_view);

                       layout.setVisibility(View.VISIBLE);
                   mPlayer.pause();

                   nPlayer.prepareAsync();
                   nPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                       @Override
                       public void onPrepared(MediaPlayer mp) {

                           mp.start();

                       }

               });


                   nPlayer.start();

               }

               else {

                   boolean gameIsOver=true;

                   for(int stateCounter:gameState){

                       if(stateCounter==2) gameIsOver=false;
                   }

                   if(gameIsOver) {

                       TextView winnerMassage = (TextView) findViewById(R.id.massage);

                       winnerMassage.setText("it's a draw.");

                       if (nPlayer.isPlaying() && nPlayer != null)
                           nPlayer.pause();





                       dPlayer.start();
                       mPlayer.pause();

                       CardView layout = (CardView) findViewById(R.id.card_view);

                       layout.setVisibility(View.VISIBLE);





                   }


               }


           }




       }

    }

    public void play(View view){

        if (nPlayer.isPlaying() && nPlayer != null) {
            nPlayer.pause();
        }

        if (dPlayer != null && dPlayer.isPlaying()) {

            dPlayer.pause();

        }

        gameIsActive=true;



        layout.setVisibility(View.INVISIBLE);

        activePlayer=0;

        for (int i=0;i<9;i++)
        {

            gameState[i]=2;


        }

        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);
        for (int i=0;i<gridLayout.getChildCount();i++)
        {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }


        mPlayer.start();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayer= MediaPlayer.create(this,R.raw.music);
        layout=(CardView) findViewById(R.id.card_view);
        nPlayer= MediaPlayer.create(this,R.raw.win);
        dPlayer= MediaPlayer.create(this,R.raw.drow);
        mPlayer.start();


    }
    @Override
    protected void onPause() {


        super.onPause();
        mPlayer.pause();

    }

}
