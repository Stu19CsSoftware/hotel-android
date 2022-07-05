package com.ranlychan.ktv.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class RoomSelectService {
    private static RoomSelectService roomSelectService;
    private Date checkInDate;//入住日期
    private Date checkOutDate;//退房日期
    private int orderTotalDay = 1 ;//入住天数
    //private int guestNumber;//住客人数
    private int adultGuestNumber = 1;
    private int childGuestNumber = 0;
    private int needRoomNumber = 1;

    private RoomSelectService(){

    }

    public static RoomSelectService getRoomSelectService() {
        synchronized (RoomSelectService.class) {
            if (roomSelectService == null) {
                roomSelectService = new RoomSelectService();
            }
        }
        return roomSelectService;
    }

    public Date getCheckInDate() {
        if(checkInDate == null){
            checkInDate = new Date();
            //SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

        }
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        if(checkOutDate == null){
            checkOutDate = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(checkOutDate);
            calendar.add(calendar.DATE,1); //把日期往后增加一天,整数往后推,负数往前移动
            checkOutDate = calendar.getTime(); //这个时间就是日期往后推一天的结果
        }
        return checkOutDate;
    }

    public String getFormattedCheckInDate(String formatPattern){
        SimpleDateFormat ft = new SimpleDateFormat(formatPattern);
        return ft.format(getCheckInDate());
    }

    public String getFormattedCheckOutDate(String formatPattern){
        SimpleDateFormat ft = new SimpleDateFormat(formatPattern);
        return ft.format(getCheckOutDate());
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getOrderTotalDay() {
        return orderTotalDay;
    }

    public void setOrderTotalDay(int orderTotalDay) {
        this.orderTotalDay = orderTotalDay;
    }

    public int getAdultGuestNumber() {
        return adultGuestNumber;
    }

    public void setAdultGuestNumber(int adultGuestNumber) {
        this.adultGuestNumber = adultGuestNumber;
    }

    public int getChildGuestNumber() {
        return childGuestNumber;
    }

    public void setChildGuestNumber(int childGuestNumber) {
        this.childGuestNumber = childGuestNumber;
    }

    public int getNeedRoomNumber() {
        return needRoomNumber;
    }

    public void setNeedRoomNumber(int needRoomNumber) {
        this.needRoomNumber = needRoomNumber;
    }
}
