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
						//花名册，好友名单，好友分类
						Roster roster=ApplicationData.xmppConnection.getRoster();
						//username 表示是用户的名称 a
						//user 表示是用户的全称a@tarena3gxmpp.com
						//name 是呢称
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
