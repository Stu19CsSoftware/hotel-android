package com.ranlychan.hotel.widget;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;

import com.lxj.xpopup.core.CenterPopupView;
import com.ranlychan.hotel.R;
import com.ranlychan.hotel.service.RoomSelectService;

public class SelectRoomAndGuestNumPopView extends CenterPopupView{

    private NumberView nvRoomNumberSelector;
    private NumberView nvAdultNumberSelector;
    private NumberView nvChildNumberSelector;

    private Button btnConfirm;

    private RoomSelectService roomSelectService;

    private int needRoomNumber = 1;
    private int adultNumber = 1;
    private int childNumber = 0;

    private PopupWindow.OnDismissListener onDismissListener;

    public SelectRoomAndGuestNumPopView(@NonNull Context context, PopupWindow.OnDismissListener onDismissListener) {
        super(context);
        this.onDismissListener = onDismissListener;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        initRoomSelectService();
        initView();

    }

    private void initRoomSelectService() {
        roomSelectService = RoomSelectService.getRoomSelectService();
    }

    private void initView() {
        nvRoomNumberSelector = findViewById(R.id.nv_room_number_selector);
        nvAdultNumberSelector = findViewById(R.id.nv_adult_number_selector);
        nvChildNumberSelector = findViewById(R.id.nv_child_number_selector);
        btnConfirm = findViewById(R.id.btn_guest_select_confirm);

        nvRoomNumberSelector.setDefaultValue(roomSelectService.getNeedRoomNumber());
        nvAdultNumberSelector.setDefaultValue(roomSelectService.getAdultGuestNumber());
        nvChildNumberSelector.setDefaultValue(roomSelectService.getChildGuestNumber());

        nvRoomNumberSelector.setOnValueChangeListener(new NumberView.OnValueChangeListener() {
            @Override
            public void onValueChange(int value) {
                needRoomNumber = value;
            }
        });
        nvAdultNumberSelector.setOnValueChangeListener(new NumberView.OnValueChangeListener() {
            @Override
            public void onValueChange(int value) {
                adultNumber = value;
            }
        });
        nvChildNumberSelector.setOnValueChangeListener(new NumberView.OnValueChangeListener() {
            @Override
            public void onValueChange(int value) {
                childNumber = value;
            }
        });


        btnConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                roomSelectService.setNeedRoomNumber(needRoomNumber);
                roomSelectService.setAdultGuestNumber(adultNumber);
                roomSelectService.setChildGuestNumber(childNumber);
                dismiss();
            }
        });

    }

    // 返回自定义弹窗的布局
    @Override
    protected int getImplLayoutId() {
        return R.layout.select_guest_popup_view;
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
        onDismissListener.onDismiss();
    }
}
