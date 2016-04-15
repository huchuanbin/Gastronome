package com.hcb.gastronome.mvp.view_controller;

import com.hcb.gastronome.mvp.model.bmob._User;

/**
 * Created by huchuanbin on 16/4/15.
 */
public interface CollectionView extends BaseView {
    void loadServerData(_User user);
}
