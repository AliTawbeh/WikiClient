package com.example.wikiclient.module;

import com.example.wikiclient.presenter.WikiPresenter;
import com.example.wikiclient.presenter.WikiPresenterImpl;
import com.example.wikiclient.view.WikiView;

/**
 * Created by Ali on 12-Apr-18.
 */

public class WikiModule {
    public WikiView mWikiView;
    public WikiModule(WikiView wikiView){
        assert wikiView!=null;
        mWikiView=wikiView;
    }
    public WikiPresenter provideWikiPresenter(){
        return new WikiPresenterImpl(mWikiView);
    }
}
