package com.tarena.xmpp.model;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Type;

public class PrivateChatBiz {
	/**
	 * 
	 * @param friendUser È«³Æ
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
						//Type.chatË½ÁÄ
						msg.setType(Type.chat);
						ApplicationData.xmppConnection.sendPacket(msg);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}.start();
	}

}
