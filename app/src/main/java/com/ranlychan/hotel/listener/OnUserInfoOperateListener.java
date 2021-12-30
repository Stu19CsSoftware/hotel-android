package com.ranlychan.hotel.listener;

import com.ranlychan.hotel.entity.User;

public interface OnUserInfoOperateListener {
    void onQueryUserComplete(User user);
    void onQueryUserError(Throwable throwable);

}
