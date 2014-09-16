package com.tarena.xmpp.view;

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
import com.tarena.xmpp.model.AddFriendBiz;
import com.tarena.xmpp.model.ApplicationData;
import com.tarena.xmpp.util.Tools;

public class AddFriendActivity extends BaseActivity {
	Button btnBack, btnSubmit;
	EditText etUsername, etName, etGroups;
	AddFriendReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.add_friend);
			setupView();
			addListener();
			receiver=new AddFriendReceiver();
			IntentFilter intentFiler=new IntentFilter(ApplicationData.ACTION_ADDFRIEND);
			registerReceiver(receiver, intentFiler);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		try {
			unregisterReceiver(receiver);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void addListener() {
		// TODO Auto-generated method stub
btnSubmit.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		try {
			String username=etUsername.getText().toString();
			String name=etName.getText().toString();
			String groups=etGroups.getText().toString();
			//你们完成数据检查
			
			AddFriendBiz addFriendBiz=new AddFriendBiz();
			addFriendBiz.addFriend(username, name, new String[]{groups});
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
});
	}

	private void setupView() {
		// TODO Auto-generated method stub
		btnSubmit = (Button) findViewById(R.id.btn_add_friend_submit);
		etUsername = (EditText) findViewById(R.id.et_add_friend_username);
		etName = (EditText) findViewById(R.id.et_add_friend_name);
		etGroups = (EditText) findViewById(R.id.et_add_friend_group);
	}
class AddFriendReceiver extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		boolean isSuccess=intent.getBooleanExtra(ApplicationData.KEY_ISSUCCESS, false);
		if (isSuccess)
		{
			Tools.showInfo(context, "数据发送成功");
		}else
		{
			Tools.showInfo(context, "数据发送失败");

		}
	}
	}
}
