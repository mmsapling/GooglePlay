package com.tylz.googleplay.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.tylz.googleplay.utils.UIUtils;

/**
 * Created by xuanwen on 2016/1/14.
 */
public abstract class BaseFragment extends Fragment {

    public LoadingPager mLoadingPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mLoadingPager == null) {


            mLoadingPager = new LoadingPager(UIUtils.getContext()) {

                @Override
                protected View initSuccessView() {
                    return BaseFragment.this.initSuccessView();
                }

                @Override
                protected LoadedResult initData() {
                    return BaseFragment.this.initData();
                }
            };
        }else {
            ViewParent parent = mLoadingPager.getParent();
            if(parent != null && parent instanceof  ViewGroup){
                ((ViewGroup)parent).removeView(mLoadingPager);
            }
        }
        return mLoadingPager;
    }

    /**
     * 展示具体的成功视图
     * 此时他也不知道展示什么样的视图
     */
    protected abstract View initSuccessView();

    /**
     * loadingPager里面的initData方法的具体实现，转到这里来
     * 和LoadingPager里面的initData方法同名
     */
    protected abstract LoadingPager.LoadedResult initData();

}
