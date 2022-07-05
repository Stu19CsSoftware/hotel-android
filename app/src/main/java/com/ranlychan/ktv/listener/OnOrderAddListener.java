package com.ranlychan.ktv.listener;

public interface OnOrderAddListener {
    /*订单添加成功*/
    void onOrderAddComplete();

    /*订单添加失败*/
    void onOrderAddError(Throwable throwable);

}
