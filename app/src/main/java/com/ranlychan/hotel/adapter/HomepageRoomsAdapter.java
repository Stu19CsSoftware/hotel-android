package com.ranlychan.hotel.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ranlychan.hotel.Constant;
import com.ranlychan.hotel.R;
import com.ranlychan.hotel.entity.RoomType;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.util.List;

public class HomepageRoomsAdapter extends BaseQuickAdapter<RoomType,BaseViewHolder> {

    public HomepageRoomsAdapter(int layoutResId, @Nullable List<RoomType> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, RoomType roomType) {
        String price = new BigDecimal(roomType.getPrice()).stripTrailingZeros().toPlainString();
        baseViewHolder.setText(R.id.tv_item_room_name,roomType.getName())
                .setText(R.id.tv_item_room_price, Constant.getMoneyUnitMark()+ price +"\n/每晚")
                .setText(R.id.tv_item_room_into,roomType.getShortIntro());
        Glide.with(getContext())
                .load(roomType.getCoverImgUrl())
                .into((ImageView) baseViewHolder.getView(R.id.iv_item_room_img));
    }
}
