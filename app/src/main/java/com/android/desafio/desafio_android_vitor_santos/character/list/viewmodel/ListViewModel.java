package com.android.desafio.desafio_android_vitor_santos.character.list.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.android.desafio.desafio_android_vitor_santos.app.base.DesafioView;
import com.android.desafio.desafio_android_vitor_santos.app.base.Presentation;
import com.android.desafio.desafio_android_vitor_santos.app.di.AppComponent;
import com.android.desafio.desafio_android_vitor_santos.character.list.model.ListCharacter;
import com.android.desafio.desafio_android_vitor_santos.character.list.repository.local.ListRepository;

import javax.inject.Inject;

public class ListViewModel extends ViewModel implements AppComponent.Injectable, Presentation {

    @Inject
    ListRepository listRepository;

    private DesafioView view;

    @Override
    public void inject(AppComponent component) {
        component.inject(this);
    }


    public LiveData<ListCharacter> getListCharacters(String ts, String apikey, String hash, Integer offset) {
//        view.showLoad(false);
        return listRepository.getListCharacters(ts, apikey, hash, offset);
    }


    public void init(DesafioView view) {
        this.view = view;
        listRepository.setPresentation(this);
    }

    @Override
    public void actionSuccess() {
        view.hideLoad(false);
        view.navigate(null);
    }

    @Override
    public void actionError() {
        view.hideLoad(false);
        view.showError("Falaha ao conectar com a internet!");
    }


}
