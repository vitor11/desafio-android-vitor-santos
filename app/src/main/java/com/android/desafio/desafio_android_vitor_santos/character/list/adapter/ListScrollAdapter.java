package com.android.desafio.desafio_android_vitor_santos.character.list.adapter;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
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

public class ListScrollAdapter extends RecyclerView.Adapter {
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    public static ListAdapterListener listAdapterListener;

    Context ctx;
    private List<ListCharacter> id;
    private List<ListCharacter> name;
    private List<ListCharacter> picture;
    ArrayList<Results> results;

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;


    public ListScrollAdapter(Context ctx, ArrayList<Results> results, List<ListCharacter> characterId, List<ListCharacter> nameCharacter, List<ListCharacter> pictureCharacter, ListAdapterListener listAdapterListener, RecyclerView recyclerView) {
        this.ctx = ctx;
        this.id = characterId;
        this.name = nameCharacter;
        this.picture = pictureCharacter;
        this.results = results;
        this.listAdapterListener = listAdapterListener;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        // End has been reached
                        // Do something
                        if (listAdapterListener != null) {
                            listAdapterListener.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return results.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card, parent, false);
        return new ListScrollAdapter.ListViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ListViewHolder) {
            ListCharacter listID   = id.get(position);
            ListCharacter listName = name.get(position);
            ListCharacter listPicture = picture.get(position);

            ((ListViewHolder) holder).viewName.setText(listName.getName());
            Picasso.with(ctx).load(listPicture.getPicture().replace("http", "https")).into(((ListViewHolder) holder).viewImg);

        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    public void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public void setListAdapterListener(ListAdapterListener listAdapterListener) {
        this.listAdapterListener = listAdapterListener;
    }


    //
    protected class ListViewHolder extends RecyclerView.ViewHolder {


        protected TextView viewName;
        protected ImageView viewImg;

        public TextView tvName;
        public TextView tvEmailId;

        public ListViewHolder(View itemView) {
            super(itemView);

            viewName = (TextView) itemView.findViewById(R.id.tv_name);
            viewImg  = (ImageView) itemView.findViewById(R.id.iv_picture);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //listAdapterListener.onCustomClick(results.get(getLayoutPosition()));
//                    listAdapterListener.onLoadMore();

                }
            });
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        }
    }
}