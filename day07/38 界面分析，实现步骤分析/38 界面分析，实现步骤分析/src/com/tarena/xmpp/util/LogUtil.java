package com.tarena.xmpp.util;

import com.tarena.xmpp.model.ApplicationData;

import android.util.Log;
//��־ log4j:��������һ����־�ļ���2013-12-16.log
//����־�ļ����ݺܴ�2013-12-16-1.log 2013-12-16-2.log
//����־���浽�ļ� �У���������

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
