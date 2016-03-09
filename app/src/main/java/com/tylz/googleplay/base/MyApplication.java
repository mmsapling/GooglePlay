package com.tylz.googleplay.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/*
 * @创建者     伍碧林
 * @创建时间   2015/12/8 08:47
 * @描述	      全局,单例,全局的容器
 * @描述	      放置全局的对象
 * @描述	      放置常用的对象
 *
 * @更新者     $Author: admin $
 * @更新时间   $Date: 2015-12-08 09:17:13 +0800 (星期二, 08 十二月 2015) $
 * @更新描述   ${TODO}
 */
public class MyApplication extends Application {

	private static Context	mContext;
	private static Handler	mHandler;
	private static long		mMainThreadId;

	public static Context getContext() {
		return mContext;
	}

	public static Handler getHandler() {
		return mHandler;
	}

	public static long getMainThreadId() {
		return mMainThreadId;
	}

	@Override
	public void onCreate() {// 程序的入口方法

		// 1.上下文
		mContext = getApplicationContext();

		// 2.主线程的Handler
		mHandler = new Handler();

		// 3.得到主线程的id
		mMainThreadId = android.os.Process.myTid();
		/**
		 myTid: thread
		 myPid: process
		 myUid: user
		 */

		super.onCreate();
	}
}
