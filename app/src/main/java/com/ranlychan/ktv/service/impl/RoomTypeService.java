package com.ranlychan.ktv.service.impl;

import android.util.Log;

import com.ranlychan.ktv.bean.BaseDataBean;
import com.ranlychan.ktv.bean.request.BaseTokenRequestBean;
import com.ranlychan.ktv.bean.QueryRecRoomsResponseBean;
import com.ranlychan.ktv.bean.QueryRoomDetailResponseBean;
import com.ranlychan.ktv.bean.RoomTypeBean;
import com.ranlychan.ktv.bean.request.QueryRoomTypeDetailRequestBean;
import com.ranlychan.ktv.entity.RoomType;
import com.ranlychan.ktv.listener.OnQueryRoomTypeListListener;
import com.ranlychan.ktv.listener.OnResponseListener;
import com.ranlychan.ktv.service.intef.ApiService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.leancloud.LCObject;
import cn.leancloud.LCQuery;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*房间类型管理业务逻辑层*/
public class RoomTypeService {

    /* 查询房间类型 */
    public void queryRoomTypes(OnQueryRoomTypeListListener listener){
//        List<RoomType> roomTypeList = new ArrayList<RoomType>();
//
//        LCQuery<LCObject> query = new LCQuery<>("RoomTpye");//LeanCloud里面打错了不是RoomType。。。
//        query.whereGreaterThan("availableRoomNum", 0);//查询条件
//        query.findInBackground().subscribe(new Observer<List<LCObject>>() {
//            public void onSubscribe(Disposable disposable) {}
//            public void onNext(List<LCObject> list) {
//                for(int i=0;i<list.size();i++){
//                    try{
//                        RoomType roomType = new RoomType();
//                        roomType.setObjectId(list.get(i).getString("objectId"));
//                        roomType.setName(list.get(i).getString("name"));
//                        roomType.setPrice(list.get(i).getNumber("price").floatValue());
//                        roomType.setAvailableRoomNum(list.get(i).getNumber("availableRoomNum").intValue());
//                        roomType.setCoverImgUrl(list.get(i).getString("coverImgUrl"));
//                        //roomType.setBednumber(list.get(i).getNumber("Bednumber").intValue());
//                        roomType.setDeposit(list.get(i).getNumber("deposit").floatValue());
//                        //roomType.setWashroom(list.get(i).getBoolean("Washroom"));
//                        //roomType.setAircondition(list.get(i).getBoolean("Aircondition"));
//                        //roomType.setTelephone(list.get(i).getBoolean("Telephone"));
//                        //roomType.setTv(list.get(i).getBoolean("Tv"));
//                        //roomType.setArea(list.get(i).getNumber("Area").floatValue());
//                        roomType.setIntro(list.get(i).getString("intro"));
//                        roomType.setShortIntro(list.get(i).getString("shortIntro"));
//                        //roomType.setCreatedAt(list.get(i).getDate("createdAt"));
//                        //roomType.setUpdatedAt(list.get(i).getDate("updatedAt"));
//                        roomTypeList.add(roomType);
//                    }catch (Throwable throwable){
//                        listener.onQueryError(throwable);
//                    }
//
//                }
//            }
//            public void onError(Throwable throwable) {
//                listener.onQueryError(throwable);
//                throwable.printStackTrace();
//            }
//            public void onComplete() {
//                listener.onQueryComplete(roomTypeList);
//            }
//        });

        ApiService sApiService = ServiceGenerator.createService(ApiService.class);

        Call<BaseDataBean<QueryRecRoomsResponseBean>> call = sApiService.queryRecRooms(new BaseTokenRequestBean("SAMPLE_TOKEN"));
        call.enqueue(new Callback<BaseDataBean<QueryRecRoomsResponseBean>>() {
            @Override
            public void onResponse(Call<BaseDataBean<QueryRecRoomsResponseBean>> call, Response<BaseDataBean<QueryRecRoomsResponseBean>> response) {
                listener.onQueryComplete(response.body().getData().getsRoomTypeBeanList());
                Log.d("RRRR","success");
            }

            @Override
            public void onFailure(Call<BaseDataBean<QueryRecRoomsResponseBean>> call, Throwable t) {
                listener.onQueryError(t);
                Log.d("RRRR", "Error" + t.toString());
            }
        });
    }

