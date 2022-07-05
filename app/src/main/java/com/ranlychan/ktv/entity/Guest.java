package com.ranlychan.ktv.entity;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * 酒店住客类
 * 不是移动端用户
 */
@Table(database = AppDataBase.class)
public class Guest {
    @PrimaryKey
    private String guestObjId;
    @Column
    private String name;
    @Column
    private String phoneNumber;
    @Column
    private String idNumber;

    public String getGuestObjId() {
        return guestObjId;
    }

    public void setGuestObjId(String guestObjId) {
        this.guestObjId = guestObjId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
