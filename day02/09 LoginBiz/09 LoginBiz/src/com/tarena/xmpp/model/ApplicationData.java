package com.tarena.xmpp.model;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.xmlpull.v1.XmlPullParserException;

import com.tarena.xmpp.R;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.XmlResourceParser;
import android.util.Log;

public class ApplicationData extends Application{
	public static  ApplicationData instance = null;
	//是个socket连接
	public static XMPPConnection xmppConnection;
	ConnectionConfiguration config;
	public static boolean isConnected=false;
	public static boolean isLoginSuccess=false;
	String host,serviceName;
	int port;
	@Override
	public void onCreate() {
		super.onCreate();
		instance=this;
		getConfig();
		//连上openfire 
		//服务器信息
		 config=new 
				ConnectionConfiguration(host, port, serviceName);
		xmppConnection=new XMPPConnection(config);
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
