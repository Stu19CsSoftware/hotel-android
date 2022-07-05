package com.ranlychan.ktv.listener;

import com.ranlychan.ktv.entity.RoomType;

public interface OnQueryRoomTypeListener {

    void onQueryComplete(RoomType roomType);

    void onQueryError(Throwable throwable);
}
