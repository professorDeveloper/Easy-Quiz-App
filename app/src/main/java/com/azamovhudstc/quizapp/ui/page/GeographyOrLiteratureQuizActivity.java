package com.azamovhudstc.quizapp.ui.page;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.azamovhudstc.quizapp.R;
import com.azamovhudstc.quizapp.model.QuizData;
import com.azamovhudstc.quizapp.ui.FinalResultActivity;
import com.azamovhudstc.quizapp.util.Constants;
import com.azamovhudstc.quizapp.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeographyOrLiteratureQuizActivity extends AppCompatActivity {
    private int currentQuestionIndex = 0;
    private TextView tvQuestion, tvQuestionNumber, text1, text2, text3, text4;
    private Button btnNext;
    private CardView radioButton1, radioButton2, radioButton3, radioButton4;
    private List<String> questions;
    private ProgressBar progressBar;
    private int correctQuestion = 0;
    String txt;
    private ImageView cardBg, backadabiy, cardBg2, cardBg3, cardBg4;
    private Map<String, Map<String, Boolean>> questionsAnswerMap;
    private ArrayList<String> getQuestionsAnswerMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_graph_and_liture);
        Intent intent = getIntent();
        String subject = intent.getStringExtra(Constants.SUBJECT);

        if (subject.equals(getString(R.string.literature))) {
            questionsAnswerMap = Utils.getRandomLiteratureAndGeographyQuestions(this, getString(R.string.literature), Constants.QUESTION_SHOWING);
        }
        initView();
        variantClick1();
        variantClick2();
        variantClick3();
        variantClick4();

        backadabiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.btnNextQuestionLiteratureAndGeography).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {

                if (!txt.isEmpty()) {

                    boolean answer = questionsAnswerMap.get(questions.get(currentQuestionIndex)).get(txt);
                    if (answer) {
                        correctQuestion++;
                    }
                    currentQuestionIndex++;
                    variantClear();
                    if (btnNext.getText().equals(getString(R.string.next))) {
                        displayNextQuestions();
                    } else {
                        Intent intentResult = new Intent(GeographyOrLiteratureQuizActivity.this, FinalResultActivity.class);
                        intentResult.putExtra(Constants.SUBJECT, subject);
                        intentResult.putExtra(Constants.CORRECT, correctQuestion);
                        intentResult.putExtra(Constants.TYPE, "math");
                        intentResult.putExtra(Constants.INCORRECT, Constants.QUESTION_SHOWING - correctQuestion);
                        intentResult.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intentResult);
                    }
                } else {
                    Toast.makeText(GeographyOrLiteratureQuizActivity.this, "Iltimos biror Variantni tanlang !", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });


        displayData();


    }

    private void variantClick1() {
        radioButton1.setOnClickListener(v -> {
                    cardBg.setImageResource(R.drawable.set_checked_to_variant);
                    cardBg2.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg3.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg4.setImageResource(R.drawable.set_un_checked_to_variant);
                    txt = text1.getText().toString();
                }
        );
    }

    private void variantClick2() {
        radioButton2.setOnClickListener(v -> {
                    cardBg2.setImageResource(R.drawable.set_checked_to_variant);
                    cardBg.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg3.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg4.setImageResource(R.drawable.set_un_checked_to_variant);
                    txt = text2.getText().toString();

                }
        );
    }

    private void variantClick3() {
        radioButton3.setOnClickListener(v -> {
                    cardBg3.setImageResource(R.drawable.set_checked_to_variant);
                    cardBg2.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg4.setImageResource(R.drawable.set_un_checked_to_variant);
                    txt = text3.getText().toString();

                }
        );
    }

    private void variantClick4() {
        radioButton4.setOnClickListener(v -> {
                    cardBg4.setImageResource(R.drawable.set_checked_to_variant);
                    cardBg2.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg3.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg.setImageResource(R.drawable.set_un_checked_to_variant);
                    txt = text4.getText().toString();

                }
        );
    }

    private void variantClear() {
        txt = "";
        cardBg.setImageResource(R.drawable.set_un_checked_to_variant);
        cardBg2.setImageResource(R.drawable.set_un_checked_to_variant);
        cardBg3.setImageResource(R.drawable.set_un_checked_to_variant);
        cardBg4.setImageResource(R.drawable.set_un_checked_to_variant);
    }

    @SuppressLint("SetTextI18n")
    private void displayNextQuestions() {
        setAnswersToRadioButton();
        tvQuestion.setText(questions.get(currentQuestionIndex));
        tvQuestionNumber.setText("" + (currentQuestionIndex + 1));
        progressBar.setProgress(currentQuestionIndex * 10);
        progressBar.setMax(100);


        if (currentQuestionIndex == Constants.QUESTION_SHOWING - 1) {
            btnNext.setText(getText(R.string.finish));
        }
    }

    @SuppressLint("SetTextI18n")
    private void displayData() {
        tvQuestion.setText(questions.get(currentQuestionIndex));
        tvQuestionNumber.setText("" + (currentQuestionIndex + 1));

        setAnswersToRadioButton();
    }

    private void setAnswersToRadioButton() {

        ArrayList<String> questionKey = new ArrayList(questionsAnswerMap.get(questions.get(currentQuestionIndex)).keySet());

        text1.setText(questionKey.get(0));
        text2.setText(questionKey.get(1));
        text3.setText(questionKey.get(2));
        text4.setText(questionKey.get(3));

    }


    public void initView() {
        questions = new ArrayList<>(questionsAnswerMap.keySet());
        txt = "";

        tvQuestion = findViewById(R.id.textView78);
        tvQuestionNumber = findViewById(R.id.textView18);
        btnNext = findViewById(R.id.btnNextQuestionLiteratureAndGeography);
        text1 = findViewById(R.id.radioButton1Text);
        text2 = findViewById(R.id.radioButto21Text);
        text3 = findViewById(R.id.radioButto3Text);
        text4 = findViewById(R.id.radioButton4Text);
        backadabiy = findViewById(R.id.backadabiy);
        cardBg = findViewById(R.id.setChecked);
        cardBg2 = findViewById(R.id.setChecked2);
        cardBg3 = findViewById(R.id.setChecked3);
        cardBg4 = findViewById(R.id.setChecked4);
        progressBar = findViewById(R.id.progressadabiy);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);
    }
}