package com.ranlychan.ktv.listener;

import com.ranlychan.ktv.bean.SRoomTypeBean;

import java.util.List;

public interface OnQueryRoomTypeListListener {

    void onQueryComplete(List<SRoomTypeBean> list);

    void onQueryError(Throwable throwable);
}
