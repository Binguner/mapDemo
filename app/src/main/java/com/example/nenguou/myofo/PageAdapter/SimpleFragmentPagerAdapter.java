package com.example.nenguou.myofo.PageAdapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Nenguou on 2017/4/13. ViewPager的适配器
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

   // private int[] imageResId = {R.drawable.menu_24, R.drawable.search_24, R.drawable.setting_poing_white_24};
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"下载管理", "城市列表"};
    private Context context;
    private List<Fragment> views;
    //private ArrayList<Fragment> views = new ArrayList<>();
  //  private List<Fragment> views = new ArrayList<>();
    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context,List<Fragment> views) {
        super(fm);
        this.context = context;
        this.views = views;
    }


    @Override
    public Fragment getItem(int position) {
        return views.get(position);
    }



    @Override
    public int getCount() {
        return views.size();
    }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
}

/*    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        // return tabTitles[position];
        Drawable image = context.getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }*/


/*    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        Drawable image = context.getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        // Replace blank spaces with image icon
        SpannableString sb = new SpannableString("   " + tabTitles[position]);
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }*/




