package com.android.desafio.desafio_android_vitor_santos.app.di;


import android.content.Context;

import com.android.desafio.desafio_android_vitor_santos.app.DesafioApp;
import com.android.desafio.desafio_android_vitor_santos.character.detail.repository.local.CharacterRepository;
import com.android.desafio.desafio_android_vitor_santos.character.detail.repository.remote.CharacterService;
import com.android.desafio.desafio_android_vitor_santos.character.list.repository.local.ListRepository;
import com.android.desafio.desafio_android_vitor_santos.character.list.repository.remote.ListService;
import com.android.desafio.desafio_android_vitor_santos.character.popular.repository.local.PopularRepository;
import com.android.desafio.desafio_android_vitor_santos.character.popular.repository.remote.PopularService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    protected static final String BASE_URL = "https://gateway.marvel.com/v1/public/";
    protected final String DATABASE_NAME = "";

    private DesafioApp app;

    public AppModule(DesafioApp app) {
        this.app = app;
    }


    @Provides
    public Context getApplicationContext() {
        return app;
    }

    @Provides
    public Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    public Retrofit provideRetrofit(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public CharacterService characterService(Retrofit retrofit) {
        return retrofit.create(CharacterService.class);
    }

    @Provides
    @Singleton
    public CharacterRepository provideCharacterRepository() {
        return new CharacterRepository(characterService(provideRetrofit(provideGson())));
    }

    @Provides
    @Singleton
    public ListService listService(Retrofit retrofit) {
        return retrofit.create(ListService.class);
    }

    @Provides
    @Singleton
    public ListRepository provideListRepository() {
        return new ListRepository(listService(provideRetrofit(provideGson())));
    }

    @Provides
    @Singleton
    public PopularService popularService(Retrofit retrofit) {
        return retrofit.create(PopularService.class);
    }

    @Provides
    @Singleton
    public PopularRepository providePopularRepository() {
        return new PopularRepository(popularService(provideRetrofit(provideGson())));
    }


}
