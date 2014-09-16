package com.tarena.xmpp.model;

import java.util.Vector;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Type;

import android.content.Intent;

public class PrivateChatBiz {
	/**
	 * 
	 * @param friendUser ȫ��
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
						//Type.chat˽��
						msg.setType(Type.chat);
						ApplicationData.xmppConnection.sendPacket(msg);
						
						//���浽ʵ����
						PrivateChatEntity.addMessage(friendUser, msg);
						//���㲥
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
