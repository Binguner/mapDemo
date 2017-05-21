package com.example.nenguou.myofo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class AboutApp extends AppCompatActivity {

    private Banner banner;
    private Button button_checkUpdate;
    private Button button_updateRecord;
    private List<String> imageUrl;
    private List<String> bannerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        initId();
        initData();
        initView();
    }

    public void click1(View view) {
        Toast.makeText(AboutApp.this, "已是最新版本", Toast.LENGTH_SHORT).show();
    }

    public void click2(View view) {
        Toast.makeText(AboutApp.this, "暂时没有", Toast.LENGTH_SHORT).show();
    }

    public void iv_back1(View view) {
        finish();
    }


    private void initId() {
        banner = (Banner) findViewById(R.id.banner);
        button_checkUpdate = (Button) findViewById(R.id.checkUpdate);
        button_updateRecord = (Button) findViewById(R.id.updateRecord);
    }

    private void initView() {
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(imageUrl);
        banner.setBannerTitles(bannerTitle);
        banner.setDelayTime(3000);
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                switch (position) {
                    case 0:
                        Uri uri1 = Uri.parse("http://www.binguner.com");
                        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
                        startActivity(intent1);
                        break;
                    case 1:
                        Uri uri2 = Uri.parse("http://www.jianshu.com/u/7ac4634329c3");
                        Intent intent2 = new Intent(Intent.ACTION_VIEW, uri2);
                        startActivity(intent2);
                        break;
                    case 2:
                        Uri uri3 = Uri.parse("http://blog.csdn.net/nenguou04");
                        Intent intent3 = new Intent(Intent.ACTION_VIEW, uri3);
                        startActivity(intent3);
                        break;
                    case 3:
                        Uri uri4 = Uri.parse("https://github.com/Nenguou");
                        Intent intent4 = new Intent(Intent.ACTION_VIEW, uri4);
                        startActivity(intent4);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initData() {
        //图片地址
        imageUrl = new ArrayList<>();
        imageUrl.add("http://upload-images.jianshu.io/upload_images/5264123-cdd808dc420a5fce.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");
        imageUrl.add("http://upload-images.jianshu.io/upload_images/5264123-08596267b2af6966.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");
        imageUrl.add("http://upload-images.jianshu.io/upload_images/5264123-7588df97c05e65dd.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");
        imageUrl.add("http://upload-images.jianshu.io/upload_images/5264123-978f28c337669ab0.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240");


        //Title名称
        bannerTitle = new ArrayList<>();
        bannerTitle.add("Blog");
        bannerTitle.add("简书");
        bannerTitle.add("CSDN");
        bannerTitle.add("GitHub");
    }

}
