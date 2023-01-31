package com.azamovhudstc.quizapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.azamovhudstc.quizapp.R;
import com.azamovhudstc.quizapp.ui.page.GeographyOrLiteratureQuizActivity;
import com.azamovhudstc.quizapp.ui.page.MathQuizActivity;
import com.azamovhudstc.quizapp.util.Constants;

public class QuizOptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_option);
        CardView cvMath = findViewById(R.id.cvMath);
        findViewById(R.id.imageViewQuizOption).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cvMath.setOnClickListener(view -> {
            mathClick();
        });
        CardView cvGeoGraph = findViewById(R.id.cvGeography);
        cvGeoGraph.setOnClickListener(view -> {
            geoGraphClick();
        });
        CardView cvLiterature = findViewById(R.id.cvLiterature);
        cvLiterature.setOnClickListener(view -> {
            literatureClick();
        });
    }

    public void mathClick() {
        Intent intent = new Intent(QuizOptionActivity.this, MathQuizActivity.class);
        intent.putExtra(Constants.SUBJECT, getString(R.string.geography));
        startActivity(intent);

    }

    public void geoGraphClick() {
        Intent intent = new Intent(QuizOptionActivity.this, FlagActivity.class);
        intent.putExtra(Constants.SUBJECT, getString(R.string.geography));
        startActivity(intent);
    }

    public void literatureClick() {
        Intent intent = new Intent(QuizOptionActivity.this, GeographyOrLiteratureQuizActivity.class);
        intent.putExtra(Constants.SUBJECT, getString(R.string.literature));
        startActivity(intent);
    }
}