package com.android.desafio.desafio_android_vitor_santos.character.popular.model;

import com.android.desafio.desafio_android_vitor_santos.character.list.model.Thumbnail;

import java.util.ArrayList;

public class PopularComic {

    private Long digitalId;
    private String title;
    private String description;
    private Thumbnail thumbnail;
    private ArrayList<Prices> prices;


    public ArrayList<Prices> getPrices() {
        return prices;
    }

    public void setPrices(ArrayList<Prices> prices) {
        this.prices = prices;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Long getDigitalId() {
        return digitalId;
    }

    public void setDigitalId(Long id) {
        this.digitalId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
