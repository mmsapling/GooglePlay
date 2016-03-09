package com.tylz.googleplay.factory;

import com.tylz.googleplay.base.BaseFragment;
import com.tylz.googleplay.fragment.AppFragment;
import com.tylz.googleplay.fragment.CategoryFragment;
import com.tylz.googleplay.fragment.GameFragment;
import com.tylz.googleplay.fragment.HomeFragment;
import com.tylz.googleplay.fragment.HotFragment;
import com.tylz.googleplay.fragment.RecomendFragment;
import com.tylz.googleplay.fragment.SubjectFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuanwen on 2016/1/12.
 */
public class FragmentFactory {
    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_APP = 1;
    public static final int FRAGMENT_GAME = 2;
    public static final int FRAGMENT_SUBJECT = 3;
    public static final int FRAGMENT_RECOMMEND = 4;
    public static final int FRAGMENT_CATEGORY = 5;
    public static final int FRAGMENT_HOT = 6;

    private static Map<Integer,BaseFragment> mCacheFragmentMap = new HashMap<>();
    public static BaseFragment createFragment(int position) {
        BaseFragment fragment = null;
        if(mCacheFragmentMap.containsKey(position)){
            fragment = mCacheFragmentMap.get(position);
            return fragment;
        }
        switch (position) {
            case FRAGMENT_HOME:
                fragment = new HomeFragment();
                break;
            case FRAGMENT_APP:
                fragment = new AppFragment();
                break;
            case FRAGMENT_GAME:
                fragment = new GameFragment();
                break;
            case FRAGMENT_SUBJECT:
                fragment = new SubjectFragment();
                break;
            case FRAGMENT_RECOMMEND:
                fragment = new RecomendFragment();
                break;
            case FRAGMENT_CATEGORY:
                fragment = new CategoryFragment();
                break;
            case FRAGMENT_HOT:
                fragment = new HotFragment();
                break;
            default:
                break;
        }
        //保存引用到集合中
        mCacheFragmentMap.put(position,fragment);
        return fragment;
    }
}
