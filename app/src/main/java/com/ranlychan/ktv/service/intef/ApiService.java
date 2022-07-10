package com.ranlychan.ktv.service.intef;

import com.ranlychan.ktv.bean.BaseDataBean;
import com.ranlychan.ktv.bean.request.BaseTokenRequestBean;
import com.ranlychan.ktv.bean.QueryRecRoomsResponseBean;
import com.ranlychan.ktv.bean.QueryRoomDetailResponseBean;
import com.ranlychan.ktv.bean.request.RoomUserLoginPhonePswBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    /**
     * request和response都是json形式，不使用系统默认的GsonConverter，拿到response后自己用Gson解析成bean
     *
     * @POST
     * Call<ResponseBody> postWithJson(@HeaderMap Map<String, String> headers, @Body RequestBody paramBody);
     */

    /**订房模块**/

    @POST("room/queryRecRooms")
    Call<BaseDataBean<QueryRecRoomsResponseBean>> queryRecRooms(@Body BaseTokenRequestBean paramBody);

    @POST("room/queryRoomDetail")
    Call<BaseDataBean<QueryRoomDetailResponseBean>> queryRoomDetail(@Body BaseTokenRequestBean paramBody);


    /**用户模块**/
    @POST("user/roomUserLoginPhonePsw")
    Call<BaseDataBean<String>> roomUserLoginPhonePsw(@Body RoomUserLoginPhonePswBean paramBody);

}
