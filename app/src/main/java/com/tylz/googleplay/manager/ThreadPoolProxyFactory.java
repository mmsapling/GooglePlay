package com.tylz.googleplay.manager;

/**
 * 创建普通的线程池的代理
 * 创建下载的线程池的代理
 */
public class ThreadPoolProxyFactory {
    static ThreadPoolProxy mNormalThreadPoolProxy;
    static ThreadPoolProxy mDownloadThreadPoolProxy;
    public static ThreadPoolProxy createNormalThreadPoolProxy(){
        if(mNormalThreadPoolProxy == null){
            synchronized (ThreadPoolProxyFactory.class){
                if(mNormalThreadPoolProxy == null){
                    mNormalThreadPoolProxy = new ThreadPoolProxy(5,5,3000);
                }
            }
        }
        return mNormalThreadPoolProxy;
    }
    public static ThreadPoolProxy createDownloadThreadPoolProxy(){
        if(mDownloadThreadPoolProxy == null){
            synchronized (ThreadPoolProxyFactory.class){
                if(mDownloadThreadPoolProxy == null){
                    mDownloadThreadPoolProxy = new ThreadPoolProxy(3,3,3000);
                }
            }
        }
        return mDownloadThreadPoolProxy;
    }
}
