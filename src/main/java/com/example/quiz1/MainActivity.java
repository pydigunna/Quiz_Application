package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView TotalQue,Question;
    Button ansA,ansB,ansC,ansD,submit;
    int score=0;
    int totalQuestion = QueAns.questions.length;
    int currentQuestionIndex =0;
    String selectedAnswer ="";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TotalQue = findViewById(R.id.no_que);
        Question = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submit = findViewById(R.id.sub_btn);
        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submit.setOnClickListener(this);
        TotalQue.setText("total questions"+totalQuestion);
        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);
        Button clickedButton =(Button) view;
        if(clickedButton.getId()==R.id.sub_btn){
            if(selectedAnswer.equals(QueAns.correctAns[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();
        }
        else{
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.CYAN);
        }

    }
    void loadNewQuestion(){
        if(currentQuestionIndex==totalQuestion){
            finishQuiz();
            return ;
        }
        Question.setText(QueAns.questions[currentQuestionIndex]);
        ansA.setText(QueAns.choices[currentQuestionIndex][0]);
        ansB.setText(QueAns.choices[currentQuestionIndex][1]);
        ansC.setText(QueAns.choices[currentQuestionIndex][2]);
        ansD.setText(QueAns.choices[currentQuestionIndex][3]);
    }
    void finishQuiz(){
        String passStatus ="";
        if(score>totalQuestion*0.5){
            passStatus ="Passed";
        }
        else{
            passStatus ="Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is"+score+"out of"+totalQuestion)
                .setPositiveButton("Restart",((dialogInterface, i) -> restartQuiz()))
                .setCancelable(false)
                .show();
    }
    void restartQuiz(){
        score=0;
        currentQuestionIndex=0;
        loadNewQuestion();
    }
}