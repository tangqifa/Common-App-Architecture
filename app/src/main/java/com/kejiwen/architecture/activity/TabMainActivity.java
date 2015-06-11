package com.kejiwen.architecture.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.kejiwen.architecture.R;
import com.kejiwen.architecture.listener.XmlClickableListener;

public class TabMainActivity extends BaseActivity {
    private final static String TAG = "TabMainActivity";
    private IndicatorViewPager mIndicatorViewPager;

    private static final String[] TAB_NAMES = {"客户", "产品", "资讯", "设置"};

    private XmlClickableListener mXmlClickableListener;
    private CustomerFragment fragment1 = null;
    private ProductFragment fragment2 = null;
    private ClassroomFragment fragment3 = null;
    private ProfileFragment fragment4 = null;
    @Override
    protected void onCreate(Bundle arg0) {
        setContentView(R.layout.activity_tabmain);
        super.onCreate(arg0);
        mTitleBar.setTitle(TAB_NAMES[0]);

        ViewPager viewPager = (ViewPager) findViewById(R.id.tabmain_viewPager);
        Indicator indicator = (Indicator) findViewById(R.id.tabmain_indicator);
        mIndicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        mIndicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        // 禁止viewpager的滑动事件
        viewPager.setCanScroll(false);
        // 设置viewpager保留界面不重新加载的页面数量
        viewPager.setOffscreenPageLimit(4);
        // 默认是1,，自动预加载左右两边的界面。设置viewpager预加载数为0。只加载加载当前界面。
        viewPager.setPrepareNumber(0);

        mIndicatorViewPager.setOnIndicatorPageChangeListener(new IndicatorViewPager.OnIndicatorPageChangeListener() {
            @Override
            public void onIndicatorPageChange(int preItem, int currentItem) {
                if (currentItem == 1) {
                    mTitleBar.setTitleVisibility(false);
                } else {
                    mTitleBar.setTitleVisibility(true);
                    mTitleBar.setTitle(TAB_NAMES[currentItem]);
                }

            }
        });
    }

    public void onClickMethod(View v) {
        mXmlClickableListener.onClickMethod(v);
    }

    @Override
    protected int getTitleBarRes() {
        return R.id.titlebar;
    }

    @Override
    public void onBackStack() {
    }

    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

        private int[] tabIcons = {R.drawable.maintab_customer_selector, R.drawable.maintab_product_selector, R.drawable.maintab_classroom_selector,
                R.drawable.maintab_profile_selector};
        private LayoutInflater inflater;

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            inflater = LayoutInflater.from(getApplicationContext());
        }

        @Override
        public int getCount() {
            return TAB_NAMES.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = (TextView) inflater.inflate(R.layout.tab_bottom, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(TAB_NAMES[position]);
            textView.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[position], 0, 0);
            return textView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            switch (position) {
                case 0:
                    fragment1 = new CustomerFragment();
                    return fragment1;
                case 1:
                    fragment2 = new ProductFragment();
                    return fragment2;
                case 2:
                    fragment3 = new ClassroomFragment();
                    return fragment3;
                case 3:
                    fragment4 = new ProfileFragment();
                    mXmlClickableListener = fragment4;
                    return fragment4;
            }
            return fragment1;
        }
    }
}
