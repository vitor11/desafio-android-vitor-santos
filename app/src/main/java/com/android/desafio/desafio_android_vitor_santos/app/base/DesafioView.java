package com.android.desafio.desafio_android_vitor_santos.app.base;

public interface DesafioView {

    void navigate(String extra);

    void showError(String login_error);

    void showLoad(boolean content);

    void showContent();

    void showEmpty();

    void hideLoad(boolean content);

    void onBackPressed(Class activity);
}
