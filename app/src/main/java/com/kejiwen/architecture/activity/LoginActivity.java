package com.kejiwen.architecture.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kejiwen.architecture.R;
import com.kejiwen.architecture.constant.Constants;
import com.kejiwen.architecture.constant.FeatureConfig;
import com.kejiwen.architecture.utils.SharePreferenceUtils;

public class LoginActivity extends BaseActivity implements OnClickListener {

    private final static String TAG = "LoginActivity";

    private EditText mUserName;
    private EditText mPassword;
    private TextView mForgetPassword;

    private String mUserNameStr;
    private String mPasswordStr;
    private Button mLoginBtn;

    private InputMethodManager mImm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.login);
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        mTitleBar.setTitle("登录");
        mUserName = (EditText) findViewById(R.id.login_name);
        mPassword = (EditText) findViewById(R.id.login_password);
        mForgetPassword = (TextView) findViewById(R.id.login_forget_password);
        mForgetPassword.setOnClickListener(this);
        mLoginBtn = (Button) findViewById(R.id.login_btn);
        mLoginBtn.setOnClickListener(this);
    }

    @Override
    protected int getTitleBarRes() {
        return R.id.titlebar;
    }

    private void initData() {
        String name = SharePreferenceUtils.getStringSecPref(this, Constants.KEY_LOGIN_USER_NAME, "");
        mUserName.setText(name);
        if (!TextUtils.isEmpty(name)) {
            mUserName.setSelection(name.length());
            mPassword.requestFocus();
        }
        if (FeatureConfig.DEBUG_LOG) {
            String password = SharePreferenceUtils.getStringSecPref(this, Constants.KEY_LOGIN_PASSWORD,
                    "");
            mPassword.setText(password);
            if (!TextUtils.isEmpty(password)) mPassword.setSelection(password.length());
        }
        mImm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    public void onClick(View arg0) {
        if (arg0 == mLoginBtn) {
            mUserNameStr = mUserName.getText().toString();
            mPasswordStr = mPassword.getText().toString();
            if (TextUtils.isEmpty(mUserNameStr)) {
                Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                return;
            } else if (TextUtils.isEmpty(mPasswordStr)) {
                Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            startActivity(new Intent(this, TabMainActivity.class));
            finish();


        } else if (arg0 == mForgetPassword) {
            Intent it = new Intent(LoginActivity.this, ModifyPasswordActivity.class);
            //startActivity(it);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
                System.exit(0);
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackStack() {

    }
}
