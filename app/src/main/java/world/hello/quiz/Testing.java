package world.hello.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Testing extends AppCompatActivity {

    private ArrayList<Question> questions;
    private Integer questionNumber = 0;
    private Integer numberOfQuestions = 0;

    private Integer numberOfCorrectAnswers = 0;
    private Integer numberOfIncorrectAnswers = 0;

    private Boolean isNeedToShowCorrectAnswer = true;

    private Button btn_choice1;
    private Button btn_choice2;
    private Button btn_choice3;
    private Button btn_choice4;
    private Button btn_closeTesting;
    private TextView tw_questionCounter;
    private TextView label_layout_header;
    private TextView tw_PressAnyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        View rootView = getWindow().getDecorView().getRootView();
        this.btn_choice1 = rootView.findViewById(R.id.btn_choiсe1);
        this.btn_choice2 = rootView.findViewById(R.id.btn_choiсe2);
        this.btn_choice3 = rootView.findViewById(R.id.btn_choiсe3);
        this.btn_choice4 = rootView.findViewById(R.id.btn_choiсe4);
        this.btn_closeTesting = rootView.findViewById(R.id.btn_closeTesting);
        this.tw_questionCounter = rootView.findViewById(R.id.questionCounter);
        this.label_layout_header = rootView.findViewById(R.id.label_layout_header);
        this.tw_PressAnyButton = rootView.findViewById(R.id.tw_PressAnyButton);

        DBController dbController = new DBController(getApplicationContext());
        this.questions = (ArrayList<Question>) dbController.getQuestions();
        this.numberOfQuestions = this.questions.size();
        doGenerateNextQuestion();
    }

    public void onChooseAnswer(View view){
        if(isNeedToShowCorrectAnswer) {
            isNeedToShowCorrectAnswer = false;
            this.tw_PressAnyButton.setVisibility(View.VISIBLE);
            Button clickedBtn = view.findViewById(view.getId());
            Button correctBtn = getButton(questions.get(this.questionNumber-1).correctAnswer);
            clickedBtn.setBackgroundColor(Color.parseColor("#FFD400"));
            if (clickedBtn.equals(correctBtn)) {
                this.numberOfCorrectAnswers++;
                clickedBtn.setBackgroundColor(Color.parseColor("#4EEF08"));
            } else {
                this.numberOfIncorrectAnswers++;
                correctBtn.setBackgroundColor(Color.parseColor("#4EEF08"));
            }
        }
        else{
            isNeedToShowCorrectAnswer = true;
            this.tw_PressAnyButton.setVisibility(View.INVISIBLE);
            doGenerateNextQuestion();
            doResetBtnColors();
        }

    }

    private void doGenerateNextQuestion(){
        if(this.questionNumber >= this.numberOfQuestions) {
            doGenerateResultFragment();
        }
        else {
            this.questionNumber++;
            this.doUpdateQuestionCounter();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            Question curQuest = this.questions.get(this.questionNumber - 1);
            Fragment f = CurrentQuestion.newInstance(
                    curQuest.question,
                    curQuest.answer1,
                    curQuest.answer2,
                    curQuest.answer3,
                    curQuest.answer4
            );
            ft.replace(R.id.fragment, f);
            ft.commit();
        }
    }

    private String getQuestionNumberLabel(){
        return questionNumber + "/" + numberOfQuestions;
    }

    private Button getButton(int buttonNumber){
        Button index = null;
        switch (buttonNumber) {
            case 1:
                index = this.btn_choice1;
                break;
            case 2:
                index = this.btn_choice2;
                break;
            case 3:
                index = this.btn_choice3;
                break;
            case 4:
                index = this.btn_choice4;
                break;
        }
        return index;
    }

    private void doResetBtnColors(){
        btn_choice1.setBackgroundColor(Color.parseColor("#FF3700B3"));
        btn_choice2.setBackgroundColor(Color.parseColor("#FF3700B3"));
        btn_choice3.setBackgroundColor(Color.parseColor("#FF3700B3"));
        btn_choice4.setBackgroundColor(Color.parseColor("#FF3700B3"));
    }

    private void doUpdateQuestionCounter(){
        this.tw_questionCounter.setText(this.getQuestionNumberLabel());
    }

    private void doGenerateResultFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment f = TestingResultFragment.newInstance(
                this.numberOfCorrectAnswers + "/" + this.numberOfQuestions
        );
        ft.replace(R.id.fragment, f);
        ft.commit();
        btn_choice1.setVisibility(View.INVISIBLE);
        btn_choice2.setVisibility(View.INVISIBLE);
        btn_choice3.setVisibility(View.INVISIBLE);
        btn_choice4.setVisibility(View.INVISIBLE);
        btn_closeTesting.setVisibility(View.VISIBLE);
        label_layout_header.setText(R.string.ACTIVITY_LABEL_TESTING_RESULT);

    }

    public void doCloseTesting(View view){
        finish();
    }
}