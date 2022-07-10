package com.ranlychan.ktv.service.impl;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.ranlychan.ktv.entity.User;
import com.ranlychan.ktv.entity.User_Table;
import com.ranlychan.ktv.listener.OnUserInfoOperateListener;

import java.util.HashMap;

public class UserInfoService {
    private static UserInfoService userInfoServiceInstance;
    //private OnUserInfoOperateListener onUserInfoOperateListener;

    private UserInfoService(){

    }

    public void login(){

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

            user.setNickName((String)param.get("userNickName"));
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

    public void updateUserInfo(User newUser,OnUserInfoOperateListener onUserInfoOperateListener){
        try{
            User user = SQLite.select()
                    .from(User.class)
                    .where(User_Table.userObjId.eq((String)newUser.getUserObjId()))
                    .querySingle();

            user.setUserName(newUser.getUserName());
            user.setNickName(newUser.getNickName());
            user.setPassword(newUser.getPassword());
            user.setEmail(newUser.getEmail());
            user.setEmailVerified(newUser.isEmailVerified());
            user.setMobilePhoneNumber(newUser.getMobilePhoneNumber());
            user.setMobilePhoneVerified(newUser.isMobilePhoneVerified());

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

    public boolean isUserInfoExist(String userObjId){
        User user = SQLite.select()
                .from(User.class)
                .querySingle();

        if(user != null){
            if(user.getUserObjId() == userObjId){
                return true;
            }

            if(user.delete()){
                return false;
            }
        }
        return false;
    }
    public void addUser(HashMap<String,Object> param,OnUserInfoOperateListener onUserInfoOperateListener){
        try{
            User user = new User();

            user.setNickName((String)param.get("userNickName"));
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
