package com.tarena.xmpp.util;
/**
 * 1,每个方法的开始，结束
 * 2，某些代码块，建立连接，发送数据，dao.query,dao,insert,xmlparser,onCreate()
 * @author pjy
 *
 */
public class TimeUtil {
long startTime,endTime;
	//得开始时间
public TimeUtil() {
	startTime=System.currentTimeMillis();
}
	//显示
public void showUseTime(String tag)
{
endTime=System.currentTimeMillis();
LogUtil.i(tag, endTime-startTime);
}
}
