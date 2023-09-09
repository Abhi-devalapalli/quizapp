package com.abhi.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.R.color;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA,ansB,ansC,ansD;
    Button submitBtn;

    int score=0;
    int totalQuestions=QuestionAnswer.question.length;
    int currentQuestionIndex=0;
    String selectedAnswer="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView=findViewById(R.id.total_questions);
        questionTextView=findViewById(R.id.question);
        ansA=findViewById(R.id.ans_A);
        ansB=findViewById(R.id.ans_B);
        ansC=findViewById(R.id.ans_C);
        ansD=findViewById(R.id.ans_D);
        submitBtn=findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions=:"+totalQuestions);

        loadNweQuestion();


    }

    @Override
    public void onClick(View v) {

        ansA.setBackgroundColor(android.R.color.white);
        ansB.setBackgroundColor(android.R.color.white);
        ansC.setBackgroundColor(android.R.color.white);
        ansD.setBackgroundColor(android.R.color.white);

        Button clickedButton=(Button) v;
        if(clickedButton.getId()==R.id.submit_btn){

            if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNweQuestion();
        }
        else {
            selectedAnswer=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }


    void loadNweQuestion(){

        if(currentQuestionIndex==totalQuestions){
            finishQuiz();
            return;
        }
        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

    }

     void finishQuiz() {
        String passstatus="";
        if(score > totalQuestions*0.60){
            passstatus="passed";

        }
        else {
            passstatus="failed";

        }
         new AlertDialog.Builder(this)
                 .setTitle(passstatus)
                 .setMessage("score is"+score+"out of"+totalQuestions)
                 .setPositiveButton("Restart",(dialog, which) -> restartQuiz())
                 .setCancelable(false)
                 .show();
     }
void restartQuiz(){
        score=0;
        currentQuestionIndex=0;
        loadNweQuestion();
}

}