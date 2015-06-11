package com.kejiwen.architecture.customview;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kejiwen.architecture.R;
import com.kejiwen.architecture.listener.OnBackStackListener;

public final class TitleBar {
    private Context mContext;

    private View mRoot;
    private ImageButton mBackButton;
    private TextView mTitle;
    private ImageButton mSettingButton;

    public TitleBar(Activity activity, int titleId) {
        this(activity.getWindow().getDecorView(), titleId);
        mContext = activity;
    }

    public TitleBar(View view, int titleId) {
        mRoot = view.findViewById(titleId);
        mBackButton = (ImageButton) mRoot.findViewById(R.id.back);
        mTitle = (TextView) mRoot.findViewById(R.id.title);
        mSettingButton = (ImageButton) mRoot.findViewById(R.id.settings);
    }

    public void setTitleVisibility(boolean visibility) {
        if (visibility) {
            mRoot.setVisibility(View.VISIBLE);
        } else {
            mRoot.setVisibility(View.GONE);
        }
    }

    public void setTitle(CharSequence title) {
        mTitle.setText(title);
    }

    public void setTitle(int titleRes) {
        setTitle(mContext.getText(titleRes));
    }


    public void setBackButton(int resid, final OnBackStackListener listener) {
        if (resid > 0) {
            mBackButton.setImageResource(resid);
        }
        if (listener != null) {
            mBackButton.setOnClickListener(
                    new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.onBackStack();
                        }
                    });
            mBackButton.setFocusable(true);
        }
    }

    public ImageButton setSettingButton(int resid,  OnClickListener listener) {
        if (resid > 0) {
            mSettingButton.setImageResource(resid);
        }
        if (listener != null) {
            mSettingButton.setOnClickListener(listener);
            mSettingButton.setVisibility(View.VISIBLE);
        } else {
            mSettingButton.setVisibility(View.GONE);
        }
        return mSettingButton;
    }


    public void setBackground(int id) {
        mRoot.setBackgroundResource(id);
    }

    public void setBackgroundColor(int color) {
        mRoot.setBackgroundColor(color);
    }

}
