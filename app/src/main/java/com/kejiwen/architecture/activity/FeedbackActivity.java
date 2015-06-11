package com.kejiwen.architecture.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kejiwen.architecture.R;
import com.kejiwen.architecture.listener.OnBackStackListener;

public class FeedbackActivity extends BaseActivity implements OnBackStackListener, View.OnClickListener{

    private final static String TAG = "ProfileInfoActivity";

    private EditText mEditText;
    private Button mSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.profile_feedback);
        super.onCreate(savedInstanceState);
        mTitleBar.setTitle("反馈");
        mTitleBar.setBackButton(R.mipmap.titlebar_back_arrow, this);

        mEditText = (EditText) findViewById(R.id.feedback_edit);
        mSubmitBtn = (Button) findViewById(R.id.feedback_submit_btn);
        mSubmitBtn.setOnClickListener(this);
    }

    @Override
    protected int getTitleBarRes() {
        return R.id.titlebar;
    }

    @Override
    public void onBackStack() {
        finish();
    }

    @Override
    public void onClick(View view) {
        String info = mEditText.getText().toString();
        if (TextUtils.isEmpty(info)) {
            Toast.makeText(this,"反馈内容不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(this,"反馈成功", Toast.LENGTH_LONG).show();
        finish();
    }
}
