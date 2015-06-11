package com.kejiwen.architecture.activity;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.shizhefei.view.listviewhelper.ListViewHelper;
import com.kejiwen.architecture.R;
import com.kejiwen.architecture.adapter.ProductHistoryAdapter;
import com.kejiwen.architecture.adapter.ProductOnSellAdapter;
import com.kejiwen.architecture.biz.ProductHistoryDataSource;
import com.kejiwen.architecture.biz.ProductOnSellDataSource;
import com.kejiwen.architecture.customview.SlideShowView;
import com.kejiwen.architecture.listener.OnPageClickListener;
import com.kejiwen.architecture.model.ProductItem;
import com.kejiwen.architecture.utils.SystemInfoUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends BaseFragment implements OnPageClickListener{
    private final static String TAG = "ProductFragment";

    private static final String[] PRODUCT_TAB_NAMES = {"在售产品", "历史产品"};

    private IndicatorViewPager mIndicatorViewPager;
    private LayoutInflater mInflate;
    private ListViewHelper<List<ProductItem>> listViewHelper;
    private SlideShowView mSlideShowView;
    private ArrayList<ImageView> mBannerViews;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        setContentView(R.layout.fragment_tab_product);
        Resources res = getResources();


        ViewPager viewPager = (ViewPager) findViewById(R.id.fragment_product_viewPager);
        FixedIndicatorView indicator = (FixedIndicatorView) findViewById(R.id.fragment_product_indicator);
        indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.TRANSPARENT, 5));

        float unSelectSize = 16;
        float selectSize = unSelectSize * 1.0f;//缩放参数

        int selectColor = res.getColor(R.color.tab_top_text_2);
        int unSelectColor = res.getColor(R.color.tab_top_text_1);
        indicator.setOnTransitionListener(new OnTransitionTextListener(selectSize, unSelectSize, selectColor, unSelectColor));

        viewPager.setOffscreenPageLimit(2);

        mIndicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        mInflate = LayoutInflater.from(getApplicationContext());
        mIndicatorViewPager.setAdapter(adapter);

        mSlideShowView = (SlideShowView) mInflate.inflate(R.layout.slide_show_view, null)
                .findViewById(R.id.slideshowView);
        ImageView imageView = new ImageView(getActivity());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setBackgroundDrawable(getResources().getDrawable(
                R.mipmap.banner_defalt));
        mBannerViews = new ArrayList<ImageView>();
        mBannerViews.add(imageView);
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                SystemInfoUtils.dip2px(getApplicationContext(), 100));
        mSlideShowView.setLayoutParams(lp);
        mSlideShowView.setPageOnClickListener(this);
        mSlideShowView.setImageViews(mBannerViews);


    }


    private IndicatorViewPager.IndicatorPagerAdapter adapter = new IndicatorViewPager.IndicatorViewPagerAdapter() {

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = mInflate.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(PRODUCT_TAB_NAMES[position]);
            return convertView;
        }

        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = mInflate.inflate(R.layout.product_fragment, container, false);
            }
            PullToRefreshListView refreshListView = (PullToRefreshListView)convertView.findViewById(R.id.pullToRefreshListView);
            listViewHelper = new ListViewHelper<List<ProductItem>>(refreshListView);
            //设置banner
            listViewHelper.setBanner(mSlideShowView);
            if (position == 0) {
                // 设置数据源
                listViewHelper.setDataSource(new ProductOnSellDataSource());
                // 设置适配器
                listViewHelper.setAdapter(new ProductOnSellAdapter(getActivity()));
            } else {
                // 设置数据源
                listViewHelper.setDataSource(new ProductHistoryDataSource());
                // 设置适配器
                listViewHelper.setAdapter(new ProductHistoryAdapter(getActivity()));
            }

            // 加载数据
            listViewHelper.refresh();
            return convertView;
        }

        @Override
        public int getCount() {
            return PRODUCT_TAB_NAMES.length;
        }
    };

    //banner监听
    @Override
    public void onPageClick(int position) {
//        Intent it = new Intent(getActivity(), CommonWebViewActivity.class);
//        it.putExtra("url", "file:///android_asset/productDetail.html");
//        startActivity(it);
    }

    @Override
    public void onClickMethod(View v) {

    }
}
