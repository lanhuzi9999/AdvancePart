package com.tarena.xmpp.view;

import java.util.Vector;

import org.jivesoftware.smack.packet.Message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tarena.xmpp.R;
import com.tarena.xmpp.model.ApplicationData;
import com.tarena.xmpp.model.PrivateChatBiz;
import com.tarena.xmpp.model.PrivateChatEntity;
import com.tarena.xmpp.util.ExceptionUtil;
import com.tarena.xmpp.util.LogUtil;
import com.tarena.xmpp.util.Tools;

import android.content.IntentFilter;

public class PrivateChatActivity extends BaseActivity{
	Button btnSend;
	TextView tvFriendName;
	EditText etbody;
	String friendUser;
	ShowMessageReceiver receiver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try {
			friendUser=getIntent().getStringExtra("friendUser");
			setContentView(R.layout.private_chat);
			setupView();
			addListener();
			setData();
			receiver=new ShowMessageReceiver();
			IntentFilter intentFilter=new IntentFilter(ApplicationData.ACTION_SHOW_PRIVATE_MESSAGE);
			registerReceiver(receiver, intentFilter);
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handle(e);
		}
	}
@Override
protected void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
	unregisterReceiver(receiver);
}
	private void setData() {
		try {
			// TODO Auto-generated method stub
			String friendUsername=friendUser.substring(0,friendUser.indexOf("@"));
			tvFriendName.setText(friendUsername);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addListener() {
		try {
			// TODO Auto-generated method stub
			btnSend.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					try {
						String body=etbody.getText().toString();
						if (!Tools.isNull(body))
						{
							PrivateChatBiz privateChatBiz=new PrivateChatBiz();
							privateChatBiz.sendMessage(friendUser, body);
							etbody.getText().clear();
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setupView() {
		// TODO Auto-generated method stub
		try {
			btnSend=(Button) findViewById(R.id.btn_private_chat_send);
			tvFriendName=(TextView) findViewById(R.id.tv_private_chat_friendName);
			etbody=(EditText) findViewById(R.id.et_private_chat_body);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
class ShowMessageReceiver extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		try {
			Vector<Message> vector=PrivateChatEntity.map.get(friendUser);
			if (vector!=null)
			{
				for(int i=0;i<vector.size();i++)
				{
					Message msg=vector.get(i);
					String body=msg.getBody();
					LogUtil.i("ShowMessageReceiver", body);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	}
}
