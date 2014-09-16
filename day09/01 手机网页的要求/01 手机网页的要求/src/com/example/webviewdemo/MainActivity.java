package com.example.webviewdemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		WebView webView=(WebView) findViewById(R.id.webView1);
		//�ֻ���ҳ ������ϵ���ҳ�ǲ�һ����
		//�����ϵ���ҳ html �����1024
		//�ֻ���ҳXhtml(��֧��frameset,���ֲ�֧��flash)������ǲ��̶��� 320,480,1080
		//�ֻ���ҳ��css,div,����ο�wap.sohu.com
		//webView.loadUrl("http://wap.sohu.com");
		webView.loadUrl("http://124.207.192.18:8080/tarenaEnglish/");
		
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				return super.shouldOverrideUrlLoading(view, url);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
