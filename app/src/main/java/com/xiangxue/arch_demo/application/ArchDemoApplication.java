package com.xiangxue.arch_demo.application;

import android.app.Application;

import com.xiangxue.base.preference.PreferencesUtil;
import com.xiangxue.network.base.BaseNetworkApi;

/**
 * Created by Allen on 2017/7/20.
 * 保留所有版权，未经允许请不要分享到互联网和其他人
 */
public class ArchDemoApplication extends Application {

    private static ArchDemoApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        BaseNetworkApi.Companion.init(new NetworkRequestInfo(this));
        PreferencesUtil.init(this);
    }

    public static ArchDemoApplication getInstance() {
        return instance;
    }
}
