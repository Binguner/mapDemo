package com.example.nenguou.myofo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Nenguou on 2017/4/12.
 */

public class DownLoadActivity extends AppCompatActivity {
    private ImageView back_botton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_layout);
        initId();

    }

    private void initId() {
        back_botton = (ImageView) findViewById(R.id.iv_back);
    }

    public void iv_back(View view){
        finish();
    }
}
