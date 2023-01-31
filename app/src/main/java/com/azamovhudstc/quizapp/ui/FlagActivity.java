package com.azamovhudstc.quizapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.azamovhudstc.quizapp.R;
import com.azamovhudstc.quizapp.contract.MainContract;
import com.azamovhudstc.quizapp.controller.AppController;
import com.azamovhudstc.quizapp.local_data.QuizPref;
import com.azamovhudstc.quizapp.local_data.source.Base;
import com.azamovhudstc.quizapp.model.QuestionModel;
import com.azamovhudstc.quizapp.util.Constants;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

import java.util.ArrayList;
import java.util.List;

public class FlagActivity extends AppCompatActivity implements MainContract.MainView {
    private final int MAX_ANSWER_COUNT = 8;
    private ProgressBar progressBar;
    private final int MAX_VARIANT = 12;
    private final List<Button> variantButtons = new ArrayList<>(MAX_VARIANT);
    private final List<Button> answerButtons = new ArrayList<>(MAX_ANSWER_COUNT);
    private TextView level_txt, coin_txt;
    private AppController appController = new AppController();
    private int level = 0;

    private ImageView back, txtForQuiz;
    private int heartCount = 0;
    private int coin = 0;
    private int unCorrect = 0;
    private Base localStorage;
    private List<String> typedAnswer;

    private final List<Boolean> typedVariants = new ArrayList<>(MAX_VARIANT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag_quiz);
        appController.shuffle();
        localStorage = Base.getInstance();
        initView();
        appController.loadQuestion();
        loadQuestion();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }


    private List<Button> loadButtonsByGroup(int position, int layoutId, View.OnClickListener clickListener) {
        LinearLayout container = findViewById(layoutId);


        List<Button> buttons = new ArrayList<>(container.getChildCount());
        for (int i = 0; i < container.getChildCount(); i++) {
            Button button = (Button) container.getChildAt(i);
            button.setTag(position + i);
            button.setOnClickListener(clickListener);
            buttons.add(button);
        }

        return buttons;
    }

    private void initView() {
        heartCount =
                Log.d("TTTTT", "onClick:" + heartCount);

        progressBar = findViewById(R.id.progress);

        back = findViewById(R.id.backFlag);
        level_txt = findViewById(R.id.level_txt);
        txtForQuiz = findViewById(R.id.txtquestion);
        variantButtons.addAll(loadButtonsByGroup(0, R.id.containerVariantFirst, this::clickVariant));
        variantButtons.addAll(loadButtonsByGroup(variantButtons.size(), R.id.containerVariantSecond, this::clickVariant));
        answerButtons.addAll(loadButtonsByGroup(0, R.id.containerAnswer, this::clickAnswer));

    }

    private void loadQuestion() {
        if (appController.getQuestionCount() > level) {
            progressBar.setProgress(level * 10);
            progressBar.setMax(120);
            QuestionModel question = appController.getQuestion(level);
            txtForQuiz.setImageResource(question.getTxtQuestion());
            level_txt.setText(String.valueOf(level + 1));
            String answer = question.getAnswer();
            typedAnswer = new ArrayList<>(answer.length());

            for (int i = 0; i < MAX_ANSWER_COUNT; i++) {
                boolean state = answer.length() > i;
                Button buttonAnswer = answerButtons.get(i);
                if (state) {
                    typedAnswer.add(null);
                    buttonAnswer.setText("");
                    buttonAnswer.setVisibility(View.VISIBLE);
                    buttonAnswer.setBackgroundResource(R.drawable.bg_answer);
                } else {
                    buttonAnswer.setVisibility(View.GONE);
                }
            }

            String variant = question.getVariant();


            for (int i = 0; i < MAX_VARIANT; i++) {
                Button buttonVariant = variantButtons.get(i);
                typedVariants.add(true);
                buttonVariant.setVisibility(View.VISIBLE);
                String letter = String.valueOf(variant.charAt(i));
                buttonVariant.setText(letter);
            }

            localStorage.setSaveCoin(coin);
            localStorage.setSaveHeart(heartCount);
            ;
        }
    }

    private void clickVariant(View view) {
        int positionAnswer = typedAnswer.indexOf(null);
        if (positionAnswer < 0) return;
        Button buttonVariant = (Button) view;
        int position = (int) buttonVariant.getTag();

        Log.d("TTT", "Position Clicked " + position);
        QuestionModel question = appController.getQuestion(level);
        String variant = question.getVariant();
        String letter = String.valueOf(variant.charAt(position));
        Button buttonAnswer = answerButtons.get(positionAnswer);
        buttonAnswer.setText(letter);
        buttonAnswer.setBackgroundResource(R.drawable.bg_variant);

        typedAnswer.set(positionAnswer, letter);
        buttonVariant.setVisibility(View.INVISIBLE);
        typedVariants.set(position, false);
        try {
            checkWinner();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        heartCount = localStorage.getSaveHeart();

    }

    private void clickAnswer(View view) {

        Button buttonAnswer = (Button) view;
        int position = (int) buttonAnswer.getTag();
        QuestionModel question = appController.getQuestion(level);
        String variant = question.getVariant();
        String typedLetter = (String) buttonAnswer.getText();

        for (int i = 0; i < MAX_VARIANT; i++) {
            String currentLetter = String.valueOf(variant.charAt(i));
            if (typedLetter.equals(currentLetter) && !typedVariants.get(i)) {
                variantButtons.get(i).setVisibility(View.VISIBLE);
                typedVariants.set(i, true);
                typedAnswer.set(position, null);
                buttonAnswer.setText("");
                buttonAnswer.setBackgroundResource(R.drawable.bg_answer);
                return;
            }
        }
    }

    private void checkWinner() throws InterruptedException {
        QuestionModel question = appController.getQuestion(level);
        String answer = question.getAnswer();

        StringBuilder answerBuilder = new StringBuilder();
        for (int i = 0; i < typedAnswer.size(); i++) {
            answerBuilder.append(typedAnswer.get(i));
        }
        String userAnswer = answerBuilder.toString();
        if (answer.length() < userAnswer.length()) return;

        if (answer.equals(userAnswer)) {
            coin += 1;
            level++;

        } else {
            unCorrect += 1;

            level++;

        }
        if (appController.getQuestionCount() > level) {
            loadQuestion();
        } else {

            Intent intentResult = new Intent(this, FinalResultActivity.class);
            intentResult.putExtra(Constants.SUBJECT, "Geography");
            intentResult.putExtra(Constants.CORRECT, coin);
            intentResult.putExtra(Constants.TYPE, "geography");
            intentResult.putExtra(Constants.INCORRECT, Constants.QUESTION_SHOWING - coin);
            intentResult.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intentResult);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}