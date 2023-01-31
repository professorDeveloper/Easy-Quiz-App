package com.azamovhudstc.quizapp.ui.page;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.azamovhudstc.quizapp.R;
import com.azamovhudstc.quizapp.ui.FinalResultActivity;
import com.azamovhudstc.quizapp.util.Constants;
import com.azamovhudstc.quizapp.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MathQuizActivity extends AppCompatActivity {
    private int currentQuestionIndex = 0;
    private TextView tvQuestion, tvQuestionNumber, text1, text2, text3, text4;
    private Button btnNext;
    private CardView radioButton1, radioButton2, radioButton3, radioButton4;
    private List<String> questions;
    int progressTime;
    private ProgressBar progressBar;
    private int correctQuestion = 0;
    String txt;
    private ImageView cardBg, backadabiy, cardBg2, cardBg3, cardBg4;
    private Map<String, Map<String, Boolean>> questionsAnswerMap;
    private ArrayList<String> getQuestionsAnswerMap;
    int time = 60000;
    static boolean isActive;
    CountDownTimer mCountDownTimer;
    private Chronometer chronometer;

    String subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_quiz);
        Intent intent = getIntent();
         subject = intent.getStringExtra(Constants.SUBJECT);
        subject = intent.getStringExtra(Constants.SUBJECT);
        chronometer = new Chronometer(this);

        if (subject.equals(getString(R.string.geography))) {
            questionsAnswerMap = Utils.getRandomLiteratureAndGeographyQuestions(this, getString(R.string.geography), Constants.QUESTION_SHOWING);
        }
        initView();

        variantClick1();
        variantClick2();
        variantClick3();
        variantClick4();
        progressBar.setMax(60);

        chronometer.start();
        Log.d("!@#", "onCreate: " + (System.currentTimeMillis() - chronometer.getBase()));


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


            }
        });


        displayData();


    }

    private void createCountDownTimer(int timeC) {
        final int[] times = {timeC};
        mCountDownTimer = new CountDownTimer(times[0], 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                times[0] -= 1000;
                time -= 1000;
                progressTime = (int) (millisUntilFinished / 1000);
                System.out.println("Progresss:" + progressTime);
                progressBar.setProgress(progressTime);

            }

            @Override
            public void onFinish() {
                //Do what you want
                times[0] = 0;
                times[0] = 0;
                Intent intentResult = new Intent(MathQuizActivity.this, FinalResultActivity.class);
                intentResult.putExtra(Constants.SUBJECT, subject);
                intentResult.putExtra(Constants.CORRECT, correctQuestion);
                intentResult.putExtra(Constants.TYPE, "math");
                intentResult.putExtra(Constants.INCORRECT, Constants.QUESTION_SHOWING - correctQuestion);
                intentResult.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentResult);
                finish();

                progressBar.setProgress(0);
            }
        }.start();

    }


    private void pauseTime() {
        mCountDownTimer.cancel();
        isActive = false;

    }


    private void variantClick1() {
        Intent intent = getIntent();
        String subject = intent.getStringExtra(Constants.SUBJECT);

        radioButton1.setOnClickListener(v -> {
                    click(v);
                    radioButton2.setEnabled(false);
                    radioButton3.setEnabled(false);
                    radioButton4.setEnabled(false);
                    cardBg.setImageResource(R.drawable.set_checked_to_variant);
                    cardBg2.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg3.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg4.setImageResource(R.drawable.set_un_checked_to_variant);
                    txt = text1.getText().toString();
                    new Handler().postDelayed(this::clickButton, 500);

                }
        );
    }

    private void variantClick2() {

        radioButton2.setOnClickListener(v -> {
                    click(v);
                    radioButton1.setEnabled(false);
                    radioButton3.setEnabled(false);
                    radioButton4.setEnabled(false);

                    cardBg2.setImageResource(R.drawable.set_checked_to_variant);
                    cardBg.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg3.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg4.setImageResource(R.drawable.set_un_checked_to_variant);
                    txt = text2.getText().toString();
                    new Handler().postDelayed(this::clickButton, 500);

                }
        );
    }


    private void variantClick3() {

        radioButton3.setOnClickListener(v -> {
                    click(v);
                    radioButton2.setEnabled(false);
                    radioButton4.setEnabled(false);
                    radioButton1.setEnabled(false);

                    cardBg3.setImageResource(R.drawable.set_checked_to_variant);
                    cardBg2.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg4.setImageResource(R.drawable.set_un_checked_to_variant);
                    txt = text3.getText().toString();
                    new Handler().postDelayed(this::clickButton, 500);

                }
        );
    }

    private void variantClick4() {
        radioButton4.setOnClickListener(v -> {
                    click(v);
            radioButton2.setEnabled(false);
            radioButton3.setEnabled(false);
            radioButton1.setEnabled(false);

            cardBg4.setImageResource(R.drawable.set_checked_to_variant);
                    cardBg2.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg3.setImageResource(R.drawable.set_un_checked_to_variant);
                    cardBg.setImageResource(R.drawable.set_un_checked_to_variant);
                    txt = text4.getText().toString();

                    new Handler().postDelayed(this::clickButton, 500);

                }
        );
    }

    private void variantClear() {
        txt = "";
        radioButton1.setEnabled(true);
        radioButton2.setEnabled(true);
        radioButton3.setEnabled(true);
        radioButton4.setEnabled(true);
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

    private void clickButton() {
        if (!txt.isEmpty()) {

            boolean answer = Boolean.TRUE.equals(questionsAnswerMap.get(questions.get(currentQuestionIndex)).get(txt));
            if (answer) {
                correctQuestion++;

                mCountDownTimer.cancel();
                createCountDownTimer(time += 3000);//                        994051755
            }
            currentQuestionIndex++;
            variantClear();
            if (btnNext.getText().equals(getString(R.string.next))) {
                displayNextQuestions();
            } else {
                Intent intentResult = new Intent(MathQuizActivity.this, FinalResultActivity.class);
                intentResult.putExtra(Constants.SUBJECT, subject);
                intentResult.putExtra(Constants.CORRECT, correctQuestion);
                intentResult.putExtra(Constants.TYPE, "math");
                intentResult.putExtra(Constants.INCORRECT, Constants.QUESTION_SHOWING - correctQuestion);
                intentResult.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentResult);
                finish();
            }
        }
    }

    private void click(View view) {
        view.setEnabled(false);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
            }
        }, 300);
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


    @Override
    protected void onDestroy() {
        super.onDestroy();

        mCountDownTimer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Time::::"+time);
        createCountDownTimer(time);
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Time::::"+time);

        mCountDownTimer.cancel();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void onBackPressed() {

        super.onBackPressed();
    }
}