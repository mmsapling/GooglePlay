package com.tylz.googleplay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;

import com.astuetz.PagerSlidingTabStrip;
import com.tylz.googleplay.base.BaseFragment;
import com.tylz.googleplay.factory.FragmentFactory;
import com.tylz.googleplay.utils.LogUtils;
import com.tylz.googleplay.utils.UIUtils;

public class MainActivity extends AppCompatActivity {
    private PagerSlidingTabStrip mMainTabs;
    private ViewPager mMainViewpager;
    private String[] mMainTitleArr;
    private MainPagerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainTabs = (PagerSlidingTabStrip) findViewById(R.id.main_tabs);
        mMainViewpager = (ViewPager) findViewById(R.id.main_viewpager);
        initActionBar();
        initData();
        initListener();
    }

    private MyOnPagerChangeListener mMyOnPagerChangeListener = new MyOnPagerChangeListener();
    private void initListener() {
        mMainTabs.setOnPageChangeListener(mMyOnPagerChangeListener);
        mMainViewpager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mMyOnPagerChangeListener.onPageSelected(0);
                mMainViewpager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }
    class MyOnPagerChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            BaseFragment fragment = FragmentFactory.createFragment(position);
            if(fragment != null){
                fragment.mLoadingPager.triggerLoadData();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    private void initData() {
        mMainTitleArr = UIUtils.getStrings(R.array.main_titles);
        mAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mMainViewpager.setAdapter(mAdapter);
        mMainTabs.setViewPager(mMainViewpager);
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("GooglePlay");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

    }
    class MainPagerAdapter extends FragmentStatePagerAdapter{

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = FragmentFactory.createFragment(position);
            LogUtils.s(fragment.getClass().getSimpleName());
            return fragment;
        }

        @Override
        public int getCount() {
            if(mMainTitleArr != null){
                return mMainTitleArr.length;
            }
            return 0;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mMainTitleArr[position];
        }
    }
}
