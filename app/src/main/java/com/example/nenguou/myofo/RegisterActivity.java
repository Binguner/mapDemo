package com.example.nenguou.myofo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class RegisterActivity extends AppCompatActivity {

    private FloatingActionButton fab221;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        initId();
    }

    private void initId() {
        fab221 = (FloatingActionButton) findViewById(R.id.fab221);
    }

    public void backbakcbak(View view){
        ActivityCompat.finishAfterTransition(this); //动画返回
/*        getWindow().setExitTransition(null);
        getWindow().setEnterTransition(null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions activityOptionsCompat =
                    ActivityOptions.makeSceneTransitionAnimation(this,fab221, getString(R.string.transitionName));
            //  ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,viewTab,viewCard);
            startActivity(new Intent(this, realLoginLayout.class), activityOptionsCompat.toBundle());
        } else {
            startActivity(new Intent(this, realLoginLayout.class));
        }*/
    }
}
