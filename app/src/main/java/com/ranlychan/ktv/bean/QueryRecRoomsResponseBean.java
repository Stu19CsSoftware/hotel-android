package com.ranlychan.ktv.bean;

import java.util.List;

public class QueryRecRoomsResponseBean {
    private List<SRoomTypeBean> list;

    public List<SRoomTypeBean> getsRoomTypeBeanList() {
        return list;
    }

    public void setsRoomTypeBeanList(List<SRoomTypeBean> sRoomTypeBeanList) {
        this.list = sRoomTypeBeanList;
    }
}
