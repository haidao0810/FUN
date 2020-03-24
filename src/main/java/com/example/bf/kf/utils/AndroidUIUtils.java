package com.example.bf.kf.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 *
 * Copyright (C) 2010-2018 SevenChu All Rights Reserved.
 *
 * FileName: AndroidCoreApplication.java
 *
 * Description: Base of Application;
 *
 * History:
 *
 * Vsesion:1.0.0   SevenChu(chutianfan@gmail.com) 2010-01-01   Create
 *
 */
public class AndroidUIUtils {

	// 根据手机的分辨率将dp的单位转成px(像素)
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	// 根据手机的分辨率将px(像素)的单位转成dp
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	// 将px值转换为sp值
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	// 将sp值转换为px值
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 获取当前上下文的屏幕宽度
	 * 
	 *            上下文
	 * @return 屏幕宽度
	 */
	public static int getScreenWidth(Activity activity) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
		return displayMetrics.widthPixels;
	}

	/**
	 * 获取当前上下文的屏幕高度
	 * 
	 *            上下文
	 * @return 屏幕高度
	 */
	public static int getScreenHeight(Activity activity) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
		return displayMetrics.heightPixels;
	}


	public static int getScreenWidth(Context context) {
		return getMetrics(context).widthPixels;
	}

	public static int getScreenHeight(Context context) {
		return getMetrics(context).heightPixels;
	}

	public static DisplayMetrics getMetrics(Context context) {
		DisplayMetrics metric = new DisplayMetrics();
		getDisplay(context).getMetrics(metric);
		int width = metric.widthPixels;     // 屏幕宽度（像素）
		int height = metric.heightPixels;   // 屏幕高度（像素）
		float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
		int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
		return metric;
	}

	public static Display getDisplay(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay();
	}

	/**
	 * 获取屏幕尺寸
	 * 
	 *            上下文
	 * @return 屏幕尺寸
	 */
	private static DisplayMetrics getDisplayMetrics(Activity activity) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay()
				.getMetrics(displayMetrics);
		return displayMetrics;
	}

	/**
	 * 获取设备 ID号
	 * @param context
	 * @return
     */
	public static String getDeviceAndroidId(Context context){
		String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
		return androidId == null ? StringUtils.EMPTY : androidId;
	}

	/**
	 * 获取设备 ID号
	 * @return
	 */
	public static String getGenrateDeviceId(){
		int deviceId = Build.BOARD.length() % 10 +
				Build.BRAND.length() % 10 +
				Build.CPU_ABI.length() % 10 +
				Build.DEVICE.length() % 10 +
				Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +
				Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +
				Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +
				Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +
				Build.USER.length() % 10;

		return String.valueOf(deviceId);
	}


	/**
	 * 获取主板SerialNumber
	 */
	public static String getSerialNumber(){
		String serial="";
		try {
			Class<?> c=Class.forName("android.os.SystemProperties");
			Method method=c.getMethod("get",String.class);
			serial= (String) method.invoke(c,"ro.serialno");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return serial;
	}
	/**
	 * 设置界面的透明度
	 *
	 * @param activity
	 * @param alpha    0.0-1.0
	 */
	public static void setLayoutAlpha(Activity activity, float alpha) {
		if (null == activity) {
			return;
		}
		WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
		lp.alpha = alpha; // 0.0-1.0
		lp.dimAmount = alpha;
		activity.getWindow().setAttributes(lp);
		activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
	}


}
