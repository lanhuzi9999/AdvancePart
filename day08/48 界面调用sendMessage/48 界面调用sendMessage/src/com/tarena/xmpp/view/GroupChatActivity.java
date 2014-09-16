package com.tarena.xmpp.view;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tarena.xmpp.R;
import com.tarena.xmpp.model.ApplicationData;
import com.tarena.xmpp.model.GroupChatBiz;
import com.tarena.xmpp.util.Tools;

public class GroupChatActivity extends BaseActivity{
	EditText etBody;
	Button btnSend;
	TextView tvRoom;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try {
			setContentView(R.layout.group_chat);
			setupView();
			addListener();
			setData();
		} catch (Exception e) {
			// TODO: handle exception
		}
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
					//Êý¾Ý¼ì²é
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
	}

}
