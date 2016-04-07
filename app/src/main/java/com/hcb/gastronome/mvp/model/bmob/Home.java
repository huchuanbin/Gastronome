package com.hcb.gastronome.mvp.model.bmob;

import cn.bmob.v3.BmobObject;

/**
 * Created by huchuanbin on 16/4/7.
 */
public class Home extends BmobObject {
    private String id;
    private String title;
    private String albums;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbums() {
        return albums;
    }

    public void setAlbums(String albums) {
        this.albums = albums;
    }
}
