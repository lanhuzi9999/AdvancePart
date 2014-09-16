package com.tarena.xmpp.view;

import com.tarena.xmpp.R;

import android.app.Activity;
import android.os.Bundle;

public class LoginActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.login);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
