package com.example.myfirstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    //0-0
    //1-X

    int activePlayer=1;
    boolean gameactive=true;
    int []gameState={2,2,2,2,2,2,2,2,2};  //0-0;;;1-X;;;2--NULL
    int [][]winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
 public void playertap(View view)
    {
        ImageView img=(ImageView)view;
    int tappedImage=Integer.parseInt(img.getTag().toString());
    if(gameactive!=true)
    {
        gameReset(view);  //already imported in header
    }
    else if(gameState[tappedImage]==2 && gameactive)
    {  gameState[tappedImage]=activePlayer;
       img.setTranslationY(1000f);  //a transition effect is given at the entry
        if(activePlayer==1)
        {
            img.setImageResource(R.drawable.cross);
            img.animate().translationYBy(-1100f).setDuration(100);
            activePlayer=0;
            TextView status=(TextView)findViewById(R.id.status);
            img.setColorFilter(ContextCompat.getColor(this, R.color.white));
            status.setText("O's_turn-tap to play");
        }
        else   //zero part
        {
            img.setImageResource(R.drawable.zero);
            img.animate().translationYBy(-1100f).setDuration(100);
            activePlayer=1;
            img.setColorFilter(ContextCompat.getColor(this, R.color.red_500));
            TextView status=(TextView)findViewById(R.id.status);
            status.setText("X's_turn-tap to play");
        }


    //check if any player has won;;;
        for(int []winningPos:winningPositions)
        {
            if(gameState[winningPos[0]]==gameState[winningPos[1]] && (gameState[winningPos[1]]==gameState[winningPos[2]])
                    && gameState[winningPos[0]]!=2)

            {
                //someone has won...find out who
                gameactive=false;//falsing out when someone has won
                String winnerStr;
                if(gameState[winningPos[0]]==0)
                {
                    winnerStr="O";
                    Toast.makeText(this, "O has won", Toast.LENGTH_SHORT).show();
                }
                else {
                    winnerStr = "X";
                    Toast.makeText(this, "X has won", Toast.LENGTH_SHORT).show();
                }
                TextView winner=(TextView)findViewById(R.id.win_status);
                winner.setVisibility(View.VISIBLE);
                winner.setText(winnerStr+" has won");  //displaying the winner
                /*telling the user when the game is over that tap the tic tac toe button to restart*/
                TextView status =( TextView)findViewById(R.id.status);
                status.setText(" tap the TIC TAC TOE BUTTON to restart");
            }
        }

    }
    else  //if user taps at the position which is already occupied
    {
        TextView status=(TextView)findViewById(R.id.status);
        status.setText("HEY man Do not cheat!!");
    }
    int count=0;
    for (int i=0;i<gameState.length;i++)
    {
        if(gameState[i]!=2)
            count++;
    }
    if(count==gameState.length)
    {
        TextView status=(TextView)findViewById(R.id.status);
        status.setText(" TIE , Tap the TIC TAC TOE BUTTON to restart");
        gameactive=false;   //game over

    }



    }

    public void restart(View view)
    {
        gameReset(view);
    }



    public void gameReset(View view)
    {
        gameactive=true;
        activePlayer=1;
        TextView winner=(TextView)findViewById(R.id.win_status);
        winner.setVisibility(View.INVISIBLE);
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        //setting all images to null
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);


        //starting of new game that's why we now told that X's turn to play just like for first time run
        TextView status=(TextView)findViewById(R.id.status);
        status.setText("X's_turn-tap to play");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


    }

}