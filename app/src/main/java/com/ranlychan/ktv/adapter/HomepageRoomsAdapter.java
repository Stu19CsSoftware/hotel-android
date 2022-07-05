package com.ranlychan.ktv.adapter;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ranlychan.ktv.Constant;
import com.ranlychan.ktv.R;
import com.ranlychan.ktv.bean.SRoomTypeBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.List;

public class HomepageRoomsAdapter extends BaseQuickAdapter<SRoomTypeBean,BaseViewHolder> {

    public HomepageRoomsAdapter(int layoutResId, @Nullable List<SRoomTypeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, SRoomTypeBean roomType) {
        String price = new BigDecimal(roomType.getPrice()).stripTrailingZeros().toPlainString();
        baseViewHolder.setText(R.id.tv_item_room_name,roomType.getName())
                .setText(R.id.tv_item_room_price, Constant.getMoneyUnitMark()+ price +"\n/每晚")
                .setText(R.id.tv_item_room_into,roomType.getShort_intro());
        Glide.with(getContext())
                .load(roomType.getCover_img())
                .into((ImageView) baseViewHolder.getView(R.id.iv_item_room_img));
        Log.d("RRRR",roomType.getName()+price+roomType.getShort_intro());
    }
}
