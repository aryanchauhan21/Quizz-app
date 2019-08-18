package com.leotarius.quizzz;

public class TrueFalse {
    private int questionID;
    private boolean answerID;

    TrueFalse(int question, boolean ans){
        questionID = question;
        answerID = ans;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public boolean isAnswerID() {
        return answerID;
    }

    public void setAnswerID(boolean answerID) {
        this.answerID = answerID;
    }
}
