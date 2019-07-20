package com.zym.playerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements IPlayerServiceContract.IView {

    private PlayerServicePresenter mPresenter = new PlayerServicePresenter(this, getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter.bindService(getApplicationContext());
    }
}
