package com.azamovhudstc.quizapp.model;

public class HistoryModel {
    private  long createdTime;
    String subject;

    private  int correct;
    private  int incorrect;
    private int earned;

    private int overallPoints;

    public HistoryModel(long createdTime, String subject, int correct, int incorrect, int earned) {
        this.createdTime = createdTime;
        this.subject = subject;
        this.correct = correct;
        this.incorrect = incorrect;
        this.earned = earned;
        this.overallPoints = overallPoints;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getEarned() {
        return earned;
    }

    public void setEarned(int earned) {
        this.earned = earned;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(int incorrect) {
        this.incorrect = incorrect;
    }

    public int getOverallPoints() {
        return overallPoints;
    }

    public void setOverallPoints(int overallPoints) {
        this.overallPoints = overallPoints;
    }
}
