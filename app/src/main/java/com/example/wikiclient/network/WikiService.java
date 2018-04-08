package com.example.wikiclient.network;

import com.example.wikiclient.data.WikiResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ali on 07-Apr-18.
 */

public interface WikiService {
    @GET("w/api.php?action=parse&section=0&prop=text&format=json")
    Observable<WikiResponse> getWikiResponse(@Query("page") String userName);
}
