package com.example.nenguou.myofo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.nenguou.myofo.PageAdapter.SearchActivity;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private com.github.clans.fab.FloatingActionButton refresh;
    private com.github.clans.fab.FloatingActionButton fab_search;
    private MapView mapView;
    private AMap aMap;
    private UiSettings mUiSettings;
    private NavigationView navView;
    private int requestCode;
    private LayoutInflater layoutInflater;

    AMapLocationClient mLocationClient = null;
    AMapLocationClientOption mLocationOption = null;
    Marker locationMarker = null;
    View hander_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initId();
        mapView.onCreate(savedInstanceState);
        //设置 Toolbar
        setToolbar();
        setNavigationView();
        refresh = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.refresh);
        refresh.setOnClickListener(this);
        setAliMap();
        askPermisson();
        singleTapToRefresh();
        controlUI();    //地图 UI 控件
        setStatusColor();   //状态栏沉浸
        layoutInflater = getLayoutInflater();

        hander_login = layoutInflater.inflate(R.layout.header,null);
    }


    private void setNavigationView() {
        //获取 NavigationView 实例，然后设置监听事件
        navView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navView.getHeaderView(0); //获取 NavigationView 中的 headerLayout
        Button login = (Button) headerView.findViewById(R.id.login);        //点击登陆按钮
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,realLoginLayout.class);
                requestCode = 1;
                startActivityForResult(intent,1);
            }
        });
      //  Button logout = (Button) headerView.findViewById(R.id.iamexitbutton);
/*        ImageView header_image = (ImageView) headerView.findViewById(R.id.icon_image);  //获取 headerLayout 里的 header_image
        header_image.setOnClickListener(new View.OnClickListener() {    //为 header_image 设置点击事件
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MyBlogActivity.class);
                startActivity(intent);
            }
        });*/
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawer(GravityCompat.START);     //选中一个 item 后关闭导航栏
                item.setChecked(true);
                switch (item.getItemId()){
                    case R.id.nav_download:
                        Intent intent1 = new Intent(MainActivity.this,GuideActivity.class);  //GuideActivity
                        startActivityForResult(intent1,0);
                        //overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_in_left);
                        //mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_guide:
                        Intent intent2 = new Intent(MainActivity.this,DownLoadActivity.class);
                        startActivityForResult(intent2,0);
                        //mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_about:
                        Intent intent3 = new Intent(MainActivity.this,AboutApp.class);
                        startActivityForResult(intent3,0);
                        //mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_exitapp:
                        finish();
                        break;
                }

                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String change = data.getStringExtra("change");
        switch (requestCode){
            case 1:
                int height_before = navView.getHeaderView(0).getHeight();
                int width_before = navView.getHeaderView(0).getWidth();
                navView.getHeaderView(0).setVisibility(View.GONE);

                navView.addHeaderView(hander_login);
                int height = navView.getHeaderView(1).getLayoutParams().height;
                navView.getHeaderView(1).setLayoutParams(new LinearLayout.LayoutParams(width_before,height_before));
        }

        }

    private void initId() {
        mapView = (MapView) findViewById(R.id.map_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        fab_search = (FloatingActionButton) findViewById(R.id.fab2);
        fab_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivityForResult(intent,0);
            }
        });

    }

    public void setAliMap(){
        //初始化地图控制器对象aMap
        if (aMap == null) {
            aMap = mapView.getMap();    //将mapView交给地图控制器管理
        }
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);   //设置定位模式
        //设置定位点的图标myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker));
        //定义精度圆样式
        myLocationStyle.strokeColor(0);
        myLocationStyle.radiusFillColor(0);

        //将定位风格设置传给地图控制器
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17)); //设置缩放级别为17
        aMap.showIndoorMap(true); //显示室内地图
    }

    //设置 Toolbar
    public void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("Binguner Map");
        setSupportActionBar(toolbar);       //使 toolbar 具有和 actionbar 一样的功能
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setNavigationIcon(R.drawable.menu_24);
    }

    //控件交互
    private void controlUI() {
        mUiSettings = aMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(false); //缩放按钮的显示与隐藏
        mUiSettings.setCompassEnabled(false); //指南针的显示与隐藏
        mUiSettings.setScaleControlsEnabled(false); //比例尺的显示与隐藏
        mUiSettings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT); //         设置LOGO位置
        mUiSettings.setRotateGesturesEnabled(false); //禁止旋转
    }

    //设置状态栏沉浸
    public void setStatusColor(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                //将侧边栏顶部延伸至status bar
                mDrawerLayout.setFitsSystemWindows(true);
                //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
                mDrawerLayout.setClipToPadding(false);
            }
        }
    }

    //刷新定位
    private void singleTapToRefresh() {
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setOnceLocation(true); //设置为单次定位模式
        mLocationOption.setNeedAddress(true); //返回地址描述
        mLocationOption.setHttpTimeOut(10000); //设置请求超时时间
        mLocationClient.setLocationOption(mLocationOption);

        //设置定位回调监听器
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if(aMapLocation != null){
                    LatLng latLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                    if(locationMarker == null){
                        locationMarker = aMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.center_marker2)));
                    }else{
                        locationMarker.setPosition(latLng);
                    }
                    //将标记移动到定位点，使用animateCamera就有动画效果
                    aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                }else{
                    Toast.makeText(MainActivity.this, "定位失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //询问权限
    private void askPermisson() {
        List<String> permissionList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.
                permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.
                permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.
                permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        }
    }

    //账号退出
    public void exit(View view){

    }


    //填充 toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //通过布局文件tool_action.xml创建 Menu 对象
        getMenuInflater().inflate(R.menu.toolbar_action, menu);
        return true;    //true 表示允许创建的 Menu 显示
    }

    //toolbar 点击响应事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {  //获取被选中项的 ID
            case R.id.notification:
                Toast.makeText(this, "搜索功能正在建设当中", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home: //导航栏入口按钮的响应事件
                //调出导航栏
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.refresh:
                mLocationClient.startLocation();
                break;
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }

    @Override
    protected void onResume(){
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


}
