package com.tarena.xmpp.view;

import org.jivesoftware.smack.packet.Message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tarena.xmpp.R;
import com.tarena.xmpp.model.ApplicationData;
import com.tarena.xmpp.model.GroupChatBiz;
import com.tarena.xmpp.model.GroupChatEntity;
import com.tarena.xmpp.util.Tools;
import com.tarena.xmpp.view.PrivateChatActivity.ShowMessageReceiver;

import android.content.IntentFilter;;

public class GroupChatActivity extends BaseActivity{
	EditText etBody;
	Button btnSend;
	TextView tvRoom;
	LinearLayout linearLayout;
	showGroupMessageReceiver receiver;
	ScrollView scrollView;
	Handler handler=new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.group_chat);
			setupView();
			addListener();
			setData();
			receiver=new showGroupMessageReceiver();
			IntentFilter  filter=new IntentFilter(ApplicationData.ACTION_SHOW_GROUP_CHAT_MESSAGE);
			registerReceiver(receiver, filter);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
@Override
protected void onDestroy() {
	// TODO Auto-generated method stub
	unregisterReceiver(receiver);
	super.onDestroy();
}
	private void setData() {
		// TODO Auto-generated method stub
		String room=ApplicationData.groupChat.getRoom();
		room=room.substring(0, room.indexOf("@"));
		tvRoom.setText(room);
		
		
	}

	private void addListener() {
		// TODO Auto-generated method stub
		btnSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					String body=etBody.getText().toString();
					//数据检查
					if (!Tools.isNull(body)){
						GroupChatBiz groupChatBiz=new GroupChatBiz();
						groupChatBiz.sendMessage(body);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
		
	}

	private void setupView() {
		// TODO Auto-generated method stub
		etBody=(EditText) findViewById(R.id.et_group_chat_body);
		btnSend=(Button) findViewById(R.id.btn_group_chat_send);
		tvRoom=(TextView) findViewById(R.id.tv_group_chat_roomName);
		linearLayout=(LinearLayout) findViewById(R.id.linearLayout_group_chat);
		scrollView=(ScrollView) findViewById(R.id.scrollView_group_chat);
	}
class showGroupMessageReceiver extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		try {
			String room=ApplicationData.groupChat.getRoom();
			Message msg=GroupChatEntity.map.get(room);
			if (msg!=null)
			{
				String from=msg.getFrom();
				String body=msg.getBody();
				if (ApplicationData.currentUser.equals(from))
				{
					//我说的话，显示在右边
					//把right.xml变成viewRight
					View viewRight=View.inflate(context, R.layout.right, null);
					//找到TextView,设值
					TextView tvBody=(TextView) viewRight.findViewById(R.id.tv_right_body);
					tvBody.setText(body);
					//把viewRight放到linearLayout
					linearLayout.addView(viewRight);
				}else
				{
					View viewLeft=View.inflate(context, R.layout.left, null);
					TextView tvBody=(TextView) viewLeft.findViewById(R.id.tv_left_body);
					String username="系统";
					//得username
					if (from.contains("/"))
					{
						username=from.substring(from.indexOf("/")+1);
					}
					tvBody.setText(username+":"+body);
					linearLayout.addView(viewLeft);
				}
			
				//往上移
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							int scrollViewHeight=scrollView.getHeight();
							int linearLayoutHeight=linearLayout.getHeight();
							scrollView.scrollTo(0, (linearLayoutHeight-scrollViewHeight));
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				});
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
}
