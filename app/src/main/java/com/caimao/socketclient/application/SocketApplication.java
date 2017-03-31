package com.caimao.socketclient.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by ppz on 2016/8/22.
 */
public class SocketApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
