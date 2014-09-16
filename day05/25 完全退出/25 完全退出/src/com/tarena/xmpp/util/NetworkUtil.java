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
		   //��ʾ�Ի������û�ȥ���÷���
		   AlertDialog.Builder dialog=new AlertDialog.Builder(context);
		   dialog.setTitle("������");
		   dialog.setMessage("��ǰû�����磬�������");
		   dialog.setPositiveButton("����", new OnClickListener() {
			
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
		   
		   dialog.setNegativeButton("������", new OnClickListener() {
			
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
