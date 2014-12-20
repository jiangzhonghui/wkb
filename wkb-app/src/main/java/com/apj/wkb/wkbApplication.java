package com.apj.wkb;

import android.app.Application;
import android.os.StrictMode;

/**
 * Created by lfw on 2014/12/20.
 */
public class wkbApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }
}
