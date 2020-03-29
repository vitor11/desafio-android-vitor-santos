package com.android.desafio.desafio_android_vitor_santos.character.list.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.desafio.desafio_android_vitor_santos.R;
import com.android.desafio.desafio_android_vitor_santos.character.list.model.ListCharacter;
import com.android.desafio.desafio_android_vitor_santos.character.list.model.Results;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.RecyclerTesteViewHolder> {

    public static ListAdapterListener listAdapterListener;
    Context ctx;
    private List<ListCharacter> id;
    private List<ListCharacter> name;
    private List<ListCharacter> picture;
    ArrayList<Results> results;

    public ListAdapter(Context ctx, ArrayList<Results> results, List<ListCharacter> characterId, List<ListCharacter> nameCharacter, List<ListCharacter> pictureCharacter, ListAdapterListener listAdapterListener) {
        this.ctx = ctx;
        this.id = characterId;
        this.name = nameCharacter;
        this.picture = pictureCharacter;
        this.results = results;
        this.listAdapterListener = listAdapterListener;
    }

    @Override
    public RecyclerTesteViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_card, viewGroup, false);
        return new RecyclerTesteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerTesteViewHolder viewHolder, int i) {
        ListCharacter listID   = id.get(i);
        ListCharacter listName = name.get(i);
        ListCharacter listPicture = picture.get(i);

        viewHolder.viewName.setText(listName.getName());
        Picasso.with(ctx).load(listPicture.getPicture().replace("http", "https")).into(viewHolder.viewImg);
    }

    @Override
    public int getItemCount() {
        return id.size();
    }


    protected class RecyclerTesteViewHolder extends RecyclerView.ViewHolder {

        protected TextView viewName;
        protected ImageView viewImg;

        public RecyclerTesteViewHolder(final View itemView) {
            super(itemView);

            viewName = (TextView) itemView.findViewById(R.id.tv_name);
            viewImg  = (ImageView) itemView.findViewById(R.id.iv_picture);

            //Setup the click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }

}
