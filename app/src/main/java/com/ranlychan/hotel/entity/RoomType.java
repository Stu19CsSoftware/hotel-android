package com.ranlychan.hotel.entity;

import java.io.Serializable;
import java.util.Date;

public class RoomType implements Serializable {
    public static final String ROOMTYPE_OBJECTID_INTENT_TAG = "roomTypeObjId";
    public static final String PRICE_UNIT = "¥";

    private String objectId;
    private boolean Aircondition;
    private float Area;
    private int Bednumber;
    private String CoverImgUrl;
    private float Deposit;
    private String Name;
    private float Price;
    private boolean Telephone;
    private boolean Tv;
    private boolean Washroom;
    private int availableRoomNum;
    private String shortIntro;
    private String intro;
    private Date createdAt;
    private Date updatedAt;


    public String getShortIntro() {
        return shortIntro;
    }

    public void setShortIntro(String shortIntro) {
        this.shortIntro = shortIntro;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public boolean isAircondition() {
        return Aircondition;
    }

    public void setAircondition(boolean aircondition) {
        Aircondition = aircondition;
    }

    public float getArea() {
        return Area;
    }

    public void setArea(float area) {
        Area = area;
    }

    public int getBednumber() {
        return Bednumber;
    }

    public void setBednumber(int bednumber) {
        Bednumber = bednumber;
    }

    public String getCoverImgUrl() {
        return CoverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        CoverImgUrl = coverImgUrl;
    }

    public float getDeposit() {
        return Deposit;
    }

    public void setDeposit(float deposit) {
        Deposit = deposit;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public boolean isTelephone() {
        return Telephone;
    }

    public void setTelephone(boolean telephone) {
        Telephone = telephone;
    }

    public boolean isTv() {
        return Tv;
    }

    public void setTv(boolean tv) {
        Tv = tv;
    }

    public boolean isWashroom() {
        return Washroom;
    }

    public void setWashroom(boolean washroom) {
        Washroom = washroom;
    }

    public int getAvailableRoomNum() {
        return availableRoomNum;
    }

    public void setAvailableRoomNum(int availableRoomNum) {
        this.availableRoomNum = availableRoomNum;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
