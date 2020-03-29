package com.android.desafio.desafio_android_vitor_santos.character.popular.model;

import java.util.ArrayList;

public class DataPopular {

    private Integer offset;
    private Integer limit;
    private Integer total;
    private Integer count;
    private ArrayList<PopularComic> results;



    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }


    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ArrayList<PopularComic> getResult() {
        return results;
    }

    public void setResult(ArrayList<PopularComic> results) {
        this.results = results;
    }

}
