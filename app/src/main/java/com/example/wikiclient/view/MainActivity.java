package com.example.wikiclient.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spanned;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wikiclient.R;
import com.example.wikiclient.module.WikiModule;
import com.example.wikiclient.presenter.WikiPresenter;
import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
//TODO use RX activity life cycle (extends RxAppCompatActivity)
//TODO show loading progress bar
//TODO add unit tests
//TODO convert to MVP architecture
//TODO use fragments
//TODO integrate dagger2
//TODO add Timber logging
public class MainActivity extends AppCompatActivity implements WikiView{

    @BindView(R.id.search_btn) Button searchBtn;
    @BindView(R.id.search_query_et) EditText searchEditText;
    @BindView(R.id.data_tv) TextView dataTextView;

    WikiPresenter mWikiPresenter;

    WikiModule mWikiModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mWikiModule = new WikiModule(this);
        mWikiPresenter = mWikiModule.provideWikiPresenter();
        setupRxBinding();
    }

    private void setupRxBinding() {
        RxView.clicks(searchBtn)
                .subscribe(o -> {
                    String query = searchEditText.getText().toString();
                    mWikiPresenter.getWikiTextClicked(query);
                });
    }

    @Override
    public void displayWikiText(Spanned spanned) {
        dataTextView.setText(spanned);
    }
}
