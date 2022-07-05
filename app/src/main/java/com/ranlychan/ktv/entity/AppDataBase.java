package com.ranlychan.ktv.entity;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = AppDataBase.NAME,version = AppDataBase.VERSION)
public class AppDataBase {
    public static final String NAME="HotelAppDataBase";
    public static final int VERSION=1;
}
