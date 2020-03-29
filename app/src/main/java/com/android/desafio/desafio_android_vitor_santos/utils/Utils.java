package com.android.desafio.desafio_android_vitor_santos.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.desafio.desafio_android_vitor_santos.character.list.model.Thumbnail;
import com.android.desafio.desafio_android_vitor_santos.character.popular.model.Prices;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Utils {

    public static String getUrlImage(Thumbnail pictures){
        String picture;

        picture = pictures.getPath()+"."+pictures.getExtension();
        return picture.replace("http", "https");
    }

    public static Double getComicPrice(ArrayList<Prices> price){
        int id = 0;
        Double priceComic = 0.0;

        for(Object o : price) {
            if(price.get(id).getType().equals("printPrice")){
                priceComic = price.get(id).getPrice();
            }
        }

        return priceComic;
    }

    public static  boolean verificaConexao(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if(isConnected)
            return true;
        else
            return false;
    }


    public static void printGson(Object o, String message){
        String json = new GsonBuilder().create().toJson(o);
        System.out.println(message+" - "+json);

    }


}
