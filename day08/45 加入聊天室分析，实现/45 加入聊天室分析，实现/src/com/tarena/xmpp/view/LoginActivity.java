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
import com.tarena.xmpp.util.ExceptionUtil;
import com.tarena.xmpp.util.NetworkUtil;
import com.tarena.xmpp.util.Tools;

public class LoginActivity extends BaseActivity implements OnClickListener{
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
			NetworkUtil networkUtil=new NetworkUtil();
			networkUtil.checkNetState(this);
			
			setupView();
			addListener();
			
			loginReceiver=new LoginReceiver();
			IntentFilter intentFilter=new IntentFilter(ApplicationData.ACTION_LOGIN);
			registerReceiver(loginReceiver, intentFilter);
			
			//int a=1/0;
			//10
//			long s,e;
//			s=System.currentTimeMillis();
//			for(int i=0;i<10000;i++)
//			{
//				int a=1+2;
//				Log.i("log", a+"");
//			}
//			e=System.currentTimeMillis();
//			Log.i("test 1", (e-s)+"");
//			
//			//2
//			s=System.currentTimeMillis();
//			for(int i=0;i<10000;i++)
//			{
//				int a=1+2;
//				//Log.i("log", a+"");
//			}
//			e=System.currentTimeMillis();
//			Log.i("test 2", (e-s)+"");
		} catch (Exception e) {
			ExceptionUtil.handle(e);
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
		btnRegister.setOnClickListener(this);
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
		case R.id.btn_login_register:
			Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_login_submit:
			doSubmit();
			break;
		}
	}
	private void doSubmit() {
		//接收数据
		String username=etUsername.getText().toString();
		String password=etPassword.getText().toString();
		//检查数据(重点)
		if (Tools.isNull(username))
		{
			//重构 refactor 更好
			//400个Toast.makeText 要改
			Tools.showInfo(this,"用户名不能为空");
//作业
			//				Toast toast=new Toast(this);
//				toast.setView(null);
			return ;
		}
		if (Tools.isNull(username))
		{
			//Toast.makeText(, Toast.LENGTH_LONG).show();
			Tools.showInfo(this, "密码不能为空");
			return ;
		}
		//调biz
		LoginBiz loginBiz=new LoginBiz();
		loginBiz.login(username, password);
	}

class LoginReceiver extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		boolean isSuccess=intent.getBooleanExtra(ApplicationData.KEY_ISSUCCESS, false);
		if (isSuccess)
		{
			Intent intentMain=new Intent(LoginActivity.this,MainActivity.class);
			startActivity(intentMain);
		}else
		{
			Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_LONG).show();
		}
	}
	}
}
