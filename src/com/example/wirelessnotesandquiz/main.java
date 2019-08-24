package com.example.wirelessnotesandquiz;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class main extends Activity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";
	protected static final int REQUEST_CODE_QUIZ = 1;
	
 
    private TextView textViewHighscore;
 
    private int highscore;
	
	
	
	Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        textViewHighscore = (TextView) findViewById(R.id.text_view_highscore);
        loadHighscore();
        
        b=(Button)findViewById(R.id.start);
        
        b.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
				startQuiz();
				
			}
        	 
        	
        	
        });
    }
        
       private void startQuiz(){
    	   
    	   Intent intent =new Intent (main.this,QuizActivity.class);
	    	startActivityForResult(intent,REQUEST_CODE_QUIZ);
			
       }
        
     
        
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
     
            if (requestCode == REQUEST_CODE_QUIZ) {
                if (resultCode == RESULT_OK) {
                    int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
                    if (score > highscore) {
                        updateHighscore(score);
                    }
                }
            }
        }
     
        private void loadHighscore() {
            SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            highscore = prefs.getInt(KEY_HIGHSCORE, 0);
            textViewHighscore.setText("Highscore: " + highscore);
        }
     
        @SuppressLint("NewApi") private void updateHighscore(int highscoreNew) {
            highscore = highscoreNew;
            textViewHighscore.setText("Highscore: " + highscore);
     
            SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(KEY_HIGHSCORE, highscore);
            editor.apply();
        }
    
        
    
    



    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
