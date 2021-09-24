package com.example.courseexamapp;

public class Question {
    private int ansId;
    private boolean ans;

    public Question(int ansId, boolean ans){
        this.ansId = ansId;
        this.ans = ans;
    }

    public int getAnsId() {
        return ansId;
    }

    public void setAnsId(int ansId) {
        this.ansId = ansId;
    }

    public boolean isAns() {
        return ans;
    }

    public void setAns(boolean ans) {
        this.ans = ans;
    }
}

