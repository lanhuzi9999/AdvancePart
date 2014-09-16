package com.tarena.xmpp.model;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Type;
import org.jivesoftware.smackx.muc.MultiUserChat;

import com.tarena.xmpp.util.ExceptionUtil;
import com.tarena.xmpp.util.LogUtil;

import android.content.Intent;

public class GroupChatBiz {

	public void join(final String room, final String name) {
		new Thread() {
			public void run() {
				try {
					String roomName = room + "@conference."
							+ ApplicationData.serviceName;
					MultiUserChat groupChat = new MultiUserChat(
							ApplicationData.xmppConnection, roomName);
					groupChat.join(name);
					ApplicationData.groupChat = groupChat;
					Intent intent = new Intent(ApplicationData.ACTION_JOIN);
					intent.putExtra(ApplicationData.KEY_ISSUCCESS, true);
					ApplicationData.instance.sendBroadcast(intent);

				} catch (Exception e) {
					// TODO: handle exception
					Intent intent = new Intent(ApplicationData.ACTION_JOIN);
					intent.putExtra(ApplicationData.KEY_ISSUCCESS, false);
					ApplicationData.instance.sendBroadcast(intent);
				}
			};
		}.start();
	}

	public void sendMessage(final String body) {
		new Thread() {
			public void run() {
				try {
					int count = 0;
					while (count < 100 && ApplicationData.groupChat == null) {
						sleep(100);
						count++;
					}
					if (ApplicationData.groupChat != null) {
						// message
						Message msg = new Message();
						msg.setFrom(ApplicationData.currentUser);
						msg.setBody(body);
						msg.setType(Type.groupchat);
						// 13082@conference.tarena3gxmpp.com
						String room=ApplicationData.groupChat.getRoom();
						msg.setTo(room);
						ApplicationData.groupChat.sendMessage(msg);
						GroupChatEntity.map.put(room,msg);
						Intent intent=new Intent(ApplicationData.ACTION_SHOW_GROUP_CHAT_MESSAGE);
						ApplicationData.instance.sendBroadcast(intent);
					} else {
						// ¼ÓÈëÊ§°Ü
					}

				} catch (Exception e) {
					// TODO: handle exception
					LogUtil.i("groupChat", "sendMessageÊ§°ÜÁË");
					ExceptionUtil.handle(e);
				}
			};
		}.start();

	}

}
