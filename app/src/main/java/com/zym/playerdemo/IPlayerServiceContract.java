package com.zym.playerdemo;

import android.content.Context;

public interface IPlayerServiceContract {

    interface IPresenter {
        void bindService(Context context);
    }

    interface IView {

    }
}
