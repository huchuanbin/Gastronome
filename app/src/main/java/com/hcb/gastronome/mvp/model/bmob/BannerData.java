package com.hcb.gastronome.mvp.model.bmob;

import java.io.File;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by huchuanbin on 16/4/6.
 */
public class BannerData extends BmobObject{
    private String url;
    private BmobFile pic;

    public BmobFile getPic() {
        return pic;
    }

    public void setPic(BmobFile pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
