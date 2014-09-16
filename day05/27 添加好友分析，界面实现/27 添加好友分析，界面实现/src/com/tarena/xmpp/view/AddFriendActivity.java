package com.tarena.xmpp.view;

import com.tarena.xmpp.R;

import android.os.Bundle;

public class AddFriendActivity extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.add_friend);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
