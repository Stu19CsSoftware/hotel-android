package com.ranlychan.hotel.widget;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lxj.xpopup.animator.PopupAnimator;
import com.lxj.xpopup.core.CenterPopupView;
import com.ranlychan.hotel.Constant;
import com.ranlychan.hotel.R;
import com.ranlychan.hotel.utils.DateUtils;
import com.ranlychan.hotel.utils.LogUtils;

import java.util.Date;

public class CalendarPopView extends CenterPopupView implements View.OnClickListener {

    private TextView tvStartime;
    private TextView tvEndtime;
    private CalendarView calendarview;
    private String starTime;
    private String endTime;
    private boolean isSelectOk = false;

    //注意：自定义弹窗本质是一个自定义View，但是只需重写一个参数的构造，其他的不要重写，所有的自定义弹窗都是这样。
    public CalendarPopView(@NonNull Context context) {
        super(context);
    }

    // 返回自定义弹窗的布局
    @Override
    protected int getImplLayoutId() {
        return R.layout.calendar_popup_view;
    }

    // 执行初始化操作，比如：findView，设置点击，或者任何你弹窗内的业务逻辑
    @Override
    protected void onCreate() {
        super.onCreate();

        initView();
        /*
        findViewById(R.id.tv_close).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss(); // 关闭弹窗
            }
        });

         */
    }

    private void initView(){
        tvStartime = findViewById(R.id.tv_startime);
        tvEndtime = findViewById(R.id.tv_endtime);
        calendarview = findViewById(R.id.calendarview);

        //ivTitleBack.setVisibility(View.GONE);
        calendarview.setETimeSelListener(new CalendarView.CalendatEtimSelListener() {
            @Override
            public void onETimeSelect(Date date) {
                if (date != null) {
                    endTime = DateUtils.formatData(date, Constant.TFORMAT_YMD);
                    tvEndtime.setText(endTime);
                }else {
                    endTime = null;
                }
            }
        });
        calendarview.setSTimeSelListener(new CalendarView.CalendarSTimeSelListener() {
            @Override
            public void onSTimeSelect(Date date) {
                if (date != null) {
                    starTime = DateUtils.formatData(date, Constant.TFORMAT_YMD);
                    tvStartime.setText(starTime);
                }else {
                    starTime = null;
                }
            }
        });
        calendarview.setCalendaSelListener(new CalendarView.CalendaSelListener() {
            @Override
            public void selectStatus(boolean isOk) {
                isSelectOk = isOk;
            }
        });
    }



    // 设置最大宽度，看需要而定，
    @Override
    protected int getMaxWidth() {
        return super.getMaxWidth();
    }
    // 设置最大高度，看需要而定
    @Override
    protected int getMaxHeight() {
        return super.getMaxHeight();
    }
    // 设置自定义动画器，看需要而定
    @Override
    protected PopupAnimator getPopupAnimator() {
        return super.getPopupAnimator();
    }
    /**
     * 弹窗的宽度，用来动态设定当前弹窗的宽度，受getMaxWidth()限制
     *
     * @return
     */
    protected int getPopupWidth() {
        return 0;
    }

    /**
     * 弹窗的高度，用来动态设定当前弹窗的高度，受getMaxHeight()限制
     *
     * @return
     */
    protected int getPopupHeight() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_popup_date_range_confirm:
                if(TextUtils.isEmpty(starTime)){
                    LogUtils.e("startTime 空");
                    return;
                }
                if(TextUtils.isEmpty(endTime) || !isSelectOk){
                    LogUtils.e("startTime 空 不ok");
                    return;
                }
                LogUtils.e("startTime："+starTime);
                LogUtils.e("startTime："+endTime);
                break;
        }
    }
}