package com.android.desafio.desafio_android_vitor_santos.character.list.repository.remote;

import com.android.desafio.desafio_android_vitor_santos.character.list.model.ListCharacter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ListService {
    @GET("characters")
    Call<ListCharacter> getListCharacters(
            @Query("ts") String ts,
            @Query("apikey") String apikey,
            @Query("hash") String hash,
            @Query("offset") Integer offset
            );
}
