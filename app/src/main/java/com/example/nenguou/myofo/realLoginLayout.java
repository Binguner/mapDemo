package com.example.nenguou.myofo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class realLoginLayout extends AppCompatActivity {

    private int requestCode = 1;
    private FloatingActionButton fab123;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_real_login_layout);
        initId();
    }

    private void initId() {
        fab123 = (FloatingActionButton) findViewById(R.id.fab12);
        cardView = (CardView) findViewById(R.id.cv);
    }

    public void gotologon(View view){
    //    Pair<View,String> viewTab = Pair.create((View)fab123,getString(R.string.transitionName));
     //   Pair<View,String> viewCard = Pair.create((View)cardView,getString(R.string.transitionNameForcv));
        getWindow().setExitTransition(null);
        getWindow().setEnterTransition(null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
           ActivityOptions activityOptionsCompat =
                    ActivityOptions.makeSceneTransitionAnimation(this,fab123, getString(R.string.transitionName));
          //  ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,viewTab,viewCard);
            startActivity(new Intent(this, RegisterActivity.class), activityOptionsCompat.toBundle());
        } else {
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }

    public void loginclick(View view) {
        Intent intent = new Intent();
        intent.putExtra("change", 1);
        setResult(requestCode, intent);
        finish();
    }
}

