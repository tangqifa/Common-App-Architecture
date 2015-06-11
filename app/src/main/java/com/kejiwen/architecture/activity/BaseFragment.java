package com.kejiwen.architecture.activity;

import java.lang.reflect.Field;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kejiwen.architecture.listener.XmlClickableListener;

public abstract class BaseFragment extends Fragment implements XmlClickableListener {
    private LayoutInflater mInflater;
    private View mContentView;
    private Context mContext;
    private ViewGroup mContainerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity().getApplicationContext();
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mInflater = inflater;
        this.mContainerView = container;
        onCreateView(savedInstanceState);
        if (mContentView == null)
            return super.onCreateView(inflater, container, savedInstanceState);
        return mContentView;
    }

    protected void onCreateView(Bundle savedInstanceState) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mContentView = null;
        mContainerView = null;
        mInflater = null;
    }

    public Context getApplicationContext() {
        return mContext;
    }

    public void setContentView(int layoutResID) {
        setContentView(mInflater.inflate(layoutResID, mContainerView, false));
    }

    public void setContentView(View view) {
        mContentView = view;
    }

    public View findViewById(int id) {
        if (mContentView != null)
            return mContentView.findViewById(id);
        return null;
    }

    // http://stackoverflow.com/questions/15207305/getting-the-error-java-lang-illegalstateexception-activity-has-been-destroyed
    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
