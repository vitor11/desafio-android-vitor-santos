package com.android.desafio.desafio_android_vitor_santos.character.popular.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.android.desafio.desafio_android_vitor_santos.app.base.DesafioView;
import com.android.desafio.desafio_android_vitor_santos.app.base.Presentation;
import com.android.desafio.desafio_android_vitor_santos.app.di.AppComponent;
import com.android.desafio.desafio_android_vitor_santos.character.popular.model.ListPopularComic;
import com.android.desafio.desafio_android_vitor_santos.character.popular.repository.local.PopularRepository;

import javax.inject.Inject;

public class PopularViewModel extends ViewModel implements AppComponent.Injectable, Presentation {

    @Inject
    PopularRepository populartRepository;

    private DesafioView view;

    @Override
    public void inject(AppComponent component) {
        component.inject(this);
    }

    public LiveData<ListPopularComic> getMostPopularComic(Long characterId, String ts, String apikey, String hash, int offset) {
        view.showLoad(true);
        return populartRepository.getMostPopularComic(characterId, ts, apikey, hash, offset);
    }


    public void init(DesafioView view) {
        this.view = view;
        populartRepository.setPresentation(this);
        view.showEmpty();
    }

    @Override
    public void actionSuccess() {
        view.hideLoad(true);
        view.navigate(null);
    }

    @Override
    public void actionError() {
        view.hideLoad(true);
        view.showError("Falaha ao conectar com a internet!");
    }

}
