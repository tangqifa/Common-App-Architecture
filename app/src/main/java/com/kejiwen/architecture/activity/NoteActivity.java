package com.kejiwen.architecture.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kejiwen.architecture.R;
import com.kejiwen.architecture.biz.CustomerDataSource;
import com.kejiwen.architecture.biz.CustomerProductDataSource;
import com.kejiwen.architecture.biz.ProductCustomerHistoryDataSource;
import com.kejiwen.architecture.biz.ProductCustomerOnsellDataSource;
import com.kejiwen.architecture.listener.OnBackStackListener;

public class NoteActivity extends BaseActivity implements OnBackStackListener, View.OnClickListener {
    private final static String TAG = "ProfileInfoActivity";

    private String mInfoStr;

    private EditText mEditText;
    private Button mSubmitBtn;
    private int mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.note);
        super.onCreate(savedInstanceState);
        mTitleBar.setTitle("备注");
        mTitleBar.setBackButton(R.mipmap.titlebar_back_arrow, this);

        mEditText = (EditText) findViewById(R.id.note_edit);
        mSubmitBtn = (Button) findViewById(R.id.submit_btn);
        mSubmitBtn.setOnClickListener(this);
        mInfoStr = getIntent().getStringExtra("info");
        mType = getIntent().getIntExtra("type", 0);

        if (!TextUtils.isEmpty(mInfoStr)) {
            mEditText.setText(mInfoStr);
        }
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
        mInfoStr = mEditText.getText().toString();
        int position = getIntent().getIntExtra("position",0);
        if (mType == 1) {
            CustomerDataSource.mLists.get(position).setAlias(mInfoStr);
        } else if (mType == 2) {
            CustomerProductDataSource.mLists.get(position).setComment(mInfoStr);
        } else if (mType == 3){
            ProductCustomerOnsellDataSource.mLists.get(position).setNote(mInfoStr);
        } else if (mType == 4) {
            ProductCustomerHistoryDataSource.mLists.get(position).setNote(mInfoStr);
        }

        Toast.makeText(this, "提交成功", Toast.LENGTH_LONG).show();
        finish();
    }

}
