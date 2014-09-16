package com.tarena.ndk;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	static
	{//lib  hello-jni   .so
		System.loadLibrary("hello-jni");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv=new TextView(this);
		tv.setText(getStringFromC());
		tv.setTextSize(90);
		tv.setTextColor(0xFFFF0000);
		setContentView(tv);
	}
	//native是由c来实现
	public native String getStringFromC();
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
