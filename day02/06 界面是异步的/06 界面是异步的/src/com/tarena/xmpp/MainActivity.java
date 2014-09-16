package com.tarena.xmpp;

import com.tarena.xmpp.view.LoginActivity;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
Handler handler=new Handler(){
	public void handleMessage(android.os.Message msg) {
		Intent intent=new Intent(MainActivity.this,LoginActivity.class);
		startActivity(intent);
	};
};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logo);
		Log.i("MainActivity","onCreate id="+Thread.currentThread().getId()+"");
//		Handler handler=new Handler();
//		handler.postDelayed(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				try {
//					Log.i("MainActivity","run id="+Thread.currentThread().getId()+"");
//
//					int count=0;
//					while(true)
//					{
//						count++;
//						Thread.currentThread().sleep(1000);
//						Log.i("MainActivity",count+"");
//					}
//					//Intent intent=new Intent(MainActivity.this,LoginActivity.class);
//					//startActivity(intent);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}, 1000);
		
		//第二种 推荐
		new Thread(){
			public void run() {
				try {
				Log.i("MainActivity","run id="+Thread.currentThread().getId()+"");

//				int count=0;
//				while(true)
//				{
//					count++;
//					Thread.currentThread().sleep(1000);
//					Log.i("MainActivity",count+"");
//				}
				
				sleep(2000);
				handler.sendEmptyMessage(-1);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			};
		}.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
