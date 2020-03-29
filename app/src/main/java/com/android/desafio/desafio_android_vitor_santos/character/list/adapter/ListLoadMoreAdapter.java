package com.android.desafio.desafio_android_vitor_santos.character.list.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.desafio.desafio_android_vitor_santos.R;
import com.android.desafio.desafio_android_vitor_santos.character.list.model.ListCharacter;
import com.android.desafio.desafio_android_vitor_santos.character.list.model.Results;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListLoadMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public static ListAdapterListener listAdapterListener;
    Context ctx;
    private List<ListCharacter> id;
    private List<ListCharacter> name;
    private List<ListCharacter> picture;
    private List<ListCharacter> description;
    ArrayList<Results> results;

    public ListLoadMoreAdapter(Context ctx, ArrayList<Results> results, List<ListCharacter> characterId, List<ListCharacter> nameCharacter, List<ListCharacter> pictureCharacter, List<ListCharacter> descriptionCharacter , ListAdapterListener listAdapterListener) {
        this.ctx = ctx;
        this.id = characterId;
        this.name = nameCharacter;
        this.picture = pictureCharacter;
        this.results = results;
        this.description = descriptionCharacter;
        this.listAdapterListener = listAdapterListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_progressbar, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof ItemViewHolder) {
            populateItemRows((ItemViewHolder) viewHolder, position);
        } else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, position);
        }

    }

    @Override
    public int getItemCount() {
        return id == null ? 0 : id.size();
    }

    @Override
    public int getItemViewType(int position) {
        return id.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder {

        protected TextView viewName;
        protected ImageView viewImg;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            viewName = (TextView) itemView.findViewById(R.id.tv_name);
            viewImg  = (ImageView) itemView.findViewById(R.id.iv_picture);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listAdapterListener.onCustomClick(id.get(getLayoutPosition()), name.get(getLayoutPosition()), picture.get(getLayoutPosition()).getPicture(), description.get(getLayoutPosition()).getDescription());
                }
            });

        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {

    }

    private void populateItemRows(ItemViewHolder viewHolder, int position) {

        ListCharacter listID   = id.get(position);
        ListCharacter listName = name.get(position);
        ListCharacter listPicture = picture.get(position);

        viewHolder.viewName.setText(listName.getName());
        Picasso.with(ctx).load(listPicture.getPicture().replace("http", "https")).into(viewHolder.viewImg);

    }


}