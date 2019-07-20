package com.zym.playerdemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.zym.player.PlayerService;
import com.zym.player.source.RawPlayerSource;

public class PlayerServicePresenter implements IPlayerServiceContract.IPresenter {

    private IPlayerServiceContract.IView mIView;
    private PlayerService mPlayerService;
    private Context mContext;

    public PlayerServicePresenter(IPlayerServiceContract.IView iView, Context applicationContext) {
        mIView = iView;
        mContext = applicationContext;
    }

    @Override
    public void bindService(Context context) {
        Intent intent = new Intent(context, PlayerService.class);
//        context.startService(intent);// activity销毁 service不会销毁 不能和组件进行数据交互
        context.bindService(intent, mConn, Service.BIND_AUTO_CREATE);// activity销毁 service可能销毁，在没有其他组件和service绑定的情况下
    }

    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // service连接成功后回调
            PlayerService.PlayerBinder binder = (PlayerService.PlayerBinder) service;
            mPlayerService = binder.getService();
            if (mPlayerService != null) {
                RawPlayerSource playerSource = new RawPlayerSource();
                mPlayerService.playOrPause(playerSource.setPath(mContext.getPackageName(), 99));
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 断开回调
            if (mPlayerService != null) {
                mPlayerService.unbindService(this);
                mPlayerService = null;
            }
        }
    };
}
