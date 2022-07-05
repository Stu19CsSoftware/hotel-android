package com.ranlychan.ktv;

public class Constant {
    public static final String TFORMAT_YMD = "yyyy-MM-dd";// HH:mm:ss SSS
    public static final String MONEY_UNIT_MARK_CNY = "￥";

    public static final String APP_ID = "LLt4j6GcLm9G33Iz1dRSGKqB";
    public static final String APP_KEY = "AE7yCF8rh4sAEzEliV1c4xB6";
    public static final String BASE_URL = "https://mock.apifox.cn/m1/1206466-0-default/";

    public static final String x_LC_Id = "X-LC-Id:" + APP_ID;
    public static final String x_LC_Key = "X-LC-Key:" + APP_KEY;
    //public static final String x_Lc_Key_Master = "X-LC-Key: LeanCloud中的master Key,master";//一般不要保存在本地
    private static String curTime = String.valueOf(System.currentTimeMillis());
    //public static final String x_LC_Sign = "X-LC-Sign:" + MD5Utils.md5(curTime+APP_KEY)+","+curTime;
    //定义全局请求baseUrl

    public static String getMoneyUnitMark(){
        return MONEY_UNIT_MARK_CNY;
    }
}
