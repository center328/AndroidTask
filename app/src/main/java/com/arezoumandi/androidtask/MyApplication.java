package com.arezoumandi.androidtask;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created by Ashraful on 11/4/2015.
 */
public class MyApplication extends MultiDexApplication {

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this); // install multidex
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
