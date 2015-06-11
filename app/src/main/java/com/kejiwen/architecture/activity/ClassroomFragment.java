package com.kejiwen.architecture.activity;

import android.os.Bundle;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shizhefei.view.listviewhelper.ListViewHelper;
import com.kejiwen.architecture.R;
import com.kejiwen.architecture.adapter.ClassroomAdapter;
import com.kejiwen.architecture.biz.ClassroomDataSource;
import com.kejiwen.architecture.model.ClassroomItem;

import java.util.List;

public class ClassroomFragment extends BaseFragment {
    private final static String TAG = "ClassroomFragment";

    private ListViewHelper<List<ClassroomItem>> mListViewHelper;


    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        setContentView(R.layout.fragment_tab_classroom);
        PullToRefreshListView refreshListView = (PullToRefreshListView) findViewById(R.id.pullToRefreshListView);
        mListViewHelper = new ListViewHelper<List<ClassroomItem>>(refreshListView);

        // 设置数据源
        mListViewHelper.setDataSource(new ClassroomDataSource());
        // 设置适配器
        mListViewHelper.setAdapter(new ClassroomAdapter(getApplicationContext()));
        // 加载数据
        mListViewHelper.refresh();
    }

    @Override
    public void onClickMethod(View v) {
    }
}
