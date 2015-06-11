package com.kejiwen.architecture.activity;

import android.os.Bundle;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shizhefei.view.listviewhelper.ListViewHelper;
import com.kejiwen.architecture.R;
import com.kejiwen.architecture.adapter.CustomerAdapter;
import com.kejiwen.architecture.biz.CustomerDataSource;
import com.kejiwen.architecture.model.CustomerItem;

import java.util.List;

public class CustomerFragment extends BaseFragment {
    private final static String TAG = "CustomerFragment";

     ListViewHelper<List<CustomerItem>> mListViewHelper;

    private CustomerAdapter mCustomerAdapter;
    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        setContentView(R.layout.fragment_tab_classroom);
        PullToRefreshListView refreshListView = (PullToRefreshListView) findViewById(R.id.pullToRefreshListView);
        mListViewHelper = new ListViewHelper<List<CustomerItem>>(refreshListView);

        // 设置数据源
        mListViewHelper.setDataSource(new CustomerDataSource());
        // 设置适配器
        mCustomerAdapter = new CustomerAdapter(getActivity());
        mListViewHelper.setAdapter(mCustomerAdapter);
        // 加载数据
        mListViewHelper.refresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        mCustomerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickMethod(View v) {

    }

}
