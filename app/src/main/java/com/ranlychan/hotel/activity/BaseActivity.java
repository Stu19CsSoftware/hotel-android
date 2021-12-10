package com.ranlychan.hotel.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;

public class BaseActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 所有子类都将继承这些相同的属性,请在设置界面之后设置
        ImmersionBar.with(this).init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // 如果你的app可以横竖屏切换，并且适配4.4或者emui3手机请务必在onConfigurationChanged方法里添加这句话
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ImmersionBar.with(this).init();
    }
}
