package com.ranlychan.ktv.listener;

import com.ranlychan.ktv.entity.User;

public interface OnUserInfoOperateListener {
    void onQueryUserComplete(User user);
    void onQueryUserError(Throwable throwable);

}
