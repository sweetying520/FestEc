package com.dream.latte_core.network;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.WindowManager;

import com.dream.latte_core.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zy on 2018/12/14.
 */
public class Loader {

    private static final class DialogListHolder{
        private static final List<AppCompatDialog> DIALOG_LIST = new ArrayList<>();
    }


    public static void showLoading(Context mContext,boolean isShowLoading){
        if(!isShowLoading){
            return;
        }
        final AppCompatDialog mAppCompatDialog = new AppCompatDialog(mContext,R.style.LoadingDialogStyle);
        mAppCompatDialog.setContentView(R.layout.item_loading);

        if(mAppCompatDialog.getWindow() != null){
            WindowManager.LayoutParams layoutParams = mAppCompatDialog.getWindow().getAttributes();
            layoutParams.gravity = Gravity.CENTER;
            mAppCompatDialog.getWindow().setAttributes(layoutParams);
        }
        DialogListHolder.DIALOG_LIST.add(mAppCompatDialog);
        mAppCompatDialog.show();
    }

    public static void dismissLoading(){
        for (int i = 0; i < DialogListHolder.DIALOG_LIST.size(); i++) {
            AppCompatDialog appCompatDialog = DialogListHolder.DIALOG_LIST.get(i);
            if(appCompatDialog != null){
                appCompatDialog.cancel();
            }
        }
    }
}
