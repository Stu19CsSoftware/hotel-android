package com.ranlychan.hotel.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.ranlychan.hotel.R;

public class RoomDetailActivityMain extends BaseActivity {
    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail_main);
        initView();
        intiTitleBar();
    }

    private void initView(){
        titleBar = findViewById(R.id.tb_room_detail);
    }

    private void intiTitleBar(){
        titleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(TitleBar titleBar) {
                finish();
            }

            @Override
            public void onTitleClick(TitleBar titleBar) {

            }

            @Override
            public void onRightClick(TitleBar titleBar) {

            }
        });
    }
}