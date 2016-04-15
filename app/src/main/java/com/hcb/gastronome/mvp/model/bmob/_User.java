package com.hcb.gastronome.mvp.model.bmob;

import com.hcb.gastronome.mvp.model.home.HomeData;

import java.sql.Array;
import java.util.List;

import cn.bmob.v3.BmobUser;

/**
 * Created by huchuanbin on 16/4/2.
 */
public class _User extends BmobUser {

    private List<HomeData> interest;

    public List<HomeData> getInterest() {
        return interest;
    }

    public void setInterest(List<HomeData> interest) {
        this.interest = interest;
    }
}
