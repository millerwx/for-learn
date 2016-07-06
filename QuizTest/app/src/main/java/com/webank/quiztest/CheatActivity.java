package com.webank.quiztest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by miller on 16/6/18.
 */
public class CheatActivity extends Activity{
    // constants
    public static String EXTRA_ANSWER_IS_TRUE = "com.webank.quiztest.answer_is_true";
    public static String EXTRA_ANSWER_SHOWN = "com.webank.quiztest.extra_answer_shown";

    private boolean mAnswerIsTrue;
    private Button mShowAnswerBtn;
    private TextView mShowAnswerText;


    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);


        setAnswerShownResult(false);
        mShowAnswerBtn = (Button)findViewById(R.id.showAnswerBtn);
        mShowAnswerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue){
                    mShowAnswerText.setText(R.string.btn_yes);
                }
                else {
                    mShowAnswerText.setText(R.string.btn_no);
                }
                setAnswerShownResult(true);
            }
        });


        mShowAnswerText = (TextView)findViewById(R.id.answerTextView);



    }

}
