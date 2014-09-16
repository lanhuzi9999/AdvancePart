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
		//手机网页 与电脑上的网页是不一样的
		//电脑上的网页 html 宽度是1024
		//手机网页Xhtml(不支持frameset,部分不支持flash)，宽度是不固定的 320,480,1080
		//手机网页用css,div,具体参考wap.sohu.com
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
