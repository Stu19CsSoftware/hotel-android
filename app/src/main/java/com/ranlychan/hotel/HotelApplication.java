package com.ranlychan.hotel;

import android.app.Application;

import cn.leancloud.LeanCloud;

public class HotelApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 提供 this、App ID、绑定的自定义 API 域名作为参数
        LeanCloud.initializeSecurely(this, "LLt4j6GcLm9G33Iz1dRSGKqB-gzGzoHsz", "https://llt4j6gc.lc-cn-n1-shared.com");
    }
}