    /* 查询房间类型 */
    public void queryRoomTypes(HashMap<String,Object> param, OnQueryRoomTypeListListener listener){
        ApiService sApiService = ServiceGenerator.createService(ApiService.class);

        Call<BaseDataBean<QueryRecRoomsResponseBean>> call = sApiService.queryRecRooms(new BaseTokenRequestBean("SAMPLE_TOKEN"));
        call.enqueue(new Callback<BaseDataBean<QueryRecRoomsResponseBean>>() {
            @Override
            public void onResponse(Call<BaseDataBean<QueryRecRoomsResponseBean>> call, Response<BaseDataBean<QueryRecRoomsResponseBean>> response) {
                listener.onQueryComplete(response.body().getData().getsRoomTypeBeanList());
                Log.d("RRRR","success");
            }

            @Override
            public void onFailure(Call<BaseDataBean<QueryRecRoomsResponseBean>> call, Throwable t) {
                listener.onQueryError(t);
                Log.d("RRRR", "Error" + t.toString());
            }
        });

        List<RoomType> roomTypeList = new ArrayList<RoomType>();
        try{


            /*查询符合时间要求的房型*/
            //LCQuery<LCObject> innerQueryStart = new LCQuery<>("RoomOccupationState");
            //innerQueryStart.whereGreaterThanOrEqualTo("startDate",(Date)param.get("startDate"));

            //LCQuery<LCObject> innerQueryEnd = new LCQuery<>("RoomOccupationState");
            //innerQueryEnd.whereLessThanOrEqualTo("endDate",(Date)param.get("endDate"));

            //LCQuery<LCObject> innerQuery = LCQuery.and(Arrays.asList(innerQueryStart, innerQueryEnd));

            /*符合日期要求的RoomType继续细化，查两个条件*/
            //LCQuery<LCObject> dateQuery = new LCQuery<>("RoomTpye");
            //dateQuery.whereDoesNotMatchQuery("objectId", innerQuery);



        }catch (Throwable throwable){
            throwable.printStackTrace();
            //listener.onQueryError(throwable);
        }

        int number = (int)param.get("availableRoomNum");
        int guestNumber = (int)param.get("availableGuestNum");

        Log.d("queryRoomTypes","roomNumber:"+number+"  guestNumber:"+guestNumber);


        /*查询符合时间要求的房型*/
        LCQuery<LCObject> innerQueryStart = new LCQuery<>("RoomOccupationState");
        innerQueryStart.whereGreaterThanOrEqualTo("startDate",(Date)param.get("startDate"));

        LCQuery<LCObject> innerQueryEnd = new LCQuery<>("RoomOccupationState");
        innerQueryEnd.whereLessThanOrEqualTo("endDate",(Date)param.get("endDate"));

        LCQuery<LCObject> innerQuery1 = LCQuery.and(Arrays.asList(innerQueryStart, innerQueryEnd));

        LCQuery<LCObject> roomNumQuery = new LCQuery<>("RoomTpye");
        roomNumQuery.whereGreaterThanOrEqualTo("availableRoomNum", number);

        LCQuery<LCObject> guestNumQuery = new LCQuery<>("RoomTpye");
        guestNumQuery.whereGreaterThanOrEqualTo("availableGuestNum",guestNumber);

        LCQuery<LCObject> innerQuery = LCQuery.and(Arrays.asList(roomNumQuery, guestNumQuery));

        innerQuery.findInBackground().subscribe(new Observer<List<LCObject>>() {
            public void onSubscribe(Disposable disposable) {}
            public void onNext(List<LCObject> list) {
                for(int i=0;i<list.size();i++){
                    try{
                        RoomType roomType = new RoomType();
                        roomType.setObjectId(list.get(i).getString("objectId"));
                        roomType.setName(list.get(i).getString("name"));
                        roomType.setPrice(list.get(i).getNumber("price").floatValue());
                        roomType.setAvailableRoomNum(list.get(i).getNumber("availableRoomNum").intValue());
                        roomType.setCoverImgUrl(list.get(i).getString("coverImgUrl"));
                        roomType.setDeposit(list.get(i).getNumber("deposit").floatValue());
                        roomType.setIntro(list.get(i).getString("intro"));
                        roomType.setShortIntro(list.get(i).getString("shortIntro"));
                        roomTypeList.add(roomType);
                    }catch (Throwable throwable){
                        listener.onQueryError(throwable);
                    }

                }
            }
            public void onError(Throwable throwable) {
                listener.onQueryError(throwable);
                throwable.printStackTrace();
            }
            public void onComplete() {
                //listener.onQueryComplete(roomTypeList);
            }
        });


        /*
        List<RoomType> roomTypeList = new ArrayList<RoomType>();

        LCQuery<LCObject> query = new LCQuery<>("RoomTpye");//LeanCloud里面打错了不是RoomType。。。
        query.whereContainedIn("objectId",dateAvailableRoomTypeIds);
        query.whereGreaterThan("availableRoomNum", (int)param.get("availableRoomNum"));//查询条件
        query.whereGreaterThan("availableGuestNum",(int)param.get("availableGuestNum"));

        //差一个日期的查询条件，需要重点实现


        query.findInBackground().subscribe(new Observer<List<LCObject>>() {
            public void onSubscribe(Disposable disposable) {}
            public void onNext(List<LCObject> list) {
                for(int i=0;i<list.size();i++){
                    try{
                        RoomType roomType = new RoomType();
                        roomType.setObjectId(list.get(i).getString("objectId"));
                        roomType.setName(list.get(i).getString("name"));
                        roomType.setPrice(list.get(i).getNumber("price").floatValue());
                        roomType.setAvailableRoomNum(list.get(i).getNumber("availableRoomNum").intValue());
                        roomType.setCoverImgUrl(list.get(i).getString("coverImgUrl"));
                        //roomType.setBednumber(list.get(i).getNumber("Bednumber").intValue());
                        roomType.setDeposit(list.get(i).getNumber("deposit").floatValue());
                        //roomType.setWashroom(list.get(i).getBoolean("Washroom"));
                        //roomType.setAircondition(list.get(i).getBoolean("Aircondition"));
                        //roomType.setTelephone(list.get(i).getBoolean("Telephone"));
                        //roomType.setTv(list.get(i).getBoolean("Tv"));
                        //roomType.setArea(list.get(i).getNumber("Area").floatValue());
                        roomType.setIntro(list.get(i).getString("intro"));
                        roomType.setShortIntro(list.get(i).getString("shortIntro"));
                        //roomType.setCreatedAt(list.get(i).getDate("createdAt"));
                        //roomType.setUpdatedAt(list.get(i).getDate("updatedAt"));
                        roomTypeList.add(roomType);
                    }catch (Throwable throwable){
                        listener.onQueryError(throwable);
                    }

                }
            }
            public void onError(Throwable throwable) {
                listener.onQueryError(throwable);
                throwable.printStackTrace();
            }
            public void onComplete() {
                listener.onQueryComplete(roomTypeList);
            }
        });

         */

    }

