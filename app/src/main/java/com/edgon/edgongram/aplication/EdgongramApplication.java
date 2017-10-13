package com.edgon.edgongram.aplication;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by gonza on 8/12/2017.
 */

public class EdgongramApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
