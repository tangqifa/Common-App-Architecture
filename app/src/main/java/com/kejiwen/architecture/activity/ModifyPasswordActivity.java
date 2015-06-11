package com.kejiwen.architecture.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kejiwen.architecture.R;

public class ModifyPasswordActivity extends BaseActivity implements OnClickListener {

    private final static String TAG = "ModifyPasswordActivity";

    private EditText mEditText;
    private EditText mNewEditText;
    private Button mSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.modify_password);
        super.onCreate(savedInstanceState);
        mTitleBar.setTitle("修改密码");
        mTitleBar.setBackButton(R.mipmap.titlebar_back_arrow, this);

        mEditText = (EditText) findViewById(R.id.login_password);
        mNewEditText = (EditText) findViewById(R.id.login_new_password);
        mSubmitBtn = (Button) findViewById(R.id.submit_btn);
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
        String password = mEditText.getText().toString();
        String newPassword = mNewEditText.getText().toString();
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "新密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(newPassword)){
            Toast.makeText(this, "确认新密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }

        if (!TextUtils.equals(newPassword, password)){
            Toast.makeText(this, "您前后输入的密码不一致,请检查", Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(this, "修改成功", Toast.LENGTH_LONG).show();
        finish();
    }
}
