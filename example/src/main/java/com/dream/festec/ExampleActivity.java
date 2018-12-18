package com.dream.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dream.latte_core.base.BaseActivity;

import me.yokeyword.fragmentation.ISupportFragment;

public class ExampleActivity extends BaseActivity {

    @Override
    protected ISupportFragment setLayout() {
        return ExampleFragment.getInstance();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
