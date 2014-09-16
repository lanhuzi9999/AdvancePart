package com.tarena.xmpp.view;

import java.util.Vector;

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
	LinearLayout linearLayout;
	ScrollView scrollView;
	
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
			linearLayout=(LinearLayout) findViewById(R.id.linearLayout_private_chat);
			scrollView=(ScrollView) findViewById(R.id.scrollView_private_chat);
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
				linearLayout.removeAllViews();
				for(int i=0;i<vector.size();i++)
				{
					Message msg=vector.get(i);
					//msg分两种
					//1,我说的话， from是currentUser 显示在右边
					//2,好友发过来，from是frienduser 显示在左边
					String from=msg.getFrom();
					String body=msg.getBody();

					if (from.equals(ApplicationData.currentUser))
					{
						//我说的
					LogUtil.i("ShowMessageReceiver", body);
					//显示在屏幕上
					//把xml变成view
					View viewRight=View.inflate(context, R.layout.right, null);
					//找到了TextView
					TextView tvBody=(TextView) viewRight.findViewById(R.id.tv_right_body);
					//给TextView设值显示的内容
					tvBody.setText(body);
					//把view放进linearlayout
					linearLayout.addView(viewRight);
					}else
					{
						//好友说的
						//把left.xml变成viewLeft
						View viewLeft=View.inflate(context, R.layout.left, null);
						
						//找到textView
						TextView tvBody=(TextView) viewLeft.findViewById(R.id.tv_left_body);
						//给textView设值
						tvBody.setText(body);
						//把viewLeft放进LinearLayout
						linearLayout.addView(viewLeft);
						
					}
					
				
					
				}
				//问题，在主线程中onCreate/setupVewi,控件的高度有错误。
				//向上移动scrollView中的内容LinearLayout
				new Handler().post(new Runnable() {
					//主线程把界面绘制完了，再执行run
					@Override
					public void run() {
						LogUtil.i("height","3");
						// TODO Auto-generated method stub
						int scrollViewHeight=scrollView.getHeight();
						int linearLayoutHeight=linearLayout.getHeight();
						scrollView.scrollTo(0, (linearLayoutHeight-scrollViewHeight));
						LogUtil.i("height", "scrollViewHeight="+scrollViewHeight+" linearLayoutHeight="+linearLayoutHeight);
					}
				});
				LogUtil.i("height","1");
				Thread.currentThread().sleep(4000);
				LogUtil.i("height","2");

				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	}
}
