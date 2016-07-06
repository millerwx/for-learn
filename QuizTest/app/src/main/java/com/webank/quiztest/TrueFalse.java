package com.webank.quiztest;

/**
 * Created by miller on 16/6/17.
 */
public class TrueFalse {
    private int mQuestion;        // 资源ID
    private boolean mTrueQuestion; //

    public int getmQuestion() {
        return mQuestion;
    }

    public void setmTrueQuestion(boolean mTrueQuestion) {
        this.mTrueQuestion = mTrueQuestion;
    }

    public void setmQuestion(int mQuestion) {

        this.mQuestion = mQuestion;
    }

    public boolean ismTrueQuestion() {

        return mTrueQuestion;
    }

    public TrueFalse(int question, boolean trueQuestion){
        mQuestion = question;
        mTrueQuestion = trueQuestion;
    }
}
