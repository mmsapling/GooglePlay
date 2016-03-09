package com.tylz.googleplay.base;

import android.widget.BaseAdapter;

import java.util.List;

/**
 * 对BaseAdpter进行常用的封装
 */
public abstract class MyBaseAdapter<ITEMBEANTYPE> extends BaseAdapter {
    public List<ITEMBEANTYPE> mDataSource;

    public MyBaseAdapter(List dataSource) {
        mDataSource = dataSource;
    }

    @Override
    public int getCount() {
        if(mDataSource != null){
            return mDataSource.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(mDataSource != null){
            return mDataSource.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
