package com.ranlychan.hotel.activity;

import android.os.Bundle;
import android.widget.Button;

import com.ranlychan.hotel.R;
import com.ranlychan.hotel.entity.RoomType;


public class RoomOrderActivityMain extends BaseActivity {

    private RoomType roomType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_order_main);

        try{

        }catch (Exception e){
            e.printStackTrace();
        }

        initView();
    }

    private void initView(){

    }
}