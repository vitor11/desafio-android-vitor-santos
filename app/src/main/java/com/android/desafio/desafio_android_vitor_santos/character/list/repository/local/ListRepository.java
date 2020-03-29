package com.android.desafio.desafio_android_vitor_santos.character.list.repository.local;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.android.desafio.desafio_android_vitor_santos.app.base.Presentation;
import com.android.desafio.desafio_android_vitor_santos.character.list.model.ListCharacter;
import com.android.desafio.desafio_android_vitor_santos.character.list.repository.remote.ListService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListRepository {

    @Inject
    ListService listService;

    private MutableLiveData<ListCharacter> listCharacters = new MutableLiveData();
    private Presentation presentation;


    public ListRepository(ListService listService) {
        this.listService = listService;
    }

    public LiveData<ListCharacter> getListCharacters(String ts, String apikey, String hash, Integer offset) {
        listService.getListCharacters(ts, apikey, hash, offset).enqueue(new Callback<ListCharacter>() {
            @Override
            public void onResponse(Call<ListCharacter> call, Response<ListCharacter> response) {

                if (response.isSuccessful()) {
                    if(listCharacters.getValue() != null)
                        listCharacters.setValue(null);

                    listCharacters.setValue(response.body());
//                    presentation.actionSuccess();
                } else {
                    presentation.actionError();
                }
            }

            @Override
            public void onFailure(Call<ListCharacter> call, Throwable t) {
                presentation.actionError();
            }
        });

        return listCharacters;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

}
