package com.hcb.gastronome.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hcb.gastronome.R;
import com.hcb.gastronome.mvp.model.bmob.BannerData;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by huchuanbin on 16/4/6.
 */
public class TestActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BmobQuery query = new BmobQuery("Banner");
        query.findObjects(this, new FindListener<BannerData>() {
            @Override
            public void onSuccess(List<BannerData> list) {
//                Log.d("TestActivity", "list.size():" + list.size());
                Log.d("TestActivity", list.get(0).getUrl());
                Log.d("TestActivity", list.get(0).getPic().getFileUrl(TestActivity.this));
            }

            @Override
            public void onError(int i, String s) {

            }
        });
//        query.findObjects(this, new FindCallback() {
//            @Override
//            public void onSuccess(JSONArray jsonArray) {
//                Log.d("TestActivity", jsonArray.toString());
//
//            }
//
//            @Override
//            public void onFailure(int i, String s) {
//                Log.d("TestActivity", s);
//            }
//        });
        //        String bql ="select * from Banner";//查询所有的游戏得分记录
//        Log.d("TestActivity", "111");
//        new BmobQuery<Banner>().doSQLQuery(this,bql,new SQLQueryListener<Banner>(){
//            @Override
//            public void done(BmobQueryResult<Banner> result, BmobException e) {
//                Log.d("TestActivity", "222");
//                if(e ==null){
//                    List<Banner> list = (List<Banner>) result.getResults();
//                    if(list!=null && list.size()>0){
//                        Log.d("TestActivity", list.get(0).getUrl());
//                    }else{
//                        Log.i("TestActivity", "查询成功，无数据返回");
//                    }
//                }else{
//                    Log.i("TestActivity", "错误码："+e.getErrorCode()+"，错误描述："+e.getMessage());
//                }
//            }
//        });
    }
}
