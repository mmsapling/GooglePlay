package com.tylz.googleplay.holder;

import android.view.View;
import android.widget.TextView;

import com.tylz.googleplay.R;
import com.tylz.googleplay.base.BaseHolder;
import com.tylz.googleplay.utils.UIUtils;

/**
 * 提供视图
 * 数据绑定
 */
public class HomeHolder  extends BaseHolder<String>{
    public View mRootView;
    private TextView mTvTem1;
    private TextView mTvTem2;
    private String mData;
    public HomeHolder(){
        super();
        mRootView = initHolderView();
    }

    /**
     * 初始化持有的视图
     */
    public View initHolderView() {
        mRootView = View.inflate(UIUtils.getContext(), R.layout.item_temp,null);
        //找出孩子对象
        mTvTem1 = (TextView) mRootView.findViewById(R.id.tmp_tv_1);
        mTvTem2 = (TextView) mRootView.findViewById(R.id.tmp_tv_2);

        return mRootView;
    }



    /**
     * 进行数据和视图的绑定
     */
    public void refreshHolderView(String data) {
        //data
        // view
        //data + view
        mTvTem1.setText("我是头" +data);
        mTvTem2.setText("我是尾" +data);
    }
}
