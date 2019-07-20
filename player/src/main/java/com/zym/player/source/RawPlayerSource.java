package com.zym.player.source;

import java.io.File;

public class RawPlayerSource implements IPlayerSource {

    private String url;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    public RawPlayerSource setPath(String packageName, int rawId) {
        String url = "android.resource://" + packageName + File.separator + rawId;
        setUrl(url);
        return this;
    }

    @Override
    public String getUrl() {
        return url;
    }
}
