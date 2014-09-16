package com.example.webviewdemo;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends Activity {
	WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 webView=(WebView) findViewById(R.id.webView1);
		//�ֻ���ҳ ������ϵ���ҳ�ǲ�һ����
		//�����ϵ���ҳ html �����1024
		//�ֻ���ҳXhtml(��֧��frameset,���ֲ�֧��flash)������ǲ��̶��� 320,480,1080
		//�ֻ���ҳ��css,div,����ο�wap.sohu.com
		//webView.loadUrl("http://wap.sohu.com");
		//webView.loadUrl("http://124.207.192.18:8080/tarenaEnglish/");
		webView.loadUrl("file:///android_asset/test.html");
		WebSettings settings=webView.getSettings();
		settings.setJavaScriptEnabled(true);
		
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				return super.shouldOverrideUrlLoading(view, url);
			}
		});
		
		//����javaScript
		Button btn=(Button) findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					webView.loadUrl("javascript:changeUsername();");
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		//��javaScript ����Phone.call
		Phone phone=new Phone();
		//javascript,�����ͨ��androidphoneClass������phone
		webView.addJavascriptInterface(phone, "phoneAndroidObject");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	final class Phone
	{
		public void call(String mobile)
		{
			try {
				Log.i("test", "call");
				Uri uri=Uri.parse("tel:"+mobile);
				Intent intent=new Intent(Intent.ACTION_CALL,uri);
				startActivity(intent);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
