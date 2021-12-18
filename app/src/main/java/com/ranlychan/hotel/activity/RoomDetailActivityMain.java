package com.ranlychan.hotel.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.ranlychan.hotel.R;
import com.ranlychan.hotel.entity.RoomType;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;

import java.util.List;

import cn.leancloud.LCObject;
import cn.leancloud.LCQuery;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import static com.ranlychan.hotel.entity.RoomType.PRICE_UNIT;
import static com.ranlychan.hotel.entity.RoomType.ROOMTYPE_OBJECTID_INTENT_TAG;

public class RoomDetailActivityMain extends BaseActivity {

    private final int GET_ROOM_DETAIL_FAILED = 0;
    private final int GET_ROOM_DETAIL_SUCCESS = 1;

    private RoomType roomType;
    private String roomTypeObjId;

    private TitleBar titleBar;
    private TextView tvRoomName;
    private TextView tvPrice;
    private TextView tvShortIntro;
    private TextView tvFullIntro;
    private Button btnOrder;

    private Handler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail_main);
        try{
            roomTypeObjId =  getIntent().getStringExtra(ROOMTYPE_OBJECTID_INTENT_TAG);
        }catch (Exception e){
            e.printStackTrace();
        }

        initView();
        intiTitleBar();
        initHandler();
        RichText.initCacheDir(RoomDetailActivityMain.this); //设置缓存目录，不设置会报错
    }

    @Override
    protected void onStart() {
        super.onStart();
        idToRoomTypeDetail();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //结束时清空内容
        RichText.clear(RoomDetailActivityMain.this);
    }


    private void initView(){
        titleBar = findViewById(R.id.tb_room_detail);
        btnOrder = findViewById(R.id.btn_room_detail_order);
        tvRoomName = findViewById(R.id.tv_room_type_name);
        tvPrice = findViewById(R.id.tv_room_price);
        tvShortIntro = findViewById(R.id.tv_room_short_into);
        tvFullIntro = findViewById(R.id.tv_room_full_into);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null != roomType){
                    Intent intent = new Intent(RoomDetailActivityMain.this, RoomOrderActivityMain.class);
                    intent.putExtra(ROOMTYPE_OBJECTID_INTENT_TAG,roomType.getObjectId());
                    startActivity(intent);
                }
            }
        });

    }

    private void initData(){
        try{
            tvRoomName.setText(roomType.getName());
            tvPrice.setText(PRICE_UNIT+String.format("%.1f",roomType.getPrice()));
            tvShortIntro.setText(roomType.getShortIntro());
            tvFullIntro.setText(roomType.getIntro());

            RichText.fromMarkdown(roomType.getIntro())
                    .showBorder(false)
                    .bind(this)
                    .size(ImageHolder.MATCH_PARENT, ImageHolder.WRAP_CONTENT)
                    .into(tvFullIntro);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void initHandler(){
        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case GET_ROOM_DETAIL_FAILED:
                        Toast.makeText(RoomDetailActivityMain.this, "获取数据失败！", Toast.LENGTH_SHORT).show();
                        break;
                    case GET_ROOM_DETAIL_SUCCESS:
                        initData();
                        break;
                }
            }
        };
    }

    private void idToRoomTypeDetail(){
        LCQuery<LCObject> query = new LCQuery<>("RoomTpye");//LeanCloud里面打错了不是RoomType。。。
        query.whereEqualTo("objectId",roomTypeObjId);
        query.getFirstInBackground().subscribe(new Observer<LCObject>() {
            public void onSubscribe(Disposable disposable) {}

            @Override
            public void onNext(@NonNull LCObject lcObject) {
                roomType = new RoomType();
                roomType.setObjectId(lcObject.getString("objectId"));
                roomType.setName(lcObject.getString("Name"));
                roomType.setPrice(lcObject.getNumber("Price").floatValue());
                roomType.setAvailableRoomNum(lcObject.getNumber("availableRoomNum").intValue());
                roomType.setCoverImgUrl(lcObject.getString("coverImgUrl"));
                roomType.setBednumber(lcObject.getNumber("Bednumber").intValue());
                roomType.setDeposit(lcObject.getNumber("Deposit").floatValue());
                roomType.setWashroom(lcObject.getBoolean("Washroom"));
                roomType.setAircondition(lcObject.getBoolean("Aircondition"));
                roomType.setTelephone(lcObject.getBoolean("Telephone"));
                roomType.setTv(lcObject.getBoolean("Tv"));
                roomType.setArea(lcObject.getNumber("Area").floatValue());
                roomType.setIntro(lcObject.getString("Intro"));
                roomType.setShortIntro(lcObject.getString("shortIntro"));
                roomType.setCreatedAt(lcObject.getDate("createdAt"));
                roomType.setUpdatedAt(lcObject.getDate("updatedAt"));
            }


            public void onError(Throwable throwable) {
                Message msg = handler.obtainMessage();
                msg.what = GET_ROOM_DETAIL_FAILED;
                msg.sendToTarget();
                throwable.printStackTrace();
            }
            public void onComplete() {
                Message msg = handler.obtainMessage();
                msg.what = GET_ROOM_DETAIL_SUCCESS;
                msg.sendToTarget();
            }
        });
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