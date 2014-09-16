package com.tarena.xmpp.receiver;

import com.tarena.xmpp.model.ApplicationData;
import com.tarena.xmpp.util.ExceptionUtil;
import com.tarena.xmpp.util.LogUtil;
import com.tarena.xmpp.util.Tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetStateChangedReceiver extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		try {
			//判断是关闭还是打开
			ConnectivityManager manager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo=manager.getActiveNetworkInfo();
			if (networkInfo==null)
			{
				ApplicationData.isConnected=false;
				ApplicationData.isLoginSuccess=false;
				//关闭
				String str="用户关闭网络了";
				Tools.showInfo(context, str);
				LogUtil.i("网络状态", str);
			}else
			{
				NetworkInfo wifiNetwork=manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
				NetworkInfo mobileNetwork=manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
				String str="已经打开网络";
				if (wifiNetwork.isConnected())
				{
					str=str+"有wifi";
				}
				if (mobileNetwork.isConnected())
				{
					str=str+"有移动网络";

				}
				Tools.showInfo(context, str);
				LogUtil.i("网络状态", str);
				
				//重新连接，重新登录
			}
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handle(e);
		}
		
	}

}
