package com.leotarius.quizzz;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mtrue , mfalse;
    private TextView content, Score;
    private int index, score;
    private String scoreString;
    private boolean userAnswer;
    private ProgressBar bar;
    private TrueFalse[] question_array = new TrueFalse[]{
            new TrueFalse(R.string.question_1,true),
            new TrueFalse(R.string.question_2,true),
            new TrueFalse(R.string.question_3,true),
            new TrueFalse(R.string.question_4,true),
            new TrueFalse(R.string.question_5,true),
            new TrueFalse(R.string.question_6,false),
            new TrueFalse(R.string.question_7,true),
            new TrueFalse(R.string.question_8,false),
            new TrueFalse(R.string.question_9,true),
            new TrueFalse(R.string.question_10,true),
            new TrueFalse(R.string.question_11,false),
            new TrueFalse(R.string.question_12,false),
            new TrueFalse(R.string.question_13,true)
    };

    private int PROGRESS_UPDATE_CONSTANT = (int) Math.ceil(100f/13f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            score = savedInstanceState.getInt("scoreValue");
            index = savedInstanceState.getInt("indexValue");
        }else {
            score = 0;
            index = 0;
        }

        mtrue = findViewById(R.id.trueButton);
        mfalse = findViewById(R.id.falseButton);
        content = findViewById(R.id.question);
        content.setText(question_array[0].getQuestionID());
        Score = findViewById(R.id.score);
        bar = findViewById(R.id.progressBar);

        // Setting question and score below to show score after screen rotation
        scoreString = "Score : " + score + "/13";
        Score.setText(scoreString);
        content.setText(question_array[index].getQuestionID());



    mtrue.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            userAnswer = true;
            int isCorrect = checkAnswer(userAnswer);
            if(isCorrect == 1) { score++; }
            scoreString = "Score : " + score + "/13";
            Score.setText(scoreString);
            updateDetails();
            updateProgress();
        }
    });

    mfalse.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            userAnswer = false;
            int isCorrect = checkAnswer(userAnswer);
            if(isCorrect == 1) { score++; }
            scoreString = "Score : " + score + "/13";
            Score.setText(scoreString);
            updateDetails();
            updateProgress();
        }
    });

    }

    private void updateDetails(){
        index = ++index % 13;
       if(index == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("GAME OVER!!!");
            alert.setCancelable(false);
            alert.setMessage("You scored "+ score +" points ");
            alert.setPositiveButton("Close application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                finish();
                }
            });
            alert.show();
        }
        content.setText(question_array[index].getQuestionID());
    }

    private int checkAnswer(boolean userAnswer){
        if(userAnswer == question_array[index].isAnswerID()){
            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
            return 1;
        } else{
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
            return 0;
        }
    }

    private void updateProgress(){
        bar.incrementProgressBy(PROGRESS_UPDATE_CONSTANT);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("scoreValue", score);
        outState.putInt("indexValue", index);
    }

}
