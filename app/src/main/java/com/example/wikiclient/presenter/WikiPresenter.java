package com.example.wikiclient.presenter;

/**
 * Created by Ali on 12-Apr-18.
 */

public interface WikiPresenter {
    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();
    void getWikiTextClicked(String query);
}
