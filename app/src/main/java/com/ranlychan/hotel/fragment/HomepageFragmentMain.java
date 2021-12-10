package com.ranlychan.hotel.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.gyf.immersionbar.ImmersionBar;
import com.ranlychan.hotel.R;
import com.ranlychan.hotel.activity.RoomDetailActivityMain;
import com.ranlychan.hotel.activity.RoomOrderActivityMain;
import com.ranlychan.hotel.adapter.HomepageRoomsAdapter;
import com.ranlychan.hotel.entity.RoomType;

import java.util.ArrayList;
import java.util.List;

import cn.leancloud.LCObject;
import cn.leancloud.LCQuery;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class HomepageFragmentMain extends Fragment {

    private final int GET_AVILI_ROOMS_FAILED = 0;
    private final int GET_AVILI_ROOMS_SUCCESS = 1;

    private RecyclerView rvRooms;
    private HomepageRoomsAdapter adapter;
    private List<RoomType> roomTypeList;

    private Handler handler;


    public HomepageFragmentMain() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).init();

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

    // 如果你的app可以横竖屏切换，并且适配4.4或者emui3手机请务必在onConfigurationChanged方法里添加这句话
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
        adapter = new HomepageRoomsAdapter(R.layout.homepage_item_room,roomTypeList);
        rvRooms.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                //点击了第position个房型
                Intent intent = new Intent(getContext(), RoomDetailActivityMain.class);
                startActivity(intent);

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
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "获取数据成功！", Toast.LENGTH_SHORT).show();
                        for(int i=0;i<roomTypeList.size();i++){
                            Log.d("ranlychan","room name=" + roomTypeList.get(i).getName());
                        }
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

        LCQuery<LCObject> query = new LCQuery<>("RoomTpye");//LeanCloud里面打错了不是RoomType。。。
        query.whereGreaterThan("availableRoomNum", 0);//查询条件
        query.findInBackground().subscribe(new Observer<List<LCObject>>() {
            public void onSubscribe(Disposable disposable) {}
            public void onNext(List<LCObject> list) {
                for(int i=0;i<list.size();i++){
                    RoomType roomType = new RoomType();
                    roomType.setObjectId(list.get(i).getString("objectId"));
                    roomType.setName(list.get(i).getString("Name"));
                    roomType.setPrice(list.get(i).getNumber("Price").floatValue());
                    roomType.setAvailableRoomNum(list.get(i).getNumber("availableRoomNum").intValue());
                    roomType.setCoverImgUrl(list.get(i).getString("coverImgUrl"));
                    roomType.setBednumber(list.get(i).getNumber("Bednumber").intValue());
                    roomType.setDeposit(list.get(i).getNumber("Deposit").floatValue());
                    roomType.setWashroom(list.get(i).getBoolean("Washroom"));
                    roomType.setAircondition(list.get(i).getBoolean("Aircondition"));
                    roomType.setTelephone(list.get(i).getBoolean("Telephone"));
                    roomType.setTv(list.get(i).getBoolean("Tv"));
                    roomType.setArea(list.get(i).getNumber("Area").floatValue());
                    roomType.setIntro(list.get(i).getString("intro"));
                    roomType.setShortIntro(list.get(i).getString("shortIntro"));
                    roomType.setCreatedAt(list.get(i).getDate("createdAt"));
                    roomType.setUpdatedAt(list.get(i).getDate("updatedAt"));
                    roomTypeList.add(roomType);
                }
            }
            public void onError(Throwable throwable) {
                Message msg = handler.obtainMessage();
                msg.what = GET_AVILI_ROOMS_FAILED;
                msg.sendToTarget();
                throwable.printStackTrace();
            }
            public void onComplete() {
                Message msg = handler.obtainMessage();
                msg.what = GET_AVILI_ROOMS_SUCCESS;
                msg.sendToTarget();
            }
        });
    }
}