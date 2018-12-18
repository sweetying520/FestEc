package com.dream.latte_core.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by zy on 2018/12/14.
 */
public abstract class BaseFragment extends SwipeBackFragment {

    private Unbinder mUnbinder;
    public abstract Object setRootView();

    public abstract void bindView(View rootView,Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = null;
        if(setRootView() instanceof View){
            mRootView = (View) setRootView();
        }else if(setRootView() instanceof Integer){
            mRootView = inflater.inflate((Integer) setRootView(),container,false);
        }

        if(mRootView != null){
            mUnbinder = ButterKnife.bind(this,mRootView);
            bindView(mRootView,savedInstanceState);
        }
        return mRootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder != null){
            mUnbinder.unbind();
        }
    }
}
