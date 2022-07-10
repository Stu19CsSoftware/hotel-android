package com.ranlychan.ktv.service.impl;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.ranlychan.ktv.entity.User;
import com.ranlychan.ktv.listener.OnUserInfoOperateListener;
import com.ranlychan.ktv.olley.BaseJsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;

public class LoginService {

    private RequestQueue requestQueue;
    private static LoginService loginService;
    private static final String url = "https://llt4j6gc.lc-cn-n1-shared.com/1.1/login";
    private UserInfoService userInfoService;

    private LoginService(Context applicationContext){
        requestQueue = Volley.newRequestQueue(applicationContext);
        userInfoService = UserInfoService.getUserInfoServiceInstance();
    }

    public static LoginService getLoginService(Context applicationContext) {
        synchronized (RoomSelectService.class) {
            if (loginService == null) {
                loginService = new LoginService(applicationContext);
            }
        }
        return loginService;
    }

    /*
    public void login(LinkedHashMap<String,String> param, OnLoginListener listener){
        OkHttps.async(Constant.baseUrl)
                .setOnResponse(res -> {
                    if(res.isSuccessful()){
                        try {
                            User user = new User();
                            JSONObject rootObject = new JSONObject();
                            user.setEmail(rootObject.getString("email"));
                            //user.setPassword(rootObject.getString("sessionToken"));
                            user.setUserObjId(rootObject.getString("objectId"));
                            user.setNickName(rootObject.getString("nickname"));
                            user.setUserName(rootObject.getString("username"));
                            user.setEmailVerified(rootObject.getBoolean("emailVerified"));
                            user.setMobilePhoneNumber(rootObject.getString("mobilePhoneNumber"));
                            user.setMobilePhoneVerified(rootObject.getBoolean("mobilePhoneVerified"));

                            if(userInfoService.isUserInfoExist(user.getUserObjId())){
                                userInfoService.updateUserInfo(user, new OnUserInfoOperateListener() {
                                    @Override
                                    public void onQueryUserComplete(User user) {
                                        listener.onLoginComplete(null);
                                    }

                                    @Override
                                    public void onQueryUserError(Throwable throwable) {
                                        listener.onLoginError(throwable);
                                    }
                                });
                            }

                        } catch (JSONException e) {
                            listener.onLoginError(e);
                            e.printStackTrace();
                        }
                        listener.onLoginComplete(null);
                    }else {
                        listener.onLoginError(res.getError());
                    }
                    //User user = res.getBody().toBean(User.class);
                })
                .addBodyPara(param)
                .addHeader("X-LC-Id", "LLt4j6GcLm9G33Iz1dRSGKqB-gzGzoHsz")
                .addHeader("X-LC-Key", "AE7yCF8rh4sAEzEliV1c4xB6")
                .addHeader("Content-Type", "application/json")
                .addHeader("Host", "llt4j6gc.lc-cn-n1-shared.com")
                .post();

    }

     */


    public void login(LinkedHashMap<String,String> param, OnLoginListener listener){
        try{
            JSONObject jsonObject = new JSONObject(param);

            Log.d("LOGIN",jsonObject.toString());

            BaseJsonObjectRequest jsonObjReq = new BaseJsonObjectRequest(Request.Method.POST,
                    url, jsonObject,
                    new Response.Listener() {
                        @Override
                        public void onResponse(Object response) {
                            try {
                                User user = new User();
                                JSONObject rootObject = (JSONObject)response;
                                user.setEmail(rootObject.getString("email"));
                                //user.setPassword(rootObject.getString("sessionToken"));
                                user.setUserObjId(rootObject.getString("objectId"));
                                user.setNickName(rootObject.getString("nickname"));
                                user.setUserName(rootObject.getString("username"));
                                user.setEmailVerified(rootObject.getBoolean("emailVerified"));
                                user.setMobilePhoneNumber(rootObject.getString("mobilePhoneNumber"));
                                user.setMobilePhoneVerified(rootObject.getBoolean("mobilePhoneVerified"));

                                if(userInfoService.isUserInfoExist(user.getUserObjId())){
                                    userInfoService.updateUserInfo(user, new OnUserInfoOperateListener() {
                                        @Override
                                        public void onQueryUserComplete(User user) {
                                            listener.onLoginComplete(null);
                                        }

                                        @Override
                                        public void onQueryUserError(Throwable throwable) {
                                            listener.onLoginError(throwable);
                                        }
                                    });
                                }

                            } catch (JSONException e) {
                                listener.onLoginError(e);
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    listener.onLoginError(error);
                }
            });
            Log.d("ranlychan","body"+new String(jsonObjReq.getBody()));
            requestQueue.add(jsonObjReq);

        }catch (Throwable throwable){
            listener.onLoginError(throwable);
        }
    }

    public void loginOut(){

    }

    public interface OnLoginListener{
        void onLoginComplete(Object msg);
        void onLoginError(Throwable throwable);
    }
}
