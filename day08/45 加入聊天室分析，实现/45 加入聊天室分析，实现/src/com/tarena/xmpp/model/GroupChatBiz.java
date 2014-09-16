package com.tarena.xmpp.model;

import org.jivesoftware.smackx.muc.MultiUserChat;

import android.content.Intent;

public class GroupChatBiz {
	
	public void join(final String room,final String name)
	{
		new Thread(){public void run() {
			try {
				String roomName=room+"@conference."+ApplicationData.serviceName;
				MultiUserChat groupChat=
						new MultiUserChat
						(ApplicationData.xmppConnection, roomName);
				groupChat.join(name);
				Intent intent=new Intent(ApplicationData.ACTION_JOIN);
				intent.putExtra(ApplicationData.KEY_ISSUCCESS, true);
				ApplicationData.instance.sendBroadcast(intent);
				
			} catch (Exception e) {
				// TODO: handle exception
				Intent intent=new Intent(ApplicationData.ACTION_JOIN);
				intent.putExtra(ApplicationData.KEY_ISSUCCESS, false);
				ApplicationData.instance.sendBroadcast(intent);
			}
		};}.start();
	}

}
