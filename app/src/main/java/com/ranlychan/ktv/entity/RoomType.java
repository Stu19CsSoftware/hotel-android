package com.ranlychan.ktv.entity;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

import java.io.Serializable;
import java.util.HashMap;

@Table(database = AppDataBase.class)
public class RoomType implements Serializable {
    public static final String ROOMTYPE_OBJECTID_INTENT_TAG = "roomTypeObjId";
    public static final String PRICE_UNIT = "¥";

    @PrimaryKey
    private String objectId;

    @Column
    private String name;

    private HashMap<String,Object> roomTypeFacilities;

    /*
    private boolean Aircondition;
    private float Area;
    private int Bednumber;
    private boolean Telephone;
    private boolean Tv;
    private boolean Washroom;
     */
    @Column
    private String coverImgUrl;
    @Column
    private float deposit;
    @Column
    private float price;
    @Column
    private int availableRoomNum;
    @Column
    private String shortIntro;
    @Column
    private String intro;



    public static String getPriceUnit() {
        return PRICE_UNIT;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Object> getRoomTypeFacilities() {
        return roomTypeFacilities;
    }

    public void setRoomTypeFacilities(HashMap<String, Object> roomTypeFacilities) {
        this.roomTypeFacilities = roomTypeFacilities;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public float getDeposit() {
        return deposit;
    }

    public void setDeposit(float deposit) {
        this.deposit = deposit;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAvailableRoomNum() {
        return availableRoomNum;
    }

    public void setAvailableRoomNum(int availableRoomNum) {
        this.availableRoomNum = availableRoomNum;
    }

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
}
