package com.example.a1200905_sondos_farrah;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView titleTextView, scoreTextView, timerTextView, questionTextView, feedbackTextView;
    private Button choice1Button, choice2Button, choice3Button, choice4Button;
    private CountDownTimer countDownTimer;
    public static int score ;
    public static int questionNumber;

    private String[][] questions = {
            {"1: What is the capital of Palestine?", "Ramallah", "Jerusalem", "Hebron", "Jenin"},
            {"2: Where is the Church of the Nativity located?", "Jerusalem", "Gaza", "Bethlehem", "Ramallah"},
            {"3: What is the city of Hebron famous for?", "Grapes", "Oranges", "Strawberry", "Figs"},
            {"4: How many colors does the flag of Palestine consist of?", "4 Colors", "3 Colors", "5 Colors", "6 Colors"},
            {"5: When did Tofan Al-Aqsa War begin?", "7th August", "7th january", "7th October", "7th june"}
    };
    private String [] answers = {"Jerusalem","Bethlehem","Grapes","4 Colors","7th October"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score = 0;
        questionNumber = 1;
        titleTextView = findViewById(R.id.titleTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        questionTextView = findViewById(R.id.questionTextView);
        feedbackTextView = findViewById(R.id.feedbackTextView);
        choice1Button = findViewById(R.id.choice1Button);
        choice2Button = findViewById(R.id.choice2Button);
        choice3Button = findViewById(R.id.choice3Button);
        choice4Button = findViewById(R.id.choice4Button);

        updateQuestion();

        // Start countdown timer
        startTimer(10);

        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(choice1Button.getText().toString());
            }
        });

        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(choice2Button.getText().toString());
            }
        });

        choice3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(choice3Button.getText().toString());
            }
        });

        choice4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(choice4Button.getText().toString());
            }
        });
    }

    private void updateQuestion() {
        if (questionNumber <= questions.length) {
            String[] currentQuestion = questions[questionNumber - 1];
            questionTextView.setText(currentQuestion[0]);
            choice1Button.setText(currentQuestion[1]);
            choice2Button.setText(currentQuestion[2]);
            choice3Button.setText(currentQuestion[3]);
            choice4Button.setText(currentQuestion[4]);
        } else {
            goToResultActivity();
        }
    }

    private void startTimer(int seconds) {
        countDownTimer = new CountDownTimer(seconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                goToNextQuestion();
            }
        };
        countDownTimer.start();
    }

    private void checkAnswer(String selectedAnswer) {
        String correctAnswer = answers[questionNumber - 1];
        if (selectedAnswer.equals(correctAnswer)) {
            score++;
            scoreTextView.setText(String.valueOf(score));
        }
        goToNextQuestion();
    }

    private void goToNextQuestion() {
        countDownTimer.cancel();
        questionNumber++;
        updateQuestion();
        startTimer(10);
    }

    private void goToResultActivity() {
        questionNumber=1;
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("score", String.valueOf(score));
        startActivity(intent);
        finish();
    }
}
