package com.tylz.googleplay.manager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的代理类
 * 帮你做一些事情
 * 帮ThreadPool 做一些事情，进行封装，暴露用户真正关心的方法
 * 用户想使用线程池，真正关心的无非就是（提交任务，只执行任务，移除任务）
 * 单例化
 */
public class ThreadPoolProxy {

    public ThreadPoolProxy(int corePoolSize,int maximumPoolSize,int keepAliveTime) {
        mCorePoolSize =    corePoolSize;
        mMaximumPoolSize = maximumPoolSize;
        mKeepAliveTime =    keepAliveTime;
    }

    static ThreadPoolExecutor mThreadPoolExecutor;//只需要初始化一次
    private static int mCorePoolSize;
    private static int mMaximumPoolSize;
    private static long mKeepAliveTime;


    private static void initThreadPoolExecutor() {
        if(mThreadPoolExecutor == null || mThreadPoolExecutor.isShutdown() || mThreadPoolExecutor.isTerminated()){
            synchronized (ThreadPoolProxy.class){
                if(mThreadPoolExecutor == null || mThreadPoolExecutor.isShutdown() || mThreadPoolExecutor.isTerminated()){
                    TimeUnit unit = TimeUnit.MILLISECONDS;
                    BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardOldestPolicy();
                    mThreadPoolExecutor = new ThreadPoolExecutor(mCorePoolSize,mMaximumPoolSize,mKeepAliveTime,unit,workQueue,threadFactory,handler);
                }
            }
        }
    }
    /**提交任务*/
    public Future<?> submit(Runnable task){
        initThreadPoolExecutor();
        Future<?> submitResult =  mThreadPoolExecutor.submit(task);
        return  submitResult;
    }

    /**执行任务*/
    public  void execute(Runnable task){
        initThreadPoolExecutor();
        mThreadPoolExecutor.execute(task);
    }
    /**移除任务*/
    public  void remove(Runnable task){
        initThreadPoolExecutor();
        mThreadPoolExecutor.remove(task);
    }
}
