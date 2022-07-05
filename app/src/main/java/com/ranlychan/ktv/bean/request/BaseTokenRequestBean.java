package com.ranlychan.ktv.bean.request;

public class BaseTokenRequestBean {
    private String session_token;

    public BaseTokenRequestBean(String session_token) {
        this.session_token = session_token;
    }

    public String getSession_token() {
        return session_token;
    }

    public void setSession_token(String session_token) {
        this.session_token = session_token;
    }
}
