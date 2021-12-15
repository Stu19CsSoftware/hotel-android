package com.ranlychan.hotel.entity;

public class MeOption {
    private int optId;
    private String name;
    private int iconId;

    public MeOption() {
    }

    public MeOption(int optId, String name, int iconId) {
        this.optId = optId;
        this.name = name;
        this.iconId = iconId;
    }

    public int getOptId() {
        return optId;
    }

    public void setOptId(int optId) {
        this.optId = optId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
