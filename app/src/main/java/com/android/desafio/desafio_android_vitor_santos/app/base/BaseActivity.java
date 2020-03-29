package com.android.desafio.desafio_android_vitor_santos.app.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.desafio.desafio_android_vitor_santos.R;
import com.android.desafio.desafio_android_vitor_santos.app.DesafioApp;
import com.android.desafio.desafio_android_vitor_santos.character.detail.viewmodel.DetailActivity;
import com.android.desafio.desafio_android_vitor_santos.character.list.adapter.ListAdapterListener;
import com.android.desafio.desafio_android_vitor_santos.character.list.model.ListCharacter;
import com.android.desafio.desafio_android_vitor_santos.character.list.model.Results;
import com.android.desafio.desafio_android_vitor_santos.utils.Utils;

import java.util.List;

public class BaseActivity extends AppCompatActivity implements DesafioView, ListAdapterListener {

    protected ProgressDialog progressDialog;

    protected DesafioApp getApp() {
        return (DesafioApp) getApplication();
    }

    @Override
    public void navigate(String extra) {
    }

    @Override
    public void showError(String login_error) {
        Toast.makeText(getApplicationContext(), login_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoad(boolean content) {
        progressDialog = ProgressDialog.show(this, "Carregando", "Aguarde...");
        progressDialog.setCancelable(false);

        if(content)
            showEmpty();
    }

    @Override
    public void showContent() {
        findViewById(R.id.view_content).setVisibility(View.VISIBLE);
        findViewById(R.id.view_empty).setVisibility(View.GONE);
        findViewById(R.id.view_error).setVisibility(View.GONE);
    }

    @Override
    public void showEmpty() {
        findViewById(R.id.view_empty).setVisibility(View.VISIBLE);
        findViewById(R.id.view_content).setVisibility(View.GONE);
        findViewById(R.id.view_error).setVisibility(View.GONE);
    }

    @Override
    public void hideLoad(boolean content) {
        if (progressDialog != null) {
            progressDialog.dismiss();

            if(content)
                showContent();
        }
    }

    @Override
    public void onCustomClick(ListCharacter id, ListCharacter name, String picture, String description) {
//        Long characterID = results.getId();

        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putExtra("ID", id.getId());
        intent.putExtra("description", description);
        intent.putExtra("name", name.getName());
        intent.putExtra("picture", picture.replace("http", "https"));
        startActivity(intent);
    }

    @Override
    public void onBackPressed(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

/*    @Override
    public void onCustomClick(Results results) {
        Long characterID = results.getId();

        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        intent.putExtra("ID", characterID);
        intent.putExtra("description", results.getDescription());
        intent.putExtra("name", results.getName());
        intent.putExtra("picture", Utils.getUrlImage(results.getThumbnail()));
        startActivity(intent);
    }*/

    @Override
    public void onLoadMore() {
        Toast.makeText(this, "FIM SCROLL", Toast.LENGTH_SHORT).show();
    }

}
