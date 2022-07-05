package com.ranlychan.ktv.service;

import com.ranlychan.ktv.entity.OrderInfo;
import com.ranlychan.ktv.listener.OnOrderAddListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import cn.leancloud.LCObject;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class OrderInfoService {
    /* 添加房间预订 */
    public void AddOrderInfo(HashMap<String,Object> orderParam, OnOrderAddListener listener) {
        // 构建对象
        LCObject order = new LCObject("Order");
        // 为属性赋值
        order.put("checkInDate", (Date)orderParam.get("checkInDate"));//入住时间
        order.put("checkOutDate", (Date)orderParam.get("checkOutDate"));//退房时间
        order.put("memo", (String)orderParam.get("memo"));//备注
        order.put("roomTypeObjId", (String)orderParam.get("roomTypeObjId"));//房型id
        order.put("userObjId", (String)orderParam.get("userObjId"));//预定用户id
        order.put("guestObjIdArr", (ArrayList<String>)orderParam.get("guestObjIdArr"));//住客id的ArrayList

        // 将对象保存到云端
        order.saveInBackground().subscribe(new Observer<LCObject>() {
            public void onSubscribe(Disposable disposable) {}
            public void onNext(LCObject order) {
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setOrderObjId(order.getObjectId());
            }
            public void onError(Throwable throwable) {
                listener.onOrderAddError(throwable);
            }
            public void onComplete() {
                listener.onOrderAddComplete();
            }
        });
    }

    /* 取消房间预订 */
    public void cancelOrderInfo(String orderObjId) {

    }

    /* 查询房间预订 */
    public void QueryOrderInfo(OrderInfo queryConditionOrderInfo) {

    }

    /* 根据订单编号获取房间预订对象 */
    public void GetOrderInfo(String orderObjId)  {

    }

}
