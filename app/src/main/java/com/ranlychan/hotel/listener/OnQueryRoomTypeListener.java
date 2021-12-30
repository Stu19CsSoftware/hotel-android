package com.ranlychan.hotel.listener;

import com.ranlychan.hotel.entity.RoomType;

import java.util.List;

public interface OnQueryRoomTypeListener {

    void onQueryComplete(RoomType roomType);

    void onQueryError(Throwable throwable);
}
