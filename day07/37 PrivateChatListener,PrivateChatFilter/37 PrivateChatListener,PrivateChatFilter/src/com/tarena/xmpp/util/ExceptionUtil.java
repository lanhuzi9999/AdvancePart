package com.tarena.xmpp.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.tarena.xmpp.model.ApplicationData;

public class ExceptionUtil {
	
	public static void handle(Exception e)
	{
		//���쳣��Ϣ����ַ���
		String str1=e.toString();
		
		StringWriter sw=new StringWriter();
		PrintWriter pw=new PrintWriter(sw);
		e.printStackTrace(pw);
		String str2=sw.toString();
		//���ʼ� 1��new intent,2�Ƽ���javamail ��̨����
		//������־ writesdcard
		if (ApplicationData.isDebug)
		{
		e.printStackTrace();
		}
	}

}
