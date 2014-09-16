package com.tarena.xmpp.model;

import java.util.ArrayList;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketInterceptor;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Packet;
import org.xmlpull.v1.XmlPullParserException;

import com.tarena.xmpp.R;
import com.tarena.xmpp.util.LogUtil;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.content.res.XmlResourceParser;
import android.util.Log;

public class ApplicationData extends Application{
	/**
	 * 发布时设置成false;
	 */
	public static final boolean isDebug=true;
	public static final String ACTION_LOGIN = "com.tarena.xmpp.view.LoginActivity.login";
	public static final String ACTION_REGISETER = "com.tarena.xmpp.view.RegisterActivity.Register";
	public static final String KEY_ISSUCCESS = "KEY_ISSUCCESS";
	public static final String ACTION_ADDFRIEND = "com.tarena.xmpp.view.AddFriendActivity.addFriend";
	public static  ApplicationData instance = null;
	//是个socket连接
	public static XMPPConnection xmppConnection;
	ConnectionConfiguration config;
	public static boolean isConnected=false;
	public static boolean isLoginSuccess=false;
	public static String host,serviceName;
	int port;
	
	public ArrayList<Activity> list=new ArrayList();
	public void exit()
	{
		try {
			for(Activity activity:list)
			{
				activity.finish();		
				LogUtil.i("exit", "finish:"+activity.toString());

			}
			//把应用的信息全部清空，下次再启动程序会执行Application.onCreate()
			System.exit(0);
			xmppConnection.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	public void onCreate() {
		super.onCreate();
		LogUtil.i("exit", "applicationData onCreate()");

		instance=this;
		getConfig();
		//连上openfire 
		//服务器信息
		 config=new 
				ConnectionConfiguration(host, port, serviceName);
		xmppConnection=new XMPPConnection(config);
		//让框架中的接口指向实现类
		AllInterceptor allInterceptor=new AllInterceptor();
		xmppConnection.addPacketInterceptor(allInterceptor, null);
		
		AllListener allListener=new AllListener();
		xmppConnection.addPacketListener(allListener, null);
		new Thread(){
			public void run() {try {
				Log.i("login", "建立连接 threadid="+Thread.currentThread().getId());

				long startTime,endTime;
				startTime=System.currentTimeMillis();
				//联openfire
				xmppConnection.connect();
				endTime=System.currentTimeMillis();
				Log.i("login", "建立连接所用时间"+(endTime-startTime));

				isConnected=xmppConnection.isConnected();
				Log.i("login", "isConnected="+isConnected);
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}}	;
		}.start();;
		
	}
	class AllInterceptor implements PacketInterceptor
	{

		@Override
		public void interceptPacket(Packet packet) {
			// TODO Auto-generated method stub
			//packet 是asmack发给openfire的数据，还有openfire发给的asmackr
			LogUtil.i("All", "发出去"+packet.toXML());
			
		}
		
	}
	class AllListener implements PacketListener
	{

		@Override
		public void processPacket(Packet packet) {
			// TODO Auto-generated method stub
			LogUtil.i("All", "服务器返回"+packet.toXML());

		}
		
	}
	
	private void getConfig() {
		// TODO Auto-generated method stub
		
		try {
			XmlResourceParser parser=this.getResources().getXml(R.xml.config);
			int eventType=parser.getEventType();
			while(eventType!=XmlResourceParser.END_DOCUMENT)
			{
				if (eventType==XmlResourceParser.START_TAG)
				{
					String tagName=parser.getName();
					if ("openfireIp".equals(tagName))
					{
						host=parser.nextText().trim();
					}
					if ("port".equals(tagName))
					{
						port=Integer.parseInt(parser.nextText().trim());
					}
					if ("serviceName".equals(tagName))
					{
						serviceName=parser.nextText().trim();
					}
				}
				eventType=parser.next();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
