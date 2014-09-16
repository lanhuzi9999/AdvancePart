package com.tarena.xmpp.util;

import com.tarena.xmpp.model.ApplicationData;

import android.util.Log;
//日志 log4j:第天生成一个日志文件，2013-12-16.log
//当日志文件内容很大，2013-12-16-1.log 2013-12-16-2.log
//用日志保存到文件 中，分析数据

public class LogUtil {
	public static void i(String tag,String msg)
	{
		if (ApplicationData.isDebug)
		{
		Log.i(tag,msg);
		}
	}
	public  static void i(String tag,long msg)
	{
		i(tag,String.valueOf(msg));
	}
	public  static void i(String tag,int msg)
	{
		i(tag,String.valueOf(msg));
	}
	public  static void i(String tag,boolean msg)
	{
		i(tag,String.valueOf(msg));
	}


}
