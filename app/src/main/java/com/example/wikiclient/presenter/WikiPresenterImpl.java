package com.example.wikiclient.presenter;

import android.text.Html;

import com.example.wikiclient.network.WikiClient;
import com.example.wikiclient.view.WikiView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ali on 12-Apr-18.
 */

public class WikiPresenterImpl implements WikiPresenter {

    private Disposable mDisposable;
    private WikiView mWikiView;

    public WikiPresenterImpl(WikiView wikiView) {
        mWikiView = wikiView;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        if(mDisposable !=null && !mDisposable.isDisposed())
            mDisposable.dispose();
    }

    @Override
    public void getWikiTextClicked(String query) {
        mDisposable = WikiClient.getInstance()
                .getWikiResponse(query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wikiResponse -> {
                    String info;
                    if(wikiResponse.parse!=null)
                        info=wikiResponse.parse.text.get("*").getAsString();
                    else
                        info=wikiResponse.error.code + "\n" + wikiResponse.error.info;
                    mWikiView.displayWikiText(Html.fromHtml(info));

                });
    }
}
