package com.ranlychan.hotel.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ranlychan.hotel.R;
import com.ranlychan.hotel.entity.MeOption;
import com.ranlychan.hotel.entity.RoomType;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MeOptionAdapter extends BaseQuickAdapter<MeOption, BaseViewHolder> {
    public MeOptionAdapter(int layoutResId, @Nullable List<MeOption> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, MeOption option) {
        baseViewHolder.setText(R.id.tv_me_item_option_name,option.getName())
        .setImageResource(R.id.iv_me_item_option_icon,option.getIconId());
    }
}
