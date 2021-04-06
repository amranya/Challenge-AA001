package com.fab.challengeaa001;

import android.app.Application;

import com.fab.challengeaa001.di.AppComponent;
import com.fab.challengeaa001.di.DaggerAppComponent;

public class CurrencyExchangeApplication extends Application {

    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }
}
