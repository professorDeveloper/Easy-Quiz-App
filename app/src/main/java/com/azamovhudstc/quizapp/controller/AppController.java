package com.azamovhudstc.quizapp.controller;

import com.azamovhudstc.quizapp.R;
import com.azamovhudstc.quizapp.model.QuestionModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AppController {
    private List<QuestionModel> questionModelList;
    private int level = 0;


    public AppController() {
        this.questionModelList = new ArrayList<>();
    }

    public void loadQuestion() {
//        questionModelList.add(new QuestionModel(R.drawable.uzbekistan, "", "daspeyodszea"));
        questionModelList.add(new QuestionModel(R.drawable.egypt, "misr", "nqakrfrsmird"));               //1
        questionModelList.add(new QuestionModel(R.drawable.china, "xitoy", "xynqftaokyqi"));      //2
        questionModelList.add(new QuestionModel(R.drawable.england, "anglya", "fdqargnylaqd"));   //3
        questionModelList.add(new QuestionModel(R.drawable.germany, "germanya", "erangdaynfmq"));//4
        questionModelList.add(new QuestionModel(R.drawable.france, "fransya", "kfyatanosher"));//5
        questionModelList.add(new QuestionModel(R.drawable.israil, "isroil", "oasrelodsiei"));//6
        questionModelList.add(new QuestionModel(R.drawable.italy, "italya", "posapyiytola"));//7
        questionModelList.add(new QuestionModel(R.drawable.spain, "ispaniya", "nidyarsifagp"));//8
        questionModelList.add(new QuestionModel(R.drawable.turkey, "turkiya", "ikdoraybusot"));//9
        questionModelList.add(new QuestionModel(R.drawable.korea, "koreya", "bedarmqdkoky"));//10
        questionModelList.add(new QuestionModel(R.drawable.brazil, "brazilya", "bedarazdkily"));//11
         questionModelList.add(new QuestionModel(R.drawable.qozoq, "qozoq", "qedorazdkqoy"));//12
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(questionModelList);
    }

    public int getQuestionCount() {
        return questionModelList.size();
    }

    public QuestionModel getQuestion(int level) {//leveldagi indexni bizga qaytaradi !
        return questionModelList.get(level);
    }
}
