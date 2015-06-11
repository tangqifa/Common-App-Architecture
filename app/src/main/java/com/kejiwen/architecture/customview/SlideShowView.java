package com.kejiwen.architecture.customview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kejiwen.architecture.R;
import com.kejiwen.architecture.listener.OnPageClickListener;

@SuppressLint("HandlerLeak")
public class SlideShowView extends FrameLayout {

    private List<ImageView> imageViewsList;
    private List<ImageView> dotViewsList;
    private LinearLayout mLinearLayout;

    private OnPageClickListener mPageOnClickListener;

    private ViewPager mViewPager;
    private int currentItem = 0;
    private ScheduledExecutorService scheduledExecutorService;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mViewPager.setCurrentItem(currentItem);
        }

    };

    public SlideShowView(Context context) {
        this(context, null);
    }

    public SlideShowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideShowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initUI(context);
        if (!(imageViewsList.size() <= 0))
        {
            setImageViews(imageViewsList);
        }

    }

    private void initUI(Context context) {
        imageViewsList = new ArrayList<ImageView>();
        dotViewsList = new ArrayList<ImageView>();
        LayoutInflater.from(context).inflate(R.layout.layout_slideshow, this, true);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mLinearLayout = (LinearLayout) findViewById(R.id.linearlayout);

    }

    public void setImageViews(List<ImageView> imageviews)
    {
        for (int i = 0; i < imageviews.size(); i++)
        {
            imageViewsList.add(imageviews.get(i));
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(10, 0, 0, 0);
        for (int i = 0; i < imageviews.size(); i++) {
            ImageView viewDot = new ImageView(getContext());
            if (i == 0) {
                viewDot.setBackgroundResource(R.mipmap.main_dot_white);
            } else {
                viewDot.setBackgroundResource(R.mipmap.main_dot_light);
            }
            viewDot.setLayoutParams(lp);
            dotViewsList.add(viewDot);
            mLinearLayout.addView(viewDot);
            if (imageviews.size() == 1) {
                mLinearLayout.setVisibility(View.GONE);
            } else {
                mLinearLayout.setVisibility(View.VISIBLE);
            }

        }
        mViewPager.setFocusable(true);
        mViewPager.setAdapter(new MyPagerAdapter());
        mViewPager.setOnPageChangeListener(new MyPageChangeListener());
        startAutoScroll();
    }

    private void startAutoScroll() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
    }

    private void stopAutoScroll() {
        scheduledExecutorService.shutdown();
    }

    private void setImageBackground(int selectItems) {
        for (int i = 0; i < dotViewsList.size(); i++) {
            if (i == selectItems) {
                dotViewsList.get(i).setBackgroundResource(R.mipmap.main_dot_white);
            } else {
                dotViewsList.get(i).setBackgroundResource(R.mipmap.main_dot_light);
            }
        }
    }

    public void setPageOnClickListener(OnPageClickListener listener) {
        if (mPageOnClickListener == null) {
            mPageOnClickListener = listener;
        }
    }

    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(imageViewsList.get(position));

        }

        @Override
        public Object instantiateItem(View container, final int position) {
            ImageView iv = imageViewsList.get(position);
            ((ViewPager) container).addView(iv);
            iv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    mPageOnClickListener.onPageClick(position);
                }
            });
            return imageViewsList.get(position);
        }

        @Override
        public int getCount() {
            return imageViewsList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }

    }

    private class MyPageChangeListener implements OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int pos) {
            setImageBackground(pos % imageViewsList.size());
        }

    }

    private class SlideShowTask implements Runnable {

        @Override
        public void run() {
            synchronized (mViewPager) {
                currentItem = (currentItem + 1) % imageViewsList.size();
                handler.obtainMessage().sendToTarget();
            }
        }

    }

    @SuppressWarnings("unused")
    private void destoryBitmaps() {

        for (int i = 0; i < imageViewsList.size(); i++) {
            ImageView imageView = imageViewsList.get(i);
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                drawable.setCallback(null);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);
        if ((action == MotionEvent.ACTION_DOWN)) {
            stopAutoScroll();
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
            startAutoScroll();
        }
        return super.dispatchTouchEvent(ev);
    }

}
