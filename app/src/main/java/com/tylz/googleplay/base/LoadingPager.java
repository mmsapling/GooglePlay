package com.tylz.googleplay.base;


import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.tylz.googleplay.R;
import com.tylz.googleplay.manager.ThreadPoolProxyFactory;
import com.tylz.googleplay.utils.LogUtils;
import com.tylz.googleplay.utils.UIUtils;

/**
 * 1.数据加载
 * 2.视图的展示逻辑 加载视图，空试图，错误视图，成功视图
 * 3.为了方便，直接继承FragmentLayout ，变成4中视图的容器
 */
public abstract class LoadingPager extends FrameLayout {

    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;

    public static final int STATE_LOADING = 0;                // 加载中
    public static final int STATE_EMPTY   = 1;                // 空视图
    public static final int STATE_ERROR   = 2;                // 错误视图
    public static final int STATE_SUCCESS = 3;                // 成功视图
    public int mCurState = STATE_LOADING;
    public View mSuccessView;

    public LoadingPager(Context context) {
        super(context);
        initCommonView();
    }

    /**
     * 初始化常规视图
     */
    private void initCommonView() {
        //加载视图
        mLoadingView = View.inflate(UIUtils.getContext(), R.layout.pager_loading, null);
        addView(mLoadingView);
        //错误页面
        mErrorView = View.inflate(UIUtils.getContext(), R.layout.pager_error, null);
        addView(mErrorView);
        //空页面
        mEmptyView = View.inflate(UIUtils.getContext(), R.layout.pager_empty, null);
        addView(mEmptyView);
        //初始化成功视图
        //根据当前的状态，显示其中一个视图
        refreshUIByState();
    }

    private void refreshUIByState() {

         //显示loading视图
          mLoadingView.setVisibility((mCurState == STATE_LOADING) ? View.VISIBLE : View.GONE);

          mEmptyView.setVisibility((mCurState == STATE_EMPTY) ? View.VISIBLE : View.GONE);

          mErrorView.setVisibility((mCurState == STATE_ERROR) ? View.VISIBLE : View.GONE);

          if(mCurState == STATE_SUCCESS && mSuccessView == null){
              //初始化成功视图并且进行展示
              mSuccessView = initSuccessView();
              addView(mSuccessView);
          }

          if(mSuccessView != null){
              //控制成功视图的显示/隐藏
              mSuccessView.setVisibility((mCurState == STATE_SUCCESS) ? View.VISIBLE : View.GONE);
          }

    }
    /**
     * 展示具体的成功视图
     * 数据加载完成，并且是数据加载成功的时候
     */
    protected abstract View initSuccessView();



    /**
     *
     */
    public void triggerLoadData(){
        LogUtils.s("######触发加载数据了#####");
        ThreadPoolProxyFactory.createNormalThreadPoolProxy().execute(new LoadDataTask());
    }
    private class LoadDataTask implements  Runnable{

        @Override
        public void run() {
            //真正的在子线程里面加载数据
           LoadedResult tempState = initData();
            //处理网络加载之后的结果
            mCurState = tempState.getState();
            //刷新ui
            UIUtils.postTaskSafely(new Runnable() {
                @Override
                public void run() {
                    refreshUIByState();
                }
            });
       }

    }
    /**
     * 真正在子线程里面开始加载数据
     * @return
     */
    protected abstract LoadedResult initData();
    /**
     * 定义枚举，控制数据加载完成之后的返回值只能在1，2，3中的某一个
     *
     */
    public enum LoadedResult{
        EMPTY(STATE_EMPTY),ERROR(STATE_ERROR),SUCCESS(STATE_SUCCESS);
        int state;
        private LoadedResult(int state){
            this.state = state;
        }
        public int getState(){
            return state;
        }
    }
}
