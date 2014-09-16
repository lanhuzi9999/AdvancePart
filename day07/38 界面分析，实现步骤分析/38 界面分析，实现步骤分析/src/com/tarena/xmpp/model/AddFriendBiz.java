package com.tarena.xmpp.model;

import org.jivesoftware.smack.Roster;

import android.content.Intent;

public class AddFriendBiz {
	
	public void addFriend(final String username,final String name,final String[] groups)
	{
		new Thread(){
			public void run() {
				try {
					if (ApplicationData.isLoginSuccess)
					{
						//�����ᣬ�������������ѷ���
						Roster roster=ApplicationData.xmppConnection.getRoster();
						//username ��ʾ���û������� a
						//user ��ʾ���û���ȫ��a@tarena3gxmpp.com
						//name ���س�
						String user=username+"@"+ApplicationData.serviceName;
						roster.createEntry(user, name, groups);
						
						Intent intent=new Intent(ApplicationData.ACTION_ADDFRIEND);
						intent.putExtra(ApplicationData.KEY_ISSUCCESS, true);
						ApplicationData.instance.sendBroadcast(intent);
						
					}
				} catch (Exception e) {
					// TODO: handle exception
					Intent intent=new Intent(ApplicationData.ACTION_ADDFRIEND);
					intent.putExtra(ApplicationData.KEY_ISSUCCESS, false);
					ApplicationData.instance.sendBroadcast(intent);
				}
			};
		}.start();
	}

}
