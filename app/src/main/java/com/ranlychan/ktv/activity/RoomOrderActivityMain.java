package com.ranlychan.ktv.activity;

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
import com.ranlychan.ktv.Constant;
import com.ranlychan.ktv.R;
import com.ranlychan.ktv.bean.RoomTypeBean;
import com.ranlychan.ktv.listener.OnResponseListener;
import com.ranlychan.ktv.service.impl.ExpenseService;
import com.ranlychan.ktv.service.impl.RoomSelectService;
import com.ranlychan.ktv.service.impl.RoomTypeService;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;

import java.math.BigDecimal;
import java.util.HashMap;

import static com.ranlychan.ktv.entity.RoomType.ROOMTYPE_OBJECTID_INTENT_TAG;


public class RoomOrderActivityMain extends BaseActivity {

    private RoomTypeService roomTypeService;
    private RoomTypeBean roomType;
    private String roomTypeObjId;
    private Handler handler;

    private TitleBar titleBar;
    private Button btnConfirmOrder;
    private TextView tvBottomTotalPrice;
    private TextView tvCheckInTime;
    private TextView tvCheckOutTime;
    private TextView tvOrderTotalDay;
    private TextView tvRoomTypeName;
    private TextView tvRoomTypeIntro;
    private TextView tvRoomTypeShortIntro;

    private TextView tvRoomNumber;
    private TextView tvGuestName;
    private TextView tvGuestPhoneNumber;

    private static final int GET_ROOM_TYPE_DETAIL_FAILED = 0;
    private static final int GET_ROOM_TYPE_DETAIL_SUCCESS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_order_main);

        try{
            roomTypeObjId =  getIntent().getStringExtra(ROOMTYPE_OBJECTID_INTENT_TAG);
        }catch (Exception e){
            e.printStackTrace();
        }
        roomTypeService = new RoomTypeService();
        initView();
        initHandler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryData();
    }

    private void queryData() {
        roomTypeService.getRoomType(roomTypeObjId, new OnResponseListener<RoomTypeBean>() {
            @Override
            public void onQueryComplete(RoomTypeBean room) {
                roomType = room;
                initData();
            }

            @Override
            public void onQueryError(Throwable throwable) {

            }
        });
    }

    private void initView(){
        titleBar = findViewById(R.id.tb_room_order_detail);
        btnConfirmOrder = findViewById(R.id.btn_room_order_confirm);
        tvBottomTotalPrice = findViewById(R.id.tv_room_order_bottom_total_price);
        tvCheckInTime = findViewById(R.id.tv_order_check_in_date);
        tvCheckOutTime = findViewById(R.id.tv_order_check_out_date);
        tvOrderTotalDay = findViewById(R.id.tv_order_total_day);
        tvRoomTypeName = findViewById(R.id.tv_order_room_type_name);
        tvRoomTypeIntro = findViewById(R.id.tv_order_room_type_intro);
        tvRoomTypeShortIntro = findViewById(R.id.tv_order_room_type_short_intro);

        tvRoomNumber = findViewById(R.id.tv_order_room_number);
        tvGuestName = findViewById(R.id.tv_order_guest_name);
        tvGuestPhoneNumber = findViewById(R.id.tv_order_guest_phone_number);

        intiTitleBar();

        btnConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击提交订单
            }
        });
    }

    private void initHandler(){
        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case GET_ROOM_TYPE_DETAIL_FAILED:
                        Log.d("RoomOrderActivityMain","获取数据失败！");
                        Toast.makeText(RoomOrderActivityMain.this, "获取数据失败！", Toast.LENGTH_SHORT).show();
                        break;
                    case GET_ROOM_TYPE_DETAIL_SUCCESS:
                        Log.d("RoomOrderActivityMain","获取数据成功！");
                        initData();
                        break;

                }
            }
        };
    }

    private void initData(){
        RoomSelectService selectService = RoomSelectService.getRoomSelectService();

        HashMap<String,Object> param = new HashMap<>();
        param.put("roomNumber",selectService.getNeedRoomNumber());
        param.put("adultGuestNumber",selectService.getAdultGuestNumber());
        param.put("childGuestNumber",selectService.getChildGuestNumber());
        param.put("roomTypePrice",roomType.getPrice());
        param.put("orderDay",selectService.getOrderTotalDay());

        String totalPrice = new BigDecimal(ExpenseService.getExpense(param)).stripTrailingZeros().toPlainString();

        tvBottomTotalPrice.setText(Constant.getMoneyUnitMark()+totalPrice);
        tvCheckInTime.setText(selectService.getFormattedCheckInDate("MM月dd日"));
        tvCheckOutTime.setText(selectService.getFormattedCheckOutDate("MM月dd日"));
        tvOrderTotalDay.setText(String.valueOf(selectService.getOrderTotalDay())+"晚");
        tvRoomTypeName.setText(roomType.getName());
        tvRoomTypeShortIntro.setText(roomType.getShort_intro());

        RichText.fromMarkdown(roomType.getFull_intro())
                .showBorder(false)
                .bind(this)
                .noImage(true)
                .size(ImageHolder.MATCH_PARENT, ImageHolder.WRAP_CONTENT)
                .into(tvRoomTypeIntro);

        tvRoomNumber.setText(String.valueOf(selectService.getNeedRoomNumber()));
        //tvGuestName.setText(GuestInfoService.getGuestNames());
        //tvGuestPhoneNumber.setText(GuestInfoService.getGuestPhoneNumbers());
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