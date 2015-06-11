package com.kejiwen.architecture.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.kejiwen.architecture.R;

public class ProfileFragment extends BaseFragment {
    private final static String TAG = "ProfileFragment";


    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        setContentView(R.layout.fragment_tab_profile);
    }


    @Override
    public void onClickMethod(View v) {
        switch (v.getId()) {
            case R.id.profile_achievement:
                Intent it = new Intent(getApplicationContext(),CommonWebViewActivity.class);
                it.putExtra("url", "file:///android_asset/performance.html");
                startActivity(it);
                break;
            case R.id.profile_password:
                startActivity(new Intent(getApplicationContext(),ModifyPasswordActivity.class));
                break;
            case R.id.profile_info:
                startActivity(new Intent(getApplicationContext(),ProfileInfoActivity.class));
                break;
            case R.id.profile_feedback:
                startActivity(new Intent(getApplicationContext(),FeedbackActivity.class));
                break;
            case R.id.profile_contact:
                callService();
                break;
            case R.id.profile_logout:
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                getActivity().finish();
                break;
        }
    }

    private void callService() {
        String posted_by = "4008793366";
        String uri = "tel:" + posted_by.trim();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

}
