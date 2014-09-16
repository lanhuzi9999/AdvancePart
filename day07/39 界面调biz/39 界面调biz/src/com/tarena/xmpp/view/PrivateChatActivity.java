package com.tarena.xmpp.view;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tarena.xmpp.R;
import com.tarena.xmpp.model.PrivateChatBiz;
import com.tarena.xmpp.util.ExceptionUtil;
import com.tarena.xmpp.util.Tools;

public class PrivateChatActivity extends BaseActivity{
	Button btnSend;
	TextView tvFriendName;
	EditText etbody;
	String friendUser;
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
		} catch (Exception e) {
			// TODO: handle exception
			ExceptionUtil.handle(e);
		}
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

}
