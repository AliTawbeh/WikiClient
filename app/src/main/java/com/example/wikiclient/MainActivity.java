package com.example.wikiclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wikiclient.network.WikiClient;
import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
//TODO use RX activity life cycle (extends RxAppCompatActivity)
//TODO show loading progress bar
//TODO add unit tests
//TODO convert to MVP architecture
//TODO use fragments
//TODO integrate dagger2
//TODO add Timber logging
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.search_btn) Button searchBtn;
    @BindView(R.id.search_query_et) EditText searchEditText;
    @BindView(R.id.data_tv) TextView dataTextView;
    Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupRxBinding();
    }

    private void setupRxBinding() {
        RxView.clicks(searchBtn)
                .subscribe(o -> {
                    String query = searchEditText.getText().toString();
                    getWikiText(query);
                });
    }

    private void getWikiText(String query) {
        disposable = WikiClient.getInstance()
                .getWikiResponse(query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wikiResponse -> {
                    String info;
                    if(wikiResponse.parse!=null)
                        info=wikiResponse.parse.text.get("*").getAsString();
                    else
                        info=wikiResponse.error.code + "\n" + wikiResponse.error.info;
                    dataTextView.setText(Html.fromHtml(info));

                });
    }

    private void displayHtmlTest(){
        String htmlText=getString(R.string.html_string);
        TextView textView = findViewById(R.id.data_tv);
        textView.setText(Html.fromHtml(htmlText));
        //textView.setText(htmlText);
    }
}
