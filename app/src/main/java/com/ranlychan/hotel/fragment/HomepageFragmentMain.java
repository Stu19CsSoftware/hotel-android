package com.ranlychan.hotel.fragment;

import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.github.gzuliyujiang.calendarpicker.CalendarPicker;
import com.github.gzuliyujiang.calendarpicker.OnRangeDatePickListener;
import com.github.gzuliyujiang.calendarpicker.OnSingleDatePickListener;
import com.github.gzuliyujiang.wheelpicker.DatePicker;
import com.github.gzuliyujiang.wheelpicker.contract.OnDatePickedListener;
import com.gyf.immersionbar.ImmersionBar;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.ranlychan.hotel.HotelApplication;
import com.ranlychan.hotel.R;
import com.ranlychan.hotel.activity.RoomDetailActivityMain;
import com.ranlychan.hotel.activity.RoomOrderActivityMain;
import com.ranlychan.hotel.adapter.HomepageRoomsAdapter;
import com.ranlychan.hotel.entity.RoomType;
import com.ranlychan.hotel.listener.OnQueryRoomTypeListListener;
import com.ranlychan.hotel.service.RoomSelectService;
import com.ranlychan.hotel.service.RoomTypeService;
import com.ranlychan.hotel.widget.CalendarPopView;
import com.ranlychan.hotel.widget.SelectRoomAndGuestNumPopView;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.ranlychan.hotel.entity.RoomType.ROOMTYPE_OBJECTID_INTENT_TAG;


public class HomepageFragmentMain extends Fragment implements View.OnClickListener {

    public static final String FORMAT_MMdd = "MM月dd日";

    private final int DEFAULT_AVAILABLE_MONTHS = 6;//可以预定当前月后的几个月,6代表支持半年内的预定
    private final int GET_AVILI_ROOMS_FAILED = 0;
    private final int GET_AVILI_ROOMS_SUCCESS = 1;

    private TextView tvSelectDate;
    private TextView tvSelectGuestNumber;
    private TextView btnSearchRooms;

    private TextView tvCheckInAndOutDate;
    private TextView tvRoomAndGuestNumber;

    private RecyclerView rvRooms;
    private HomepageRoomsAdapter adapter;
    private List<RoomType> roomTypeList;

    private Handler handler;

    private RoomTypeService roomTypeService;
    private HotelApplication hotelApplication;
    private RoomSelectService roomSelectService;

