package com.android.desafio.desafio_android_vitor_santos.character.list.adapter;

import com.android.desafio.desafio_android_vitor_santos.character.list.model.ListCharacter;

public interface ListAdapterListener {
    void onLoadMore();
    void onCustomClick(ListCharacter id, ListCharacter name, String picture, String description);
}
