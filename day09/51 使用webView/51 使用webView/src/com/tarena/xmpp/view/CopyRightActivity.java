package com.tarena.xmpp.view;

import com.tarena.xmpp.R;
import com.tarena.xmpp.util.ExceptionUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CopyRightActivity extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try {
			this.setContentView(R.layout.copyright);
			WebView webView=(WebView) findViewById(R.id.webView1);
			webView.loadUrl("file:///android_asset/copyright.html");
			webView.setWebViewClient(new WebViewClient(){
				//超链接
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					// TODO Auto-generated method stub
					if (url.contains("tel:"))
					{
						//打电话
						String mobile=url.substring(url.indexOf(":")+1);
						
						Uri uri=Uri.parse("tel:"+mobile);
						startActivity(new Intent(Intent.ACTION_CALL, uri));
						//超链接android已经处理了，webview不处理
						return true;
					}
					//webView加载url
					return false;
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handle(e);
		}
	}

}
