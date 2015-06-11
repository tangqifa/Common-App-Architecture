package com.kejiwen.architecture.activity;

import android.os.Bundle;

import com.kejiwen.architecture.R;
import com.kejiwen.architecture.listener.OnBackStackListener;

public class ProfileInfoActivity extends BaseActivity implements OnBackStackListener{

    private final static String TAG = "ProfileInfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.profile_info);
        super.onCreate(savedInstanceState);
        mTitleBar.setTitle("个人信息");
        mTitleBar.setBackButton(R.mipmap.titlebar_back_arrow, this);
    }

    @Override
    protected int getTitleBarRes() {
        return R.id.titlebar;
    }

    @Override
    public void onBackStack() {
        finish();
    }
}
