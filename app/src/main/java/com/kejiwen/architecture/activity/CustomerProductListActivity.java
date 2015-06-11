package com.kejiwen.architecture.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shizhefei.view.listviewhelper.ListViewHelper;
import com.kejiwen.architecture.R;
import com.kejiwen.architecture.adapter.CustomerProductAdapter;
import com.kejiwen.architecture.biz.CustomerProductDataSource;
import com.kejiwen.architecture.listener.OnBackStackListener;
import com.kejiwen.architecture.model.ProductItem;

import java.util.List;

public class CustomerProductListActivity extends BaseActivity implements OnBackStackListener, View.OnClickListener {
    private final static String TAG = "CustomerProductListActivity";

    private ListViewHelper mListViewHelper;

    private String mPhoneStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.customer_product_list);
        super.onCreate(savedInstanceState);
        mPhoneStr = getIntent().getStringExtra("phone");
        mTitleBar.setTitle(getIntent().getStringExtra("name"));
        mTitleBar.setBackButton(R.mipmap.titlebar_back_arrow, this);
        mTitleBar.setSettingButton(R.mipmap.titlebar_phone, this);

        PullToRefreshListView refreshListView = (PullToRefreshListView) findViewById(R.id.pullToRefreshListView);
        mListViewHelper = new ListViewHelper<List<ProductItem>>(refreshListView);

        // 设置数据源
        mListViewHelper.setDataSource(new CustomerProductDataSource());
        // 设置适配器
        mListViewHelper.setAdapter(new CustomerProductAdapter(this));
        // 加载数据
        mListViewHelper.refresh();
    }

    @Override
    public void onBackStack() {
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settings:
                callServicePhone();
                break;
        }
    }
    private void callServicePhone() {
        String uri = "tel:" + mPhoneStr.trim();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }
    @Override
    protected int getTitleBarRes() {
        return R.id.titlebar;
    }
}
