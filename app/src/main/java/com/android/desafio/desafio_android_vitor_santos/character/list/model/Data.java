package com.android.desafio.desafio_android_vitor_santos.character.list.model;

import java.util.ArrayList;

public class Data {

    private Integer offset;
    private Integer limit;
    private Integer total;
    private Integer count;
    private ArrayList<Results> results;



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

    public ArrayList<Results> getResult() {
        return results;
    }

    public void setResult(ArrayList<Results> results) {
        this.results = results;
    }




}
