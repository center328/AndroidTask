package com.arezoumandi.androidtask;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.arezoumandi.androidtask.db.AppDatabase;
import com.arezoumandi.androidtask.db.repository.AppExecutors;
import com.arezoumandi.androidtask.db.repository.DataRepository;

/**
 * Created by Ashraful on 11/4/2015.
 */
public class MyApplication extends MultiDexApplication {

    private static AppExecutors mAppExecutors;

    public static AppDatabase getDatabase(Context mContext) {
        return AppDatabase.getInstance(mContext.getApplicationContext(), mAppExecutors);
    }

    public static DataRepository getRepository(Context mContext) {
        return DataRepository.getInstance(getDatabase(mContext.getApplicationContext()));
    }

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this); // install multidex

        mAppExecutors = new AppExecutors();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
