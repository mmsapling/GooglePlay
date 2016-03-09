package com.tylz.googleplay.fragment;

import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.tylz.googleplay.base.BaseFragment;
import com.tylz.googleplay.base.LoadingPager;
import com.tylz.googleplay.utils.UIUtils;

import java.util.Random;

/**
 * Created by xuanwen on 2016/1/13.
 */
public class RecomendFragment  extends BaseFragment {

    @Override
    protected View initSuccessView() {
        TextView view = new TextView(UIUtils.getContext());
        view.setGravity(Gravity.CENTER);
        view.setText(this.getClass().getSimpleName());
        return view;
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

        return loadedResultArr[random.nextInt(loadedResultArr.length)];
    }
}