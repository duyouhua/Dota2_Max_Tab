package xyz.isunxu.dota2_max_tab.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import xyz.isunxu.dota2_max_tab.R;
import xyz.isunxu.dota2_max_tab.fragment.SimpleFragment;
import xyz.isunxu.dota2_max_tab.view.TriangleTabView;

public class MainActivity extends AppCompatActivity {

    private TriangleTabView mTriangleTabView;
    private ViewPager mViewPager;

    private FragmentPagerAdapter mFragmentPagerAdapter;

    private String[] titles = new String[]{"比赛概况","雷达图","最高纪录","队友对手",};
    private List<Fragment> mFragmentList = new ArrayList<>();


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initDatas();

        addListeners();

    }


    private void addListeners() {

        heightTextColor(0);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                mTriangleTabView.scroll(position, positionOffset);
            }


            @Override public void onPageSelected(int position) {
                heightTextColor(position);
            }


            @Override public void onPageScrollStateChanged(int state) {

            }
        });



        for(int i = 0;i<titles.length;i++) {
            final int j =i;

            mTriangleTabView.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {

                    heightTextColor(j);
                }
            });

        }
    }


    private void initDatas() {

       for(int i = 0;i<titles.length;i++) {
           final int j =i;
           mFragmentList.add(SimpleFragment.getInstance(i));
       }


        mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }


            @Override public int getCount() {
                return titles.length;
            }
        };

        mViewPager.setAdapter(mFragmentPagerAdapter);
    }


    private void initTextViewColor() {
        for (int i = 0; i < titles.length; i++) {
            final TextView textView = (TextView) mTriangleTabView.getChildAt(i);
            textView.setTextColor(Color.BLACK);
        }
    }

    private void heightTextColor(int postion) {
        initTextViewColor();

        TextView textView = (TextView) mTriangleTabView.getChildAt(postion);
        textView.setTextColor(Color.parseColor("#ffffffff"));
        mViewPager.setCurrentItem(postion);
    }

    private void initViews() {

        mTriangleTabView = (TriangleTabView) findViewById(R.id.triangletabview);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
    }
}
