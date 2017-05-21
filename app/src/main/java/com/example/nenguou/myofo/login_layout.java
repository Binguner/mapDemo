package com.example.nenguou.myofo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class login_layout extends AppCompatActivity {

    private Button button_login;
    private MainActivity mainActivity;
    private int requestCode = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_layout);
        initId();
        button_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Intent intent = new Intent();
                intent.putExtra("change",1);
                setResult(requestCode,intent);
                finish();
            }
        });
    }
    public void initId(){
        button_login = (Button) findViewById(R.id.login);
    }

}