    public void queryRoomDetail(OnResponseListener<RoomTypeBean> listener){
        ApiService sApiService = ServiceGenerator.createService(ApiService.class);

        Call<BaseDataBean<QueryRoomDetailResponseBean>> call = sApiService.queryRoomDetail(new QueryRoomTypeDetailRequestBean("SAMPLE_TOKEN","1"));
        call.enqueue(new Callback<BaseDataBean<QueryRoomDetailResponseBean>>() {
            @Override
            public void onResponse(Call<BaseDataBean<QueryRoomDetailResponseBean>> call, Response<BaseDataBean<QueryRoomDetailResponseBean>> response) {
                listener.onQueryComplete(response.body().getData().getRoomtype());
                Log.d("RRRR","success");
            }

            @Override
            public void onFailure(Call<BaseDataBean<QueryRoomDetailResponseBean>> call, Throwable t) {
                listener.onQueryError(t);
                Log.d("RRRR", "Error" + t.toString());
            }
        });
    }

    /* 根据记录编号获取房间类型对象*/
    public void getRoomType(String roomTypeObjId, OnResponseListener<RoomTypeBean> listener)  {
        ApiService sApiService = ServiceGenerator.createService(ApiService.class);

        Call<BaseDataBean<QueryRoomDetailResponseBean>> call = sApiService.queryRoomDetail(new QueryRoomTypeDetailRequestBean("SAMPLE_TOKEN","1"));
        call.enqueue(new Callback<BaseDataBean<QueryRoomDetailResponseBean>>() {
            @Override
            public void onResponse(Call<BaseDataBean<QueryRoomDetailResponseBean>> call, Response<BaseDataBean<QueryRoomDetailResponseBean>> response) {
                listener.onQueryComplete(response.body().getData().getRoomtype());
                Log.d("RRRR","success");
            }

            @Override
            public void onFailure(Call<BaseDataBean<QueryRoomDetailResponseBean>> call, Throwable t) {
                listener.onQueryError(t);
                Log.d("RRRR", "Error" + t.toString());
            }
        });
        /*
        LCQuery<LCObject> query = new LCQuery<>("RoomTpye");//LeanCloud里面打错了不是RoomType。。。
        query.whereEqualTo("objectId",roomTypeObjId);
        query.getFirstInBackground().subscribe(new Observer<LCObject>() {
            RoomType roomType = new RoomType();

            public void onSubscribe(Disposable disposable) {}

            @Override
            public void onNext(@NonNull LCObject lcObject) {
                try{
                    roomType = new RoomType();
                    roomType.setObjectId(lcObject.getString("objectId"));
                    roomType.setName(lcObject.getString("name"));
                    roomType.setPrice(lcObject.getNumber("price").floatValue());
                    roomType.setAvailableRoomNum(lcObject.getNumber("availableRoomNum").intValue());
                    roomType.setCoverImgUrl(lcObject.getString("coverImgUrl"));
                    //roomType.setBednumber(lcObject.getNumber("Bednumber").intValue());
                    roomType.setDeposit(lcObject.getNumber("deposit").floatValue());
                    //roomType.setWashroom(lcObject.getBoolean("Washroom"));
                    //roomType.setAircondition(lcObject.getBoolean("Aircondition"));
                    //roomType.setTelephone(lcObject.getBoolean("Telephone"));
                    //roomType.setTv(lcObject.getBoolean("Tv"));
                    //roomType.setArea(lcObject.getNumber("Area").floatValue());
                    roomType.setIntro(lcObject.getString("intro"));
                    roomType.setShortIntro(lcObject.getString("shortIntro"));
                    //roomType.setCreatedAt(lcObject.getDate("createdAt"));
                    //roomType.setUpdatedAt(lcObject.getDate("updatedAt"));
                }catch (Throwable throwable){
                    listener.onQueryError(throwable);
                }
            }

            public void onError(Throwable throwable) {
                listener.onQueryError(throwable);
                throwable.printStackTrace();
            }
            public void onComplete() {
                listener.onQueryComplete(roomType);
            }
        });

         */


    }

}
