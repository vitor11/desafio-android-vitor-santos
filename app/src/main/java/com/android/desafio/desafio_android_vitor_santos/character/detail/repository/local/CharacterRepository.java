package com.android.desafio.desafio_android_vitor_santos.character.detail.repository.local;

import com.android.desafio.desafio_android_vitor_santos.app.base.Presentation;
import com.android.desafio.desafio_android_vitor_santos.character.detail.repository.remote.CharacterService;

import javax.inject.Inject;

public class CharacterRepository {

    @Inject
    CharacterService characterService;

    private Presentation presentation;


    public CharacterRepository(CharacterService historicService) {
        this.characterService = historicService;
    }
}
