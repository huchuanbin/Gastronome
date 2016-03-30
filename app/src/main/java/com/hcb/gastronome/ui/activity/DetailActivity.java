package com.hcb.gastronome.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.hcb.gastronome.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Corbin on 2016/3/23.
 */
public class DetailActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
//    @Bind(R.id.btn)
//    Button btn;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("My Title");
        setSupportActionBar(toolbar);
    }
}
