package com.example.nenguou.myofo.PageAdapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nenguou.myofo.R;

public class SearchActivity extends AppCompatActivity {

    private ImageView microphone_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initId();
    }

    private void initId() {
        microphone_search = (ImageView) findViewById(R.id.microphone_search);
    }

    public void yuying(View view) {
        Toast.makeText(SearchActivity.this, "语音搜索功能正在开发", Toast.LENGTH_SHORT).show();
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_hotel:
                Toast.makeText(SearchActivity.this, "hotel", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_food:
                Toast.makeText(SearchActivity.this, "food", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_bus_station:
                Toast.makeText(SearchActivity.this, "bus_station", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_bank:
                Toast.makeText(SearchActivity.this,"bank",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_views:
                Toast.makeText(SearchActivity.this,"views",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_fighting_station:
                Toast.makeText(SearchActivity.this,"gas_station",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_collection:
                Toast.makeText(SearchActivity.this,"collection",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_more:
                Toast.makeText(SearchActivity.this,"more",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
