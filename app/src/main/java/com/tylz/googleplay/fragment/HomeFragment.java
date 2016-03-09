package com.tylz.googleplay.fragment;

import android.graphics.Color;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tylz.googleplay.base.BaseFragment;
import com.tylz.googleplay.base.LoadingPager;
import com.tylz.googleplay.base.MyBaseAdapter;
import com.tylz.googleplay.holder.HomeHolder;
import com.tylz.googleplay.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class HomeFragment extends BaseFragment {
    private List<String> mDatas;
    @Override
    protected View initSuccessView() {
        ListView listView = new ListView(UIUtils.getContext());
        //常规的设置
        listView.setCacheColorHint(Color.TRANSPARENT);
        listView.setDividerHeight(0);
        listView.setFadingEdgeLength(0);
        listView.setFastScrollEnabled(true);
        //数据和视图的绑定
        mDatas = new ArrayList<String>();
        for(int i = 0; i < 10;i++){
            mDatas.add(i + "");
        }
        listView.setAdapter(new HomeAdapter(mDatas));
        return listView;
    }

    /**
     * 真正在子线程里面开始加载想加载的数据
     * 返回值只能是 1，2，3中的某一个，如果设置为其他值，会影响视图的显示逻辑
     */
    @Override
    protected LoadingPager.LoadedResult initData() {
        SystemClock.sleep(2000);
        //随机返回3中状态中的一种
        LoadingPager.LoadedResult[] loadedResultArr = {LoadingPager.LoadedResult.EMPTY,
                LoadingPager.LoadedResult.ERROR, LoadingPager.LoadedResult.SUCCESS};
        Random random = new Random();

        return LoadingPager.LoadedResult.SUCCESS;
    }
    class HomeAdapter extends MyBaseAdapter<String>{

        public HomeAdapter( List dataSource) {
            super( dataSource);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HomeHolder homeHolder = null;
            if(convertView == null){
                homeHolder = new HomeHolder();
            }else{
                homeHolder = (HomeHolder) convertView.getTag();
            }
            //视图和数据的绑定
            homeHolder.setDataAndRefreshUI(mDataSource.get(position));
            return homeHolder.initHolderView();
        }
    }
}