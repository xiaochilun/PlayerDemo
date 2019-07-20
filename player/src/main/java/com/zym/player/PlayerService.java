package com.zym.player;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.zym.player.player.IPlayer;
import com.zym.player.player.PlayerFactory;
import com.zym.player.source.RawPlayerSource;
import com.zym.player.state.PlayerState;

import static com.zym.player.state.PlayerState.IDLE;

public class PlayerService extends Service {

    private PlayerState mState = IDLE;
    private IPlayer mPlayer;
    private PlayerFactory mPlayerFactory;

    public class PlayerBinder extends Binder {

        public PlayerService getService() {
            return PlayerService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PlayerBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 不管start还是bind不管调用几次只会回调一次,用来做全局初始化操作
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // startService多次回调多次,一般给Service传递数据
        return super.onStartCommand(intent, flags, startId);
    }

    public void playOrPause(RawPlayerSource rawPlayerSource) {
        switch (mState) {
            case IDLE:
                // 初始化播放器播放
                if (mPlayerFactory == null) {
                    mPlayerFactory = new PlayerFactory();
                }

                if (mPlayer == null) {
                    mPlayer = mPlayerFactory.createPlayer(getApplicationContext());
                }

                if (mPlayer == null) return;

                String url = rawPlayerSource.getUrl();
                mPlayer.release();

                mPlayer.prepare(getApplicationContext(), url);

                break;
            case PREPARING:
                break;
            default:
                break;
        }
    }
}
