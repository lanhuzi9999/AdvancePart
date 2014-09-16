package com.tarena.xmpp.model;

import java.util.Vector;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Type;

import android.content.Intent;

public class PrivateChatBiz {
	/**
	 * 
	 * @param friendUser 全称
	 * @param body
	 */
	public void sendMessage(final String friendUser,final String body)
	{
		new Thread(){
			@Override
			public void run() {
				try {
					if (ApplicationData.isLoginSuccess)
					{
						Message msg=new Message();
						msg.setTo(friendUser);
						
						msg.setFrom(ApplicationData.currentUser);
						msg.setBody(body);
						//Type.chat私聊
						msg.setType(Type.chat);
						ApplicationData.xmppConnection.sendPacket(msg);
						
						//保存到实体类
						Vector<Message> vector=
								PrivateChatEntity.map.get(friendUser);
						if (vector==null)
						{
							vector=new Vector<Message>();
							PrivateChatEntity.map.put(friendUser, vector);
						}
						vector.add(msg);
						//发广播
						Intent intent=new Intent(ApplicationData.ACTION_SHOW_PRIVATE_MESSAGE);
						ApplicationData.instance.sendBroadcast(intent);
						
						
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
	}

}
