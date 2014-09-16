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
				//������
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					// TODO Auto-generated method stub
					if (url.contains("tel:"))
					{
						//��绰
						String mobile=url.substring(url.indexOf(":")+1);
						
						Uri uri=Uri.parse("tel:"+mobile);
						startActivity(new Intent(Intent.ACTION_CALL, uri));
						//������android�Ѿ������ˣ�webview������
						return true;
					}
					//webView����url
					return false;
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handle(e);
		}
	}

}
