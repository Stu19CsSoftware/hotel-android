package com.ranlychan.ktv.activity;

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
import com.ranlychan.ktv.R;
import com.ranlychan.ktv.bean.RoomTypeBean;
import com.ranlychan.ktv.listener.OnResponseListener;
import com.ranlychan.ktv.service.impl.RoomTypeService;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.OnImageClickListener;

import java.util.List;

import static com.ranlychan.ktv.entity.RoomType.PRICE_UNIT;
import static com.ranlychan.ktv.entity.RoomType.ROOMTYPE_OBJECTID_INTENT_TAG;

public class RoomDetailActivityMain extends BaseActivity {

    private final int GET_ROOM_DETAIL_FAILED = 0;
    private final int GET_ROOM_DETAIL_SUCCESS = 1;

    private RoomTypeBean roomType;
    private String roomTypeObjId;

    private TitleBar titleBar;
    private TextView tvRoomName;
    private TextView tvPrice;
    private TextView tvShortIntro;
    private TextView tvFullIntro;
    private Button btnOrder;

    private Handler handler;

    private RoomTypeService roomTypeService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail_main);
        try{
            roomTypeObjId =  getIntent().getStringExtra(ROOMTYPE_OBJECTID_INTENT_TAG);
        }catch (Exception e){
            e.printStackTrace();
        }
        roomTypeService = new RoomTypeService();

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
                    intent.putExtra(ROOMTYPE_OBJECTID_INTENT_TAG,roomType.getRtid());
                    //intent.putExtra(,);
                    startActivity(intent);
                }
            }
        });

    }

    private void initData(){

        try{
            tvRoomName.setText(roomType.getName());
            tvPrice.setText(PRICE_UNIT+String.format("%.1f",roomType.getPrice()));
            tvShortIntro.setText(roomType.getShort_intro());
            tvFullIntro.setText(roomType.getFull_intro());

            RichText.fromMarkdown(roomType.getFull_intro())
                    .showBorder(false)
                    .bind(this)
                    .autoPlay(true)
                    .size(ImageHolder.MATCH_PARENT, ImageHolder.WRAP_CONTENT)
                    .imageClick(new OnImageClickListener() {
                        @Override
                        public void imageClicked(List<String> imageUrls, int position) {
                            /*
                            //TODO 图片放大
                            List<ImageDecoder.ImageInfo> imageInfosList = new ArrayList<>();
                            for (int i = 0; i < imageUrls.size(); i++) {
                                ImageDecoder.ImageInfo imageInfo = new ImageDecoder.ImageInfo(imageUrls.get(i), 200, 200);
                                imageInfosList.add(imageInfo);
                            }
                            if (imageInfosList == null || imageInfosList.isEmpty()) return;
                            PicShowDialog dialog = new PicShowDialog(LaiShowWebViewActivity.this, imageInfosList, position);
                            dialog.show();
                             */
                        }

                    })
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
                        Log.d("RoomDetailActivityMain","获取数据失败！");
                        Toast.makeText(RoomDetailActivityMain.this, "获取数据失败！", Toast.LENGTH_SHORT).show();
                        break;
                    case GET_ROOM_DETAIL_SUCCESS:
                        Log.d("RoomDetailActivityMain","获取数据成功！");
                        initData();
                        break;
                }
            }
        };
    }

    private void idToRoomTypeDetail(){
        roomTypeService.queryRoomDetail(new OnResponseListener<RoomTypeBean>() {
            @Override
            public void onQueryComplete(RoomTypeBean r) {
                roomType = r;
                Message msg = handler.obtainMessage();
                msg.what = GET_ROOM_DETAIL_SUCCESS;
                msg.sendToTarget();
            }

            @Override
            public void onQueryError(Throwable throwable) {
                Message msg = handler.obtainMessage();
                msg.what = GET_ROOM_DETAIL_FAILED;
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