package com.tarena.xmpp.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.tarena.xmpp.model.ApplicationData;

public class ExceptionUtil {
	
	public static void handle(Exception e)
	{
		//把异常信息变成字符串
		String str1=e.toString();
		
		StringWriter sw=new StringWriter();
		PrintWriter pw=new PrintWriter(sw);
		e.printStackTrace(pw);
		String str2=sw.toString();
		//发邮件 1，new intent,2推荐用javamail 后台发送
		//保存日志 writesdcard
		if (ApplicationData.isDebug)
		{
		e.printStackTrace();
		}
	}

}
