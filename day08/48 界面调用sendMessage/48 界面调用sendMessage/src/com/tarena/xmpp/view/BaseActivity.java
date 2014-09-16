package com.tarena.xmpp.view;

import com.tarena.xmpp.model.ApplicationData;
import com.tarena.xmpp.util.LogUtil;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ApplicationData.instance.list.add(this);
		LogUtil.i("exit", "BaseActivity add"+this.toString());
	}

}
