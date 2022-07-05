package com.ranlychan.ktv.entity;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


@Table(database = AppDataBase.class)
public class OrderInfo implements Serializable {
    /*订单编号*/
    @PrimaryKey
    private String orderObjId;

    /*预订的房型*/
    @Column
    private String roomTypeObjId;

    /*预订的用户*/
    @Column
    private String userObjId;

    /*入住时间*/
    @Column
    private Date checkInTime;

    /*退房时间*/
    @Column
    private Date checkOutTime;

    /*订单金额*/
    @Column
    private float orderTotalPrice;

    /*附加信息*/
    @Column
    private String orderMemo;

    /*下单时间*/
    @Column
    private Date orderAddTime;

    /*预定到的房间id*/
    @Column
    private String roomObjId;

    /*住客编号列表*/
    private ArrayList<String> guestObjIdList;


    public String getOrderObjId() {
        return orderObjId;
    }

    public void setOrderObjId(String orderObjId) {
        this.orderObjId = orderObjId;
    }

    public String getRoomTypeObjId() {
        return roomTypeObjId;
    }

    public void setRoomTypeObjId(String roomTypeObjId) {
        this.roomTypeObjId = roomTypeObjId;
    }

    public String getUserObjId() {
        return userObjId;
    }

    public void setUserObjId(String userObjId) {
        this.userObjId = userObjId;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public float getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(float orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public String getOrderMemo() {
        return orderMemo;
    }

    public void setOrderMemo(String orderMemo) {
        this.orderMemo = orderMemo;
    }

    public Date getOrderAddTime() {
        return orderAddTime;
    }

    public void setOrderAddTime(Date orderAddTime) {
        this.orderAddTime = orderAddTime;
    }

    public String getRoomObjId() {
        return roomObjId;
    }

    public void setRoomObjId(String roomObjId) {
        this.roomObjId = roomObjId;
    }

    public ArrayList<String> getGuestObjIdList() {
        return guestObjIdList;
    }

    public void setGuestObjIdList(ArrayList<String> guestObjIdList) {
        this.guestObjIdList = guestObjIdList;
    }
}