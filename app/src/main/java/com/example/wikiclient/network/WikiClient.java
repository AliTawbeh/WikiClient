package com.example.wikiclient.network;

import android.support.annotation.NonNull;

import com.example.wikiclient.data.WikiResponse;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ali on 07-Apr-18.
 */

public class WikiClient {
    private static final String WIKI_BASE_URL="https://en.wikipedia.org/";

    private static WikiClient wikiClient;
    private WikiService wikiService;

    private WikiClient(){
        final Gson gson = new GsonBuilder()
                .setLenient()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(WIKI_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        wikiService = retrofit.create(WikiService.class);
    }

    public static WikiClient getInstance(){
        if(wikiClient==null)
            wikiClient = new WikiClient();
        return wikiClient;
    }

    public Observable<WikiResponse> getWikiResponse(@NonNull String title){
//        String urlTitle;
//        try {
//            urlTitle =  URLEncoder.encode(title, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            urlTitle=title;
//        }
        return wikiService.getWikiResponse(title);
    }
}
