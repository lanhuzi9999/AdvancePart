package com.tarena.xmpp.util;
/**
 * 1,ÿ�������Ŀ�ʼ������
 * 2��ĳЩ����飬�������ӣ��������ݣ�dao.query,dao,insert,xmlparser,onCreate()
 * @author pjy
 *
 */
public class TimeUtil {
long startTime,endTime;
	//�ÿ�ʼʱ��
public TimeUtil() {
	startTime=System.currentTimeMillis();
}
	//��ʾ
public void showUseTime(String tag)
{
endTime=System.currentTimeMillis();
LogUtil.i(tag, endTime-startTime);
}
}
