package com.android.desafio.desafio_android_vitor_santos.character.detail.viewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.desafio.desafio_android_vitor_santos.R;
import com.android.desafio.desafio_android_vitor_santos.app.base.BaseActivity;
import com.android.desafio.desafio_android_vitor_santos.character.popular.viewmodel.PopularActivity;
import com.android.desafio.desafio_android_vitor_santos.utils.Utils;
import com.squareup.picasso.Picasso;

public class DetailActivity extends BaseActivity{

    //private ActivityDetailBinding binding;
    private Long characterID;
    private String name;
    private String description;
    private String picture;

    //VALORES
    private TextView nameTextView;
    private TextView descriptionTextView;
    private ImageView pictureImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();

        nameTextView = findViewById(R.id.tv_name_details);
        descriptionTextView = findViewById(R.id.tv_description);
        pictureImageView = findViewById(R.id.iv_picture_character);



        characterID = getIntent().getExtras().getLong("ID");
        name = getIntent().getExtras().getString("name");
        description = getIntent().getExtras().getString("description");
        picture = getIntent().getExtras().getString("picture");



        nameTextView.setText(name);
        descriptionTextView.setText(description);
        Picasso.with(getApplicationContext()).load(picture).into(pictureImageView);

    }

    public void getPopularHQ(View view){

        if(!Utils.verificaConexao(this))
            Toast.makeText(this, "Sem conexão com a internet", Toast.LENGTH_SHORT).show();

        else{
            Intent intent = new Intent(getApplicationContext(), PopularActivity.class);
            intent.putExtra("ID", characterID);
            startActivityForResult(intent, 1);
        }

    }


}
