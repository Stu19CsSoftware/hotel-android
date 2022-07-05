package com.ranlychan.ktv;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.ranlychan.ktv.service.RoomSelectService;
import com.tencent.bugly.Bugly;

import cn.leancloud.LeanCloud;

public class KTVApplication extends Application {
    public RoomSelectService roomSelectService;

    @Override
    public void onCreate() {
        super.onCreate();
        // 提供 this、App ID、绑定的自定义 API 域名作为参数
        LeanCloud.initializeSecurely(this, "LLt4j6GcLm9G33Iz1dRSGKqB-gzGzoHsz", "https://llt4j6gc.lc-cn-n1-shared.com");
        Bugly.init(getApplicationContext(), "1b1aa78902", false);
        dbFlowInit();
        roomSelectService = RoomSelectService.getRoomSelectService();
    }

    private void dbFlowInit(){
        try {
            FlowManager.init(new FlowConfig.Builder(getApplicationContext())
                    .openDatabasesOnInit(true) .build());
            FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RoomSelectService getRoomSelectService(){
        return roomSelectService;
    }

}

