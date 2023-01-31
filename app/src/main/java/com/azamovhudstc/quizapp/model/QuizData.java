package com.azamovhudstc.quizapp.model;

public class QuizData {
    String chooses;
    boolean type;

    public QuizData(String chooses, boolean type) {
        this.chooses = chooses;
        this.type = type;
    }

    public String getChooses() {
        return chooses;
    }

    public void setChooses(String chooses) {
        this.chooses = chooses;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
