package com.ranlychan.hotel.listener;

import com.ranlychan.hotel.entity.RoomType;

import java.util.List;

public interface OnQueryRoomTypeListListener {

    void onQueryComplete(List<RoomType> list);

    void onQueryError(Throwable throwable);
}
