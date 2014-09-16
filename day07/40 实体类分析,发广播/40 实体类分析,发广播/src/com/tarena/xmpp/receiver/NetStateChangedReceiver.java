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
			//�ж��ǹرջ��Ǵ�
			ConnectivityManager manager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo=manager.getActiveNetworkInfo();
			if (networkInfo==null)
			{
				ApplicationData.isConnected=false;
				ApplicationData.isLoginSuccess=false;
				//�ر�
				String str="�û��ر�������";
				Tools.showInfo(context, str);
				LogUtil.i("����״̬", str);
			}else
			{
				NetworkInfo wifiNetwork=manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
				NetworkInfo mobileNetwork=manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
				String str="�Ѿ�������";
				if (wifiNetwork.isConnected())
				{
					str=str+"��wifi";
				}
				if (mobileNetwork.isConnected())
				{
					str=str+"���ƶ�����";

				}
				Tools.showInfo(context, str);
				LogUtil.i("����״̬", str);
				
				//�������ӣ����µ�¼
			}
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handle(e);
		}
		
	}

}
