package com.android.desafio.desafio_android_vitor_santos.character.popular.repository.remote;

import com.android.desafio.desafio_android_vitor_santos.character.popular.model.ListPopularComic;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PopularService {

    @GET("characters/{characterId}/comics")
    Call<ListPopularComic> getMostPopularComic(
            @Path("characterId") Long characterId,
            @Query("ts") String ts,
            @Query("apikey") String apikey,
            @Query("hash") String hash,
            @Query("offset") Integer offset

    );
}
