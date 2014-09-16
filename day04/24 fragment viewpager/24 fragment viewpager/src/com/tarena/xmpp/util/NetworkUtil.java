package com.tarena.xmpp.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
   public void checkNetState(final Context context)
   {
	   ConnectivityManager manager=(ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
	   NetworkInfo activityNetworkInfo=manager.getActiveNetworkInfo();
	   if (activityNetworkInfo==null)
	   {
		   //显示对话框，让用户去设置风络
		   AlertDialog.Builder dialog=new AlertDialog.Builder(context);
		   dialog.setTitle("打开网络");
		   dialog.setMessage("当前没有网络，请打开网络");
		   dialog.setPositiveButton("设置", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				try {
					Intent intent=new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
					context.startActivity(intent);
				} catch (Exception e) {
					// TODO: handle exception
					ExceptionUtil.handle(e);;
				}
				
			}
		});
		   
		   dialog.setNegativeButton("不设置", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				try {
					dialog.dismiss();
				} catch (Exception e) {
					// TODO: handle exception
					ExceptionUtil.handle(e);
				}
				
			}
			
		});
		   dialog.show();
	   }
	   
   }
}
