package com.kejiwen.architecture.activity;

import android.os.Bundle;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shizhefei.view.listviewhelper.ListViewHelper;
import com.kejiwen.architecture.R;
import com.kejiwen.architecture.adapter.ProductCustomerHistoryListAdapter;
import com.kejiwen.architecture.adapter.ProductCustomerOnSellListAdapter;
import com.kejiwen.architecture.biz.ProductCustomerHistoryDataSource;
import com.kejiwen.architecture.biz.ProductCustomerOnsellDataSource;
import com.kejiwen.architecture.listener.OnBackStackListener;
import com.kejiwen.architecture.model.ProductItem;

import java.util.List;

public class ProductCustomerListActivity extends BaseActivity implements OnBackStackListener, View.OnClickListener {
    private final static String TAG = "CustomerProductListActivity";

    private ListViewHelper mListViewHelper;
    private ProductCustomerHistoryListAdapter mHistoryAdapter;
    private ProductCustomerOnSellListAdapter mOnSellAdapter;
    private int mListType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.customer_product_list);
        super.onCreate(savedInstanceState);
        mListType = getIntent().getIntExtra("type",0);
        mTitleBar.setTitle("客户列表");
        mTitleBar.setBackButton(R.mipmap.titlebar_back_arrow, this);

        PullToRefreshListView refreshListView = (PullToRefreshListView) findViewById(R.id.pullToRefreshListView);
        mListViewHelper = new ListViewHelper<List<ProductItem>>(refreshListView);

        if (mListType == 0) {
            // 设置数据源
            mOnSellAdapter = new ProductCustomerOnSellListAdapter(this);
            mListViewHelper.setDataSource(new ProductCustomerOnsellDataSource());
            // 设置适配器
            mListViewHelper.setAdapter(mOnSellAdapter);
        } else {
            mHistoryAdapter = new ProductCustomerHistoryListAdapter(this);
            // 设置数据源
            mListViewHelper.setDataSource(new ProductCustomerHistoryDataSource());
            // 设置适配器
            mListViewHelper.setAdapter(mHistoryAdapter);
        }

        // 加载数据
        mListViewHelper.refresh();
    }

    @Override
    public void onBackStack() {
        finish();
    }

    @Override
    public void onClick(View view) {

    }
    @Override
    public void onResume() {
        super.onResume();
        if (mListType == 0) {
            mOnSellAdapter.notifyDataSetChanged();
        } else {
            mHistoryAdapter.notifyDataSetChanged();
        }

    }
    @Override
    protected int getTitleBarRes() {
        return R.id.titlebar;
    }
}
