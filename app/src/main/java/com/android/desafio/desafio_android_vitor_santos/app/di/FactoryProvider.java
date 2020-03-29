package com.android.desafio.desafio_android_vitor_santos.app.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.android.desafio.desafio_android_vitor_santos.app.DesafioApp;

public class FactoryProvider extends ViewModelProvider.NewInstanceFactory {
    private DesafioApp app;

    public FactoryProvider(DesafioApp app) {
        this.app = app;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        T t = super.create(modelClass);
        if (t instanceof AppComponent.Injectable) {
            ((AppComponent.Injectable) t).inject(app.getAppComponent());
        }
        return t;
    }
}