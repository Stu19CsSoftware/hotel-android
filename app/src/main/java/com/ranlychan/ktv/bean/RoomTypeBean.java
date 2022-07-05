package com.ranlychan.ktv.bean;

import java.util.List;

public class RoomTypeBean {
    private String rtid;
    private String name;
    private String cover_img;
    private List<RoomServiceBean> service_list;
    private String full_intro;
    private String short_intro;
    private float rate_star_level;
    private float price;
    private String price_unit;
    private String created_at;

    public String getRtid() {
        return rtid;
    }

    public void setRtid(String rtid) {
        this.rtid = rtid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public List<RoomServiceBean> getService_list() {
        return service_list;
    }

    public void setService_list(List<RoomServiceBean> service_list) {
        this.service_list = service_list;
    }

    public String getFull_intro() {
        return full_intro;
    }

    public void setFull_intro(String full_intro) {
        this.full_intro = full_intro;
    }

    public String getShort_intro() {
        return short_intro;
    }

    public void setShort_intro(String short_intro) {
        this.short_intro = short_intro;
    }

    public float getRate_star_level() {
        return rate_star_level;
    }

    public void setRate_star_level(float rate_star_level) {
        this.rate_star_level = rate_star_level;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPrice_unit() {
        return price_unit;
    }

    public void setPrice_unit(String price_unit) {
        this.price_unit = price_unit;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
