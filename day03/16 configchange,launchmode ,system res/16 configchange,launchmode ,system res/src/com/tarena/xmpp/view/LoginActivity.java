package com.tarena.xmpp.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tarena.xmpp.R;
import com.tarena.xmpp.model.ApplicationData;
import com.tarena.xmpp.model.LoginBiz;

public class LoginActivity extends Activity implements OnClickListener{
	//申明
	Button btnRegister,btnSubmit;
	EditText etUsername,etPassword;
	LoginReceiver loginReceiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try {
			Log.i("LoginActivity", "onCreate");
			setContentView(R.layout.login);
			setupView();
			addListener();
			
			loginReceiver=new LoginReceiver();
			IntentFilter intentFilter=new IntentFilter(ApplicationData.ACTION_LOGIN);
			registerReceiver(loginReceiver, intentFilter);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		Log.i("LoginActivity", "onConfigurationChanged "+newConfig.orientation);

		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}
@Override
protected void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
	try {
		unregisterReceiver(loginReceiver);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}
	private void addListener() {
		// TODO Auto-generated method stub
		btnSubmit.setOnClickListener(this);
	}

	private void setupView() {
		// TODO Auto-generated method stub
		btnRegister=(Button) findViewById(R.id.btn_login_register);
		btnSubmit=(Button) findViewById(R.id.btn_login_submit);
		
		etUsername=(EditText) findViewById(R.id.et_login_username);
		etPassword=(EditText) findViewById(R.id.et_login_password);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.btn_login_submit:
			//接收数据
			String username=etUsername.getText().toString();
			String password=etPassword.getText().toString();
			//检查数据(重点)
			if (username==null || "".equals(username)||" ".equals(username))
			{
				Toast.makeText(this, "用户名不能为空", Toast.LENGTH_LONG).show();
				return ;
			}
			if (password==null || "".equals(password)||" ".equals(password))
			{
				Toast.makeText(this, "密码不能为空", Toast.LENGTH_LONG).show();
				return ;
			}
			//调biz
			LoginBiz loginBiz=new LoginBiz();
			loginBiz.login(username, password);
			break;
		}
	}
class LoginReceiver extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		boolean isSuccess=intent.getBooleanExtra(ApplicationData.KEY_ISSUCCESS, false);
		if (isSuccess)
		{
			//intent
		}else
		{
			Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_LONG).show();
		}
	}
	}
}
