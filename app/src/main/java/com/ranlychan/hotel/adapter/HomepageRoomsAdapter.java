package com.ranlychan.hotel.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ranlychan.hotel.R;
import com.ranlychan.hotel.entity.RoomType;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HomepageRoomsAdapter extends BaseQuickAdapter<RoomType,BaseViewHolder> {

    public HomepageRoomsAdapter(int layoutResId, @Nullable List<RoomType> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, RoomType roomType) {
        baseViewHolder.setText(R.id.tv_item_room_name,roomType.getName())
                .setText(R.id.tv_item_room_price,String.valueOf(roomType.getPrice()));
        Glide.with(getContext())
                .load(roomType.getCoverImgUrl())
                .into((ImageView) baseViewHolder.getView(R.id.iv_item_room_img));
    }
}
