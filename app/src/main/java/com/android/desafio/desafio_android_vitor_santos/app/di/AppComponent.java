package com.android.desafio.desafio_android_vitor_santos.app.di;

import com.android.desafio.desafio_android_vitor_santos.character.list.viewmodel.ListViewModel;
import com.android.desafio.desafio_android_vitor_santos.character.popular.viewmodel.PopularViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(ListViewModel viewModel);
    void inject(PopularViewModel viewModel);

    interface Injectable {
        void inject(AppComponent component);
    }
}
