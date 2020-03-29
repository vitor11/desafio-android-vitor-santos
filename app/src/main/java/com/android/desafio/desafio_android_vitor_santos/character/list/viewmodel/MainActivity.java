package com.android.desafio.desafio_android_vitor_santos.character.list.viewmodel;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;
import android.widget.Toast;

import com.android.desafio.desafio_android_vitor_santos.R;
import com.android.desafio.desafio_android_vitor_santos.app.base.BaseActivity;
import com.android.desafio.desafio_android_vitor_santos.app.di.FactoryProvider;
import com.android.desafio.desafio_android_vitor_santos.character.list.adapter.ListLoadMoreAdapter;
import com.android.desafio.desafio_android_vitor_santos.character.list.model.ListCharacter;
import com.android.desafio.desafio_android_vitor_santos.character.list.model.Results;
import com.android.desafio.desafio_android_vitor_santos.character.list.model.Thumbnail;
import com.android.desafio.desafio_android_vitor_santos.utils.Constants;
import com.android.desafio.desafio_android_vitor_santos.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    ListLoadMoreAdapter adapter;
    private List<ListCharacter> listNames = new ArrayList<>();
    private List<ListCharacter> listPictures = new ArrayList<>();
    private List<ListCharacter> listIds = new ArrayList<>();
    private List<ListCharacter> listDescription = new ArrayList<>();

    private ListViewModel listViewModel;
    private ListCharacter listCharacters;

    private int off = 0;
    int total;
    boolean isLoading = false;

    protected Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        handler = new Handler();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_characters);
        mRecyclerView.setHasFixedSize(true);

        //Aqui é instanciado o Recyclerview
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        ((LinearLayoutManager) mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        initViewModel();
//        scroll();
        initScrollListener();


    }

    private void initViewModel(){
        listViewModel = ViewModelProviders.of(this, new FactoryProvider(getApp())).get(ListViewModel.class);
        listViewModel.init(this);
        getCharacters(off, isLoading);
    }

    public void getCharacters(int offset, boolean load){
        listViewModel.getListCharacters(Constants.TS, Constants.APIKEY, Constants.HASH, offset).observe(this, emp ->{
            if (emp != null && emp.getData().getOffset() == offset) {
                listCharacters = null;
                listCharacters = emp;
                initContent(listCharacters, load);
            }
        });
    }


    public void initContent(ListCharacter list, boolean load){
        ArrayList<Results> pa = list.getData().getResult();
        int id = 0;
        total += pa.size();

        for(Object o : pa){

            ListCharacter names = new ListCharacter();
            names.setName(pa.get(id).getName());
            listNames.add(names);

            ListCharacter pictures = new ListCharacter();

            Thumbnail pat = pa.get(id).getThumbnail();

            pictures.setPicture(pat.getPath()+"."+pat.getExtension());
            listPictures.add(pictures);

            ListCharacter characterIds = new ListCharacter();
            characterIds.setId(pa.get(id).getId());
            listIds.add(characterIds);


            ListCharacter description = new ListCharacter();
            description.setDescription(pa.get(id).getDescription());
            listDescription.add(description);

            id++;
        }

        setRecycler(pa);

    }

    public void setRecycler(ArrayList<Results> pa){

        adapter = new ListLoadMoreAdapter(getApplication(), listCharacters.getData().getResult(), listIds, listNames, listPictures, listDescription, this);
        mRecyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }


    public void initScrollListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == listNames.size() - 1) {
                        if(!Utils.verificaConexao(getApplicationContext())){
                            Toast.makeText(getApplicationContext(), "Sem conexão com a internet", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            loadMore();
                            isLoading = true;
                        }
                    }
                }
            }
        });
    }

    public void loadMore() {
        listCharacters = null;
        listNames.add(null);
        listPictures.add(null);
        listIds.add(null);
        listDescription.add(null);


        adapter.notifyItemInserted(listIds.size() - 1);
        adapter.notifyItemInserted(listNames.size() - 1);
        adapter.notifyItemInserted(listPictures.size() - 1);
        adapter.notifyItemInserted(listDescription.size() - 1);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                listNames.remove(listNames.size() -1 );
                listPictures.remove(listPictures.size() -1 );
                listIds.remove(listIds.size() -1 );
                listDescription.remove(listDescription.size() -1 );

                int scrollPosition = total;//listPictures.size();
                off = (total/20);

                adapter.notifyItemRemoved(scrollPosition);

                getCharacters(off , isLoading);

                isLoading = false;
            }
        }, 4000);
    }

}
