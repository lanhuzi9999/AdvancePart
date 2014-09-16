package com.tarena.xmpp.model;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.jivesoftware.smack.packet.Message;

public class PrivateChatEntity {
	//string �Ǻ��ѵ�ȫ��
	public static ConcurrentHashMap<String, Vector<Message>> map=new ConcurrentHashMap();
	public static void addMessage(final String friendUser, Message msg) {
		Vector<Message> vector=
				map.get(friendUser);
		if (vector==null)
		{
			vector=new Vector<Message>();
			map.put(friendUser, vector);
		}
		vector.add(msg);
	}
}
