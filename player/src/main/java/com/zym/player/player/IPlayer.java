package com.zym.player.player;

import android.content.Context;

public interface IPlayer {

    void release();

    void prepare(Context context, String url);
}
