package com.azamovhudstc.quizapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.azamovhudstc.quizapp.R;
import com.azamovhudstc.quizapp.adapter.HistoryAdapter;
import com.azamovhudstc.quizapp.local_data.QuizPref;
import com.azamovhudstc.quizapp.model.HistoryModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView rvHistory;
    private ArrayList<HistoryModel> historyModelList;
    private TextView tvTotalPoints, tvTotalQuestions;
    QuizPref quizPref;
    private int overallPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initView();
        findViewById(R.id.imageViewHistory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Collections.sort(loadData(), new AttemptCreatedTimeComparator());

        if (historyModelList.size()==0){
            findViewById(R.id.placeHolder).setVisibility(View.VISIBLE);
        }else {
            HistoryAdapter adapter = new HistoryAdapter(historyModelList, this);
            rvHistory.setAdapter(adapter);
            tvTotalQuestions.setText(String.valueOf(historyModelList.size()));
            for (HistoryModel userWithAttempts : historyModelList) {
                overallPoints += userWithAttempts.getEarned();
            }
            tvTotalPoints.setText(String.valueOf(overallPoints));

        }


    }

    public void initView() {
        quizPref = QuizPref.getInstance();
        rvHistory = findViewById(R.id.rvHistory);
        tvTotalQuestions = findViewById(R.id.tvTotalQuestionsHistory);
        tvTotalPoints = findViewById(R.id.tvOverAllPointsHistory);

    }

    private ArrayList<HistoryModel> loadData() {

        historyModelList = new ArrayList<HistoryModel>();
        Gson gson = new Gson();
        String gsonString = quizPref.getHistoryQuiz();
        Type type = new TypeToken<ArrayList<HistoryModel>>() {
        }.getType();
        if (!gsonString.isEmpty()) {
            historyModelList = gson.fromJson(gsonString, type);

        }
        return historyModelList;
    }

    public class AttemptCreatedTimeComparator implements Comparator<HistoryModel> {

        @Override
        public int compare(HistoryModel attempt, HistoryModel t1) {
            return String.valueOf(t1.getCreatedTime()).compareTo(String.valueOf(attempt.getCreatedTime()));
        }
    }
}