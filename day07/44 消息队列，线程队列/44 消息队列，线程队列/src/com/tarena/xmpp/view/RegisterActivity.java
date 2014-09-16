package com.tarena.xmpp.view;

import java.util.HashMap;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.tarena.xmpp.R;
import com.tarena.xmpp.model.ApplicationData;
import com.tarena.xmpp.model.RegisterBiz;
import com.tarena.xmpp.util.ExceptionUtil;
import com.tarena.xmpp.util.Tools;

public class RegisterActivity extends BaseActivity implements OnClickListener{
	Button btnLogin,btnSubmit;
	EditText etUsername,etPassword,etName;
	RegisterReceiver receiver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try {
			this.setContentView(R.layout.register);
			setupView();
			addListener();
			receiver=new RegisterReceiver();
			this.registerReceiver(receiver, new IntentFilter(ApplicationData.ACTION_REGISETER));
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handle(e);
		}
	}
@Override
protected void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
	try {
		this.unregisterReceiver(receiver);
	} catch (Exception e) {
		// TODO: handle exception
	}
}
	private void addListener() {
		// TODO Auto-generated method stub
		btnSubmit.setOnClickListener(this);
	}

	private void setupView() {
		// TODO Auto-generated method stub
		btnLogin=(Button) findViewById(R.id.btn_register_login);
		btnSubmit=(Button) findViewById(R.id.btn_register_submit);
		
		etUsername=(EditText) findViewById(R.id.et_register_username);
		etPassword=(EditText) findViewById(R.id.et_register_password);
		etName=(EditText) findViewById(R.id.et_register_name);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.btn_register_submit:
			String username=etUsername.getText().toString();
			String password=etPassword.getText().toString();
			String name=etName.getText().toString();
			//数据的检查，学员自己完成
			HashMap map=new HashMap();
			map.put("name", name);
			
			RegisterBiz registerBiz=new RegisterBiz();
			registerBiz.register(username, password, map);
			break;
		}
	}
class RegisterReceiver extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		try {
			boolean isSuccess=intent.getBooleanExtra(ApplicationData.KEY_ISSUCCESS, false);
			if (isSuccess)
			{
				Tools.showInfo(context, "注册成功");
			}else
			{
				Tools.showInfo(context, "注册失败");

			}
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handle(e);
		}
	}
	}
}
