package com.ranlychan.hotel.service;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.ranlychan.hotel.entity.User;
import com.ranlychan.hotel.entity.User_Table;
import com.ranlychan.hotel.listener.OnUserInfoOperateListener;

import java.util.HashMap;

public class UserInfoService {
    private static UserInfoService userInfoServiceInstance;
    //private OnUserInfoOperateListener onUserInfoOperateListener;

    private UserInfoService(){

    }

    public static UserInfoService getUserInfoServiceInstance() {
        synchronized (UserInfoService.class) {
            if (userInfoServiceInstance == null) {
                userInfoServiceInstance = new UserInfoService();
            }
        }
        return userInfoServiceInstance;
    }

    public void updateUserInfo(HashMap<String,Object> param,OnUserInfoOperateListener onUserInfoOperateListener){
        try{
            User user = SQLite.select()
                    .from(User.class)
                    .where(User_Table.userObjId.eq((String)param.get("userObjId")))
                    .querySingle();

            user.setUserNickName((String)param.get("userNickName"));
            user.setPassword((String)param.get("password"));
            user.setEmail((String)param.get("email"));
            user.setUserName((String)param.get("userName"));
            user.setEmailVerified((Boolean) param.get("emailVerified"));

            if(user.update()){
                onUserInfoOperateListener.onQueryUserComplete(user);
            }else{
                throw new Exception("UserInfoUpdateFail!");
            }
        }catch (Throwable throwable){
            onUserInfoOperateListener.onQueryUserError(throwable);
        }
    }

    public void queryLocalUser(OnUserInfoOperateListener onUserInfoOperateListener){
        if(onUserInfoOperateListener != null){
            try {
                onUserInfoOperateListener.onQueryUserComplete(SQLite.select()
                        .from(User.class)
                        .querySingle());

            }catch (Throwable throwable){
                onUserInfoOperateListener.onQueryUserError(throwable);
            }

        }
    }

    public void addUser(HashMap<String,Object> param,OnUserInfoOperateListener onUserInfoOperateListener){
        try{
            User user = new User();

            user.setUserNickName((String)param.get("userNickName"));
            user.setPassword((String)param.get("password"));
            user.setEmail((String)param.get("email"));
            user.setUserName((String)param.get("userName"));
            user.setEmailVerified((Boolean) param.get("emailVerified"));

            if(user.save()){
                onUserInfoOperateListener.onQueryUserComplete(user);
            }else{
                throw new Exception("UserInfoUpdateFail!");
            }
        }catch (Throwable throwable){
            onUserInfoOperateListener.onQueryUserError(throwable);
        }
    }

    public void removeUser(HashMap<String,Object> param,OnUserInfoOperateListener onUserInfoOperateListener){
        try{
            User user = SQLite.select()
                    .from(User.class)
                    .where(User_Table.userObjId.eq((String)param.get("userObjId")))
                    .querySingle();

            if(user.delete()){
                onUserInfoOperateListener.onQueryUserComplete(user);
            }else{
                throw new Exception("UserInfoDeleteFail!");
            }
        }catch (Throwable throwable){
            onUserInfoOperateListener.onQueryUserError(throwable);
        }
    }

    /*
    public void setOnUserInfoOperateListener(OnUserInfoOperateListener onUserInfoOperateListener) {
        this.onUserInfoOperateListener = onUserInfoOperateListener;
    }

     */
}
