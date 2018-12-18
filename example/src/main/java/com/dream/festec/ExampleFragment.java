package com.dream.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.latte_core.base.LatteFragment;
import com.dream.latte_core.network.Loader;
import com.dream.latte_core.network.RestClient;
import com.dream.latte_core.network.callback.ISuccess;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zy on 2018/12/14.
 */
public class ExampleFragment extends LatteFragment {

    public static final String TAG = ExampleFragment.class.getSimpleName();

    @BindView(R.id.tv_example_fragment)
    TextView tvExampleFragment;
    @OnClick(R.id.btn_test)
    void netClick(){
        Loader.showLoading(_mActivity,true);
        new Thread(){
            @Override
            public void run() {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                _mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RestClient.builder()
                                .setContext(_mActivity)
                                .url("http://www.baidu.com")
                                .setSuccess(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        Log.d(TAG, "onSuccess: " + response);
                                        Loader.dismissLoading();
                                    }
                                })
                                .build()
                                .get(false);
                    }
                });
            }
        }.start();

    }

    public static ExampleFragment getInstance() {
        ExampleFragment mFragment = new ExampleFragment();
        Bundle args = new Bundle();
        mFragment.setArguments(args);
        return mFragment;
    }

    @Override
    public Object setRootView() {
        return R.layout.fragment_example;
    }

    @Override
    public void bindView(View rootView, Bundle savedInstanceState) {
        Toast.makeText(_mActivity, tvExampleFragment.getText().toString(), Toast.LENGTH_SHORT).show();
        Log.d(TAG, "bindView: ");

    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        Log.d(TAG, "onLazyInitView: 懒加载");

        RestClient.builder()
                .setContext(_mActivity)
                .url("http://www.baidu.com")
                .setSuccess(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d(TAG, "onSuccess: " + response);
                        Loader.dismissLoading();
                    }
                })
                .build()
                .get();
    }
}
