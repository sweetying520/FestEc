package com.dream.latte_core.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.dream.latte_core.R;
import com.dream.latte_core.config.Latte;

import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by zy on 2018/12/14.
 */
public abstract class BaseActivity extends SupportActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(Bundle savedInstanceState){
        if(savedInstanceState == null){
            final FrameLayout mFrameLayout = new FrameLayout(Latte.getContext());
            mFrameLayout.setId(R.id.fragment_container);
            setContentView(mFrameLayout);
            loadRootFragment(R.id.fragment_container,setLayout());
        }
    }

    protected abstract ISupportFragment setLayout();
}
