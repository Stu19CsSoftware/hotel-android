package com.ranlychan.ktv.fragment;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.gyf.immersionbar.ImmersionBar;
import com.makeramen.roundedimageview.RoundedImageView;
import com.ranlychan.ktv.R;
import com.ranlychan.ktv.adapter.MeOptionAdapter;
import com.ranlychan.ktv.entity.MeOption;

import java.util.ArrayList;
import java.util.List;


public class MeFragmentMain extends Fragment implements View.OnClickListener {

    private final int GET_OPTIONS_FAILED = 0;
    private final int GET_OPTIONS_SUCCESS = 1;

    private RecyclerView rvOptions;
    private MeOptionAdapter adapter;
    private List<MeOption> optionList;

    private Handler handler;

    private RoundedImageView rviUserAvatar;
    private TextView tvUserName;
    private TextView tvEditProfile;

    public MeFragmentMain() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me_main, container, false);

        initViews(view);
        initAdapter();
        initHandler();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
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
        rvOptions.setLayoutManager(layoutM);

        //禁止嵌套滑动，使得页面滑动更流畅
        rvOptions.setNestedScrollingEnabled(false);

        //设置适配器
        optionList = new ArrayList<>();
        adapter = new MeOptionAdapter(R.layout.me_item_option,optionList);
        rvOptions.setAdapter(adapter);

        //添加分割线
        rvOptions.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                //点击了第position个选项
                //Intent intent = new Intent(getContext(), OptionActivity.class);
                //startActivity(intent);

            }
        });

    }

    private void initViews(View view){
        rvOptions = view.findViewById(R.id.rv_me_options);
        rviUserAvatar = view.findViewById(R.id.iv_me_avatar);
        tvUserName = view.findViewById(R.id.tv_me_name);
        tvEditProfile = view.findViewById(R.id.tv_me_profile_edit);
    }

    private void initHandler(){
        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case GET_OPTIONS_FAILED:
                        Toast.makeText(getContext(), "获取数据失败！", Toast.LENGTH_SHORT).show();
                        break;
                    case GET_OPTIONS_SUCCESS:
                        adapter.notifyDataSetChanged();
                        //Toast.makeText(getContext(), "获取数据成功！", Toast.LENGTH_SHORT).show();
                        for(int i=0;i<optionList.size();i++){
                            Log.d("ranlychan","option name=" + optionList.get(i).getName());
                        }
                        break;
                }
            }
        };
    }
    private void getOptsData(){
        optionList.clear();
        optionList.add(new MeOption(1,"修改密码",R.drawable.ic_password));
        optionList.add(new MeOption(2,"设置",R.drawable.ic_setting));
        optionList.add(new MeOption(3,"我的订单",R.drawable.ic_bill));

        Message msg = handler.obtainMessage();
        msg.what=GET_OPTIONS_SUCCESS;
        msg.sendToTarget();

    }

    private void getPersonalData(){

        /*测试加载个人信息数据开始*/
        Glide.with(getContext())
                .load("https://s3.bmp.ovh/imgs/2021/12/bebbc313714d0b5f.png")
                .into(rviUserAvatar);
        tvUserName.setText("名字");
        /*测试数据结束*/
        //Toast.makeText(getContext(), "添加完成", Toast.LENGTH_SHORT).show();


        rviUserAvatar.setOnClickListener(this);
        tvUserName.setOnClickListener(this);
        rviUserAvatar.setOnClickListener(this);
    }

    private void initData(){
        if(handler != null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    getOptsData();
                    getPersonalData();
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_me_name:
                break;
            case R.id.tv_me_profile_edit:
                break;
            case R.id.iv_me_avatar:
                break;
        }
    }
}