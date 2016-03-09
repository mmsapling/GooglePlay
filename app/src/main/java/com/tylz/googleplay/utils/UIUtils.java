package com.tylz.googleplay.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;

import com.tylz.googleplay.base.MyApplication;


/*
 * @创建者     伍碧林
 * @创建时间   2015/12/8 08:58
 * @描述	     和ui相关的一些工具方法
 *
 * @更新者     $Author: admin $
 * @更新时间   $Date: 2015-12-08 09:17:13 +0800 (星期二, 08 十二月 2015) $
 * @更新描述   ${TODO}
 */
public class UIUtils {
	/**得到上下文*/
	public static Context getContext() {
		return MyApplication.getContext();
	}

	/**得到Resource对象*/
	public static Resources getResources() {
		return getContext().getResources();
	}

	/**得到String.xml中定义的字符信息*/
	public static String getString(int resId) {
		return getResources().getString(resId);
	}

	/**得到String.xml中定义的字符数组信息*/
	public static String[] getStrings(int resId) {
		return getResources().getStringArray(resId);
	}

	/**得到color.xml中定义的颜色信息*/
	public static int getColor(int resId) {
		return getResources().getColor(resId);
	}

	/**得到主线程的线程id*/
	public static long getMainThreadId() {
		return MyApplication.getMainThreadId();
	}

	/**得到主线程的一个handler*/
	public static Handler getMainThreadHandler() {
		return MyApplication.getHandler();
	}

	/**安全的执行一个任务*/
	public static void postTaskSafely(Runnable task) {
		// 得到当前的线程
		long curThreadId = android.os.Process.myTid();
		// 得到主线程的线程id
		long mainThreadId = getMainThreadId();
		if (curThreadId == mainThreadId) {
			// 如果当前是在主线程-->直接执行
			task.run();
		} else {
			// 如果当前是在子线程-->通过消息机制,把任务发送到主线程执行
			getMainThreadHandler().post(task);
		}
	}
	public static String getPackageName(){
		return getContext().getPackageName();
	}
}