    public HomepageFragmentMain() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).init();
        roomTypeService = new RoomTypeService();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_homepage_main, container, false);
        initViews(view);
        initAdapter();
        initHandler();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 必须调用该方法，防止内存泄漏
        ImmersionBar.with(this).destroy(this);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        ImmersionBar.with(this).init();
    }

    private void initAdapter() {
        //设置RecyclerView的布局管理器，决定显示出来是怎么排放
        LinearLayoutManager layoutM = new LinearLayoutManager(getContext());
        layoutM.setOrientation(LinearLayoutManager.VERTICAL);
        rvRooms.setLayoutManager(layoutM);

        //禁止嵌套滑动，使得页面滑动更流畅
        rvRooms.setNestedScrollingEnabled(false);

        //设置适配器
        roomTypeList = new ArrayList<>();
        adapter = new HomepageRoomsAdapter(R.layout.homepage_item_room_type,roomTypeList);
        rvRooms.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                //点击了第position个房型
                try{
                    Intent intent = new Intent(getContext(), RoomDetailActivityMain.class);
                    intent.putExtra(ROOMTYPE_OBJECTID_INTENT_TAG,roomTypeList.get(position).getObjectId());
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });
        adapter.addChildClickViewIds(R.id.btn_item_room_order);
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                try {
                    switch (view.getId()) {
                        case R.id.btn_item_room_order:
                            //点击了第position个房型的预定按钮
                            Intent intent = new Intent(getContext(), RoomOrderActivityMain.class);
                            intent.putExtra(ROOMTYPE_OBJECTID_INTENT_TAG,roomTypeList.get(position).getObjectId());
                            startActivity(intent);
                            break;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        getRoomsData();
    }

    private void initViews(View view){
        rvRooms = view.findViewById(R.id.rv_homepage_rooms);
        tvSelectDate = view.findViewById(R.id.tv_homepage_window_select_date);
        tvSelectGuestNumber = view.findViewById(R.id.tv_homepage_window_select_guest_number);
        btnSearchRooms = view.findViewById(R.id.btn_homepage_window_search);
        tvRoomAndGuestNumber = view.findViewById(R.id.tv_homepage_window_guest_number);
        tvCheckInAndOutDate = view.findViewById(R.id.tv_homepage_window_check_in_out_date);

        tvCheckInAndOutDate.setOnClickListener(this);
        tvRoomAndGuestNumber.setOnClickListener(this);
        tvSelectDate.setOnClickListener(this);
        tvSelectGuestNumber.setOnClickListener(this);
        btnSearchRooms.setOnClickListener(this);

        roomSelectService = RoomSelectService.getRoomSelectService();


        tvCheckInAndOutDate.setText(roomSelectService.getFormattedCheckInDate(FORMAT_MMdd)+" - "+
                roomSelectService.getFormattedCheckOutDate(FORMAT_MMdd));
        tvRoomAndGuestNumber.setText(String.valueOf(roomSelectService.getNeedRoomNumber())+"间 - "+String.valueOf(roomSelectService.getAdultGuestNumber())+"人");
    }

    private void initHandler(){
        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case GET_AVILI_ROOMS_FAILED:
                        Toast.makeText(getContext(), "获取数据失败！", Toast.LENGTH_SHORT).show();
                        break;
                    case GET_AVILI_ROOMS_SUCCESS:
                        Toast.makeText(getContext(), "获取数据成功！", Toast.LENGTH_SHORT).show();
                        for(int i=0;i<roomTypeList.size();i++){
                            Log.d("ranlychan","room name=" + roomTypeList.get(i).getName());
                        }
                        adapter.notifyDataSetChanged();
                        break;
                }
            }
        };
    }

    /**
     * 默认获取房型数据，当房型的可用房间数大于0即返回
     */
    private void getRoomsData(){
        roomTypeList.clear();

        roomTypeService.queryRoomTypes(new OnQueryRoomTypeListListener() {
            @Override
            public void onQueryComplete(List<RoomType> list) {
                roomTypeList.addAll(list);
                Message msg = handler.obtainMessage();
                msg.what = GET_AVILI_ROOMS_SUCCESS;
                msg.sendToTarget();
            }

            @Override
            public void onQueryError(Throwable throwable) {
                Message msg = handler.obtainMessage();
                msg.what = GET_AVILI_ROOMS_FAILED;
                msg.sendToTarget();
            }
        });


        //异步开始前
        //HomepageGetRoomsIdlingResource.increment();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_homepage_window_check_in_out_date:
            case R.id.tv_homepage_window_select_date:
                RoomSelectService roomSelectService = RoomSelectService.getRoomSelectService();

                CalendarPicker picker = new CalendarPicker(getActivity());
                picker.setRangeDateOnFuture(DEFAULT_AVAILABLE_MONTHS);
                //picker.setRangeDate(roomSelectService.getCheckInDate(),roomSelectService.getCheckOutDate());
                picker.setSelectedDate(roomSelectService.getCheckInDate(),roomSelectService.getCheckOutDate());
                picker.setOnRangeDatePickListener(new OnRangeDatePickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onRangeDatePicked(@NonNull Date startDate, @NonNull Date endDate) {
                        roomSelectService.setCheckInDate(startDate);
                        roomSelectService.setCheckOutDate(endDate);
                        roomSelectService.setOrderTotalDay(Days.daysBetween(new DateTime(startDate),new DateTime(endDate)).getDays());
                        tvCheckInAndOutDate.setText(roomSelectService.getFormattedCheckInDate(FORMAT_MMdd)+" - "+roomSelectService.getFormattedCheckOutDate(FORMAT_MMdd));
                    }
                });

                picker.show();

                /*
                new XPopup.Builder(getContext())
                        .asCustom(new CalendarPopView(getContext()))
                        .show();

                 */
                break;
            case R.id.tv_homepage_window_guest_number:
            case R.id.tv_homepage_window_select_guest_number:
                roomSelectService = RoomSelectService.getRoomSelectService();
                BasePopupView infoSelectWindow = new XPopup.Builder(getContext())
                        .asCustom(new SelectRoomAndGuestNumPopView(getContext(), new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                                tvRoomAndGuestNumber.setText(String.valueOf(roomSelectService.getNeedRoomNumber())+"间 - "+String.valueOf(roomSelectService.getAdultGuestNumber())+"人");
                            }
                        }))
                        .show();
                break;
            case R.id.btn_homepage_window_search:
                roomSelectService = RoomSelectService.getRoomSelectService();

                roomTypeList.clear();
                adapter.notifyDataSetChanged();
                HashMap<String,Object> param = new HashMap<>();
                param.put("startDate",roomSelectService.getCheckInDate());
                param.put("endDate",roomSelectService.getCheckOutDate());
                param.put("availableRoomNum",roomSelectService.getNeedRoomNumber());
                param.put("availableGuestNum",roomSelectService.getAdultGuestNumber());

                roomTypeService.queryRoomTypes(param,new OnQueryRoomTypeListListener() {
                    @Override
                    public void onQueryComplete(List<RoomType> list) {
                        roomTypeList.addAll(list);
                        Message msg = handler.obtainMessage();
                        msg.what = GET_AVILI_ROOMS_SUCCESS;
                        msg.sendToTarget();
                    }

                    @Override
                    public void onQueryError(Throwable throwable) {
                        Message msg = handler.obtainMessage();
                        msg.what = GET_AVILI_ROOMS_FAILED;
                        msg.sendToTarget();
                    }
                });
                break;
        }
    }
}