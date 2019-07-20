package com.zym.player.player;

import android.content.Context;
import android.media.MediaPlayer;

import com.zym.player.tool.DataSourceUtil;

public class PlayerFactory {

    /**
     * 一般工厂设计模式:可以创建用户想用的播放器
     * @return
     */
    public IPlayer createPlayer(Context context) {
        // 读取配置
        int playerType = DataSourceUtil.getMetaDataFromApp(context);
        switch (playerType) {
            case 1:
                return new GoogleMediaPlayer();
            default:
                break;
        }
        return null;
    }
}
