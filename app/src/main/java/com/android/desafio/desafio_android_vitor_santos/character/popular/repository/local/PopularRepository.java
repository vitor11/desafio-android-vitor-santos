package com.android.desafio.desafio_android_vitor_santos.character.popular.repository.local;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.android.desafio.desafio_android_vitor_santos.app.base.Presentation;
import com.android.desafio.desafio_android_vitor_santos.character.popular.model.ListPopularComic;
import com.android.desafio.desafio_android_vitor_santos.character.popular.repository.remote.PopularService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularRepository {

    @Inject
    PopularService popularService;

    private MutableLiveData<ListPopularComic> mostPopularComic = new MutableLiveData();
    private Presentation presentation;


    public PopularRepository(PopularService popularService) {
        this.popularService = popularService;
    }

    public LiveData<ListPopularComic> getMostPopularComic(Long characterId, String ts, String apikey, String hash, int offset) {
        popularService.getMostPopularComic(characterId, ts, apikey, hash, offset).enqueue(new Callback<ListPopularComic>() {
            @Override
            public void onResponse(Call<ListPopularComic> call, Response<ListPopularComic> response) {

                if (response.isSuccessful()) {
                    mostPopularComic.setValue(response.body());
                    presentation.actionSuccess();
                } else {
                    presentation.actionError();
                }
            }

            @Override
            public void onFailure(Call<ListPopularComic> call, Throwable t) {
                presentation.actionError();
            }
        });

        return mostPopularComic;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }
}
