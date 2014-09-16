package com.tarena.xmpp.model;

import java.util.HashMap;




import org.jivesoftware.smack.AccountManager;

import android.content.Intent;

import com.tarena.xmpp.util.ExceptionUtil;
import com.tarena.xmpp.util.Tools;

public class RegisterBiz {
	
	public void register(final String username,final String password,final HashMap map)
	{
		new Thread(){
			public void run() {
				try {
					int count=0;
					while(count<1000&&ApplicationData.xmppConnection.isConnected()==false)
					{
						sleep(100);
						count++;
					}
					if (ApplicationData.xmppConnection.isConnected())
					{
						//×¢²á
						AccountManager accountManager=ApplicationData.xmppConnection.getAccountManager();
						accountManager.createAccount(username, password, map);
						
						Intent intent=new Intent(ApplicationData.ACTION_REGISETER);
						intent.putExtra(ApplicationData.KEY_ISSUCCESS, true);
						ApplicationData.instance.sendBroadcast(intent);
					}else
					{
						Tools.showInfo(ApplicationData.instance, "Á¬½ÓÊ§°Ü");
					}
				} catch (Exception e) {
					// TODO: handle exception
					ExceptionUtil.handle(e);
					Intent intent=new Intent(ApplicationData.ACTION_REGISETER);
					intent.putExtra(ApplicationData.KEY_ISSUCCESS, false);
					ApplicationData.instance.sendBroadcast(intent);
				}
			};
		}.start();
	}

}
