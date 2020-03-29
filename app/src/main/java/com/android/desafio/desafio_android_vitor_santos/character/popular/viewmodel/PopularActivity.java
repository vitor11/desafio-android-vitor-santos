package com.android.desafio.desafio_android_vitor_santos.character.popular.viewmodel;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.desafio.desafio_android_vitor_santos.R;
import com.android.desafio.desafio_android_vitor_santos.app.base.BaseActivity;
import com.android.desafio.desafio_android_vitor_santos.app.di.FactoryProvider;
import com.android.desafio.desafio_android_vitor_santos.character.list.viewmodel.MainActivity;
import com.android.desafio.desafio_android_vitor_santos.character.popular.model.InformationComic;
import com.android.desafio.desafio_android_vitor_santos.character.popular.model.ListPopularComic;
import com.android.desafio.desafio_android_vitor_santos.character.popular.model.PopularComic;
import com.android.desafio.desafio_android_vitor_santos.character.popular.model.Prices;
import com.android.desafio.desafio_android_vitor_santos.databinding.ActivityPopularBinding;
import com.android.desafio.desafio_android_vitor_santos.utils.Constants;
import com.android.desafio.desafio_android_vitor_santos.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PopularActivity extends BaseActivity implements Toolbar.OnCreateContextMenuListener {

    private ActivityPopularBinding binding;
    private Long characterID;
    private PopularViewModel popularViewModel;
    private InformationComic dataInformationComic = new InformationComic();
    private ImageView pictureImageView;
    private LinearLayout mainLayout;
    private ListPopularComic listComicTest;

    private int off = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_popular);

        mainLayout = ((LinearLayout) findViewById(R.id.ln_popular));
        pictureImageView = (ImageView) findViewById(R.id.iv_picture_character);

        pictureImageView = findViewById(R.id.iv_picture_character);
        characterID = getIntent().getExtras().getLong("ID");

        initViewModel();
    }


    private void initViewModel(){
        popularViewModel = ViewModelProviders.of(this, new FactoryProvider(getApp())).get(PopularViewModel.class);
        popularViewModel.init(this);

        getComic();
    }

    public void getComic() {
        popularViewModel.getMostPopularComic(characterID ,Constants.TS, Constants.APIKEY, Constants.HASH, off).observe(this, emp ->{
            if(emp != null){
                listComicTest = emp;
                getMoreExpansiveComics(listComicTest);
            }
        });
    }

    public void makeBindingComic(InformationComic listBinding){
        if(listBinding != null){
            binding.viewContent.setInformationComic(listBinding);
            Picasso.with(getApplicationContext()).load(listBinding.getImage()).into(pictureImageView);
//            showContent();
        }
    }

    public void getMoreExpansiveComics(ListPopularComic list){
        int total = 0;
        int qtdOffset;
        ArrayList<PopularComic> pc;

        if(list.getData().getTotal() == 0){
            Toast.makeText(this, "Não Há informações para este personagem", Toast.LENGTH_SHORT).show();
            onBackPressed(MainActivity.class);
        }
        else {
            if (list.getData().getTotal() > 20) {
                qtdOffset = (list.getData().getTotal()) / 20;

                while (total <= qtdOffset) {
                    off = total;
                    getContent(listComicTest);
                    total++;
                }
            } else
                getContent(listComicTest);
        }

        makeBindingComic(dataInformationComic);
    }

    public void getContent(ListPopularComic list){

        int id = 0;
        ArrayList<PopularComic> pc = list.getData().getResult();

        for(Object o : pc){

            if(id == 0){

                ArrayList<Prices> price = pc.get(id).getPrices();

                dataInformationComic.setDescription(pc.get(id).getDescription());
                dataInformationComic.setImage(Utils.getUrlImage(pc.get(id).getThumbnail()));
                dataInformationComic.setPrice(Utils.getComicPrice(pc.get(id).getPrices()));
                dataInformationComic.setTitle(pc.get(id).getTitle());
            }
            else{
                if(Utils.getComicPrice(pc.get(id).getPrices()) > dataInformationComic.getPrice()){
                    dataInformationComic = null;

                    dataInformationComic.setDescription(pc.get(id).getDescription());
                    dataInformationComic.setImage(Utils.getUrlImage(pc.get(id).getThumbnail()));
                    dataInformationComic.setPrice(Utils.getComicPrice(pc.get(id).getPrices()));
                    dataInformationComic.setTitle(pc.get(id).getTitle());
                }
            }
        }
    }

}
