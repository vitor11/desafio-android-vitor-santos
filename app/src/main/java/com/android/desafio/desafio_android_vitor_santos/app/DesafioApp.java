package com.android.desafio.desafio_android_vitor_santos.app;

import android.app.Application;

import com.android.desafio.desafio_android_vitor_santos.app.di.AppComponent;
import com.android.desafio.desafio_android_vitor_santos.app.di.AppModule;
import com.android.desafio.desafio_android_vitor_santos.app.di.DaggerAppComponent;

public class DesafioApp extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.init();
    }

    private void init() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


}
