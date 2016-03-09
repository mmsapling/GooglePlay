package com.tylz.googleplay.base;

import android.view.View;


/**
 * Created by xuanwen on 2016/3/6.
 */
public abstract class BaseHolder<HOLDERBEANTYPE> {
    public View mRootView; //持有的视图
    public HOLDERBEANTYPE mData;

    public BaseHolder() {
        mRootView = initHolderView();
        //找一个holder，然后把它绑定到convertview身上
        mRootView.setTag(this); //最难理解的地方
    }

    /**
     * 初始化持有的视图
     * 在BaseHolder里面不知道如何剧吐的初始化持有的视图
     * 必须实现但是不知道具体实现，定义成为抽象方法，交给子类具体实现
     */
    public abstract View initHolderView();

    /**
     * 接收数据，然后进行数据和视图的绑定
     */
    public void setDataAndRefreshUI(HOLDERBEANTYPE data) {
        //保存传递过来的数据到成员变量
        mData = data;
        refreshHolderView(data);
    }

    /**
     * 进行数据和视图的绑定
     * 不知道怎么实现，必须实现，交给子类去实现
     */
    public abstract void refreshHolderView(HOLDERBEANTYPE data) ;
}
