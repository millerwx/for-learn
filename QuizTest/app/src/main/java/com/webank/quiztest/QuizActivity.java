package com.webank.quiztest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private static String TAG = "QuizActivity";
    private static String KEY_INDEX = "key_index";

    private Button mYesBtn;
    private Button mNoBtn;
    private Button mNextBtn;
    private Button mCheatBtn;
    private TextView mTextView;
    private Boolean mIsCheater;

    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.QUESTION1, false),
            new TrueFalse(R.string.QUESTION2, true),
            new TrueFalse(R.string.QUESTION3, true),
            new TrueFalse(R.string.QUESTION4, true),
            new TrueFalse(R.string.QUESTION5, true),
            new TrueFalse(R.string.QUESTION6, true)
    };

    private int mCurrentIndex = 0;

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getmQuestion();
        mTextView.setText(question);
    };

    private void checkAnwser(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismTrueQuestion();

        int msgResId = 0;
        if(mIsCheater) {
            msgResId = R.string.judgement_toast;
        }
        else {
            if(userPressedTrue == answerIsTrue){
                msgResId = R.string.toast_right;
            }
            else {
                msgResId = R.string.toast_wrong;
            }
        }

        Toast.makeText(QuizActivity.this, msgResId, Toast.LENGTH_SHORT).show();
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null){
            return;
        }

        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart is called--");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause is called--");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume is called--");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop is called--");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestory is called--");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInsatanceState is called--");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate is called--");

        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        setContentView(R.layout.activity_quiz);
        // 初始化
        mYesBtn = (Button)this.findViewById(R.id.btn_yes);
        mNoBtn = (Button)this.findViewById(R.id.btn_no);
        mNextBtn = (Button)this.findViewById(R.id.btn_next);
        mTextView = (TextView)this.findViewById(R.id.txt_question);
        mCheatBtn = (Button)this.findViewById(R.id.btn_cheat);
        mIsCheater = false;

        updateQuestion();

        // 事件响应
        mYesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnwser(true);
            }
        });

        mNoBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkAnwser(false);
            }
        });

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsCheater = false;
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });


        mCheatBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // start cheat
                Intent i = new Intent(QuizActivity.this, CheatActivity.class);
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, mQuestionBank[mCurrentIndex].ismTrueQuestion());
                startActivityForResult(i, 0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
