package com.tarena.xmpp.model;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.xmlpull.v1.XmlPullParserException;

import com.tarena.xmpp.R;

import android.app.Application;
import android.content.res.Resources.NotFoundException;
import android.content.res.XmlResourceParser;
import android.util.Log;

public class ApplicationData extends Application{
	//�Ǹ�socket����
	XMPPConnection xmppConnection;
	ConnectionConfiguration config;
	public static boolean isConnected=false;
	String host,serviceName;
	int port;
	@Override
	public void onCreate() {
		super.onCreate();
		getConfig();
		//����openfire 
		//��������Ϣ
		 config=new 
				ConnectionConfiguration(host, port, serviceName);
		xmppConnection=new XMPPConnection(config);
		 new Thread(){
			public void run() {try {
				//��openfire
				xmppConnection.connect();
				isConnected=xmppConnection.isConnected();
				Log.i("����", "isConnected="+isConnected);
				
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
