package com.zym.player.tool;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

public class DataSourceUtil {

    private static int mValue;

    public static int getMetaDataFromApp(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName()
                    , PackageManager.GET_META_DATA);
            mValue = applicationInfo.metaData.getInt("player_type");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mValue;
    }
}
