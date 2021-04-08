package com.fab.challengeaa001;

import android.app.Application;
import android.content.Context;

import com.fab.challengeaa001.di.AppComponent;
import com.fab.challengeaa001.di.DaggerAppComponent;

public class CurrencyExchangeApplication extends Application {

    private AppComponent appComponent;
    private static Context appContext;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
        if (appContext != null) appContext = null;
        appContext = this.getApplicationContext();
    }

    public static Context getAppContext() {
        return appContext;
    }
}
