package com.example.quizapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button tipButton;
    private TextView questionTextView;
    int currentQuestionIndex = 0;
    int points;
    private static final String KEY_CURRENT_INDEX = "currentQuestionIndex";
    public static final String KEY_EXTRA_ANSWER = "com.modzel.quiz.correctAnswer";
    private static final int REQUEST_CODE_PROMPT = 0;
    private boolean answerWasShown;
    private static final String QUIZ_TAG = "mainactivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(QUIZ_TAG,"Wywołanie onCreate");
        setContentView(R.layout.activity_main);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        tipButton = findViewById(R.id.tip_button);
        questionTextView = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);

        showCurrentQuestion();
        if (savedInstanceState != null) {
            currentQuestionIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuestionIndex = (currentQuestionIndex + 1) % questions.length;
                showCurrentQuestion();
                answerWasShown = false;
            }
        });

        tipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PromptActivity.class);
                boolean correctAnswer = questions[currentQuestionIndex].isTrue();
                intent.putExtra(KEY_EXTRA_ANSWER, correctAnswer);
                startActivityForResult(intent, REQUEST_CODE_PROMPT);
            }
        });




    }

    private Question[] questions = new Question[] {
            new Question(R.string.q_wozek, true),
            new Question(R.string.q_miska, true),
            new Question(R.string.q_ryszard, false),
            new Question(R.string.q_boiler, true),
            new Question(R.string.q_ryzowar, false)
    };

    private void checkAnswerCorrectness(boolean userAnswer) {
        boolean correctAnswer = questions[currentQuestionIndex].isTrue();
        if(currentQuestionIndex == 0)
            points = 0;
        String message;
            if(answerWasShown) {
                message = getResources().getString(R.string.cheat);
            }
            else if (userAnswer == correctAnswer) {
                message = getResources().getString(R.string.correct_answer);
                points++;
            }
            else
                message = getResources().getString(R.string.incorrect_answer);
            if(currentQuestionIndex==4)
                message += " Ilość zdobytych punktów " + Integer.toString(points);
            Toast.makeText(this, message , Toast.LENGTH_SHORT).show();

        }

    private void showCurrentQuestion() {
        questionTextView.setText(questions[currentQuestionIndex].getId());
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(QUIZ_TAG, "Wywołanie onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(QUIZ_TAG, "Wywołanie onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(QUIZ_TAG, "Wywołanie onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(QUIZ_TAG, "Wywołanie onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(QUIZ_TAG, "Wywołanie onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(QUIZ_TAG, "Wywołanie onDestroy");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(QUIZ_TAG, "Wywołanie onSaveInstanceState");
        outState.putInt(KEY_CURRENT_INDEX, currentQuestionIndex);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK)
            return;
        if (requestCode == REQUEST_CODE_PROMPT) {
            if (data == null)
                return;
            answerWasShown = data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER_SHOWN, false);
        }
    }

}