package com.ranlychan.ktv.bean.request;

public class QueryRoomTypeDetailRequestBean extends BaseTokenRequestBean{
    private String rtid;

    public QueryRoomTypeDetailRequestBean(String session_token, String rtid) {
        super(session_token);
        this.rtid = rtid;
    }

    public String getRtid() {
        return rtid;
    }

    public void setRtid(String rtid) {
        this.rtid = rtid;
    }


}
