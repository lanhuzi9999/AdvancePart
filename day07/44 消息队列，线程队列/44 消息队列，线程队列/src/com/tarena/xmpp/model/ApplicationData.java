package com.tarena.xmpp.model;

import java.util.ArrayList;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketInterceptor;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.RosterGroup;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Type;
import org.jivesoftware.smack.packet.RosterPacket;
import org.xmlpull.v1.XmlPullParserException;

import com.tarena.xmpp.R;
import com.tarena.xmpp.util.ExceptionUtil;
import com.tarena.xmpp.util.LogUtil;
import com.tarena.xmpp.util.Tools;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.content.res.XmlResourceParser;
import android.util.Log;

public class ApplicationData extends Application{
	/**
	 * ����ʱ���ó�false;
	 */
	public static final boolean isDebug=true;
	public static final String ACTION_LOGIN = "com.tarena.xmpp.view.LoginActivity.login";
	public static final String ACTION_REGISETER = "com.tarena.xmpp.view.RegisterActivity.Register";
	public static final String KEY_ISSUCCESS = "KEY_ISSUCCESS";
	public static final String ACTION_ADDFRIEND = "com.tarena.xmpp.view.AddFriendActivity.addFriend";
	public static final String ACTION_GROUP_lIST_CHANGED = "com.tarena.xmpp.view.GrougListFragment";
	public static final String ACTION_SHOW_PRIVATE_MESSAGE = "com.tarena.xmpp.view.PrivateChatActivity";
	public static  ApplicationData instance = null;
	//�Ǹ�socket����
	public static XMPPConnection xmppConnection;
	ConnectionConfiguration config;
	public static boolean isConnected=false;
	public static boolean isLoginSuccess=false;
	public static String host,serviceName;
	public static String currentUser;
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
			//��Ӧ�õ���Ϣȫ����գ��´������������ִ��Application.onCreate()
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
		//����openfire 
		//��������Ϣ
		 config=new 
				ConnectionConfiguration(host, port, serviceName);
		xmppConnection=new XMPPConnection(config);
		//�ÿ���еĽӿ�ָ��ʵ����
		AllInterceptor allInterceptor=new AllInterceptor();
		xmppConnection.addPacketInterceptor(allInterceptor, null);
		
		AllListener allListener=new AllListener();
		xmppConnection.addPacketListener(allListener, null);
		
		//GroupListListener ע�ᵽasmack
		//<iq id="A4N5E-2" to="an7@tarena3gxmpp.com/Smack" 
		//type="result">
		//<query xmlns="jabber:iq:roster" >
		//<item jid="pc6@tarena3gxmpp.com" name="pc6" subscription="both">
		//<group>1308</group></item></query></iq>

		GroupListListener groupListListener=new GroupListListener();
		PacketTypeFilter packetTypeFilter=new PacketTypeFilter(RosterPacket.class);

xmppConnection.addPacketListener(groupListListener, packetTypeFilter);

//˽��
PrivateChatFilter privateChatFilter=new PrivateChatFilter();
PrivateChatListener privateChatListener=new PrivateChatListener();
xmppConnection.addPacketListener(privateChatListener, privateChatFilter);
		new Thread(){
			public void run() {try {
				Log.i("login", "�������� threadid="+Thread.currentThread().getId());

				long startTime,endTime;
				startTime=System.currentTimeMillis();
				//��openfire
				xmppConnection.connect();
				endTime=System.currentTimeMillis();
				Log.i("login", "������������ʱ��"+(endTime-startTime));

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
			//packet ��asmack����openfire�����ݣ�����openfire������asmackr
			LogUtil.i("All", "����ȥ"+packet.toXML());
			
		}
		
	}
	class AllListener implements PacketListener
	{

		@Override
		public void processPacket(Packet packet) {
			
//packet.toXml <presence><iq><messsage>,ÿ����ǩ��Ӧһ����
			//<presence> ��Ӧ����Presence��
			//Packet�Ǹ������� packetһ������������Presence
			if (packet instanceof Presence)
			{
				Presence presence=(Presence) packet;
				Type type=presence.getType();
				String friendName=presence.getFrom();
				if (type==Type.unsubscribe)
				{
					Tools.showInfo(getApplicationContext(), friendName+"��ͬ��");
				}else if(type==Type.subscribed)
				{
					Tools.showInfo(getApplicationContext(), friendName+"ͬ����");

				}
				
			}
			LogUtil.i("All", "��������������packe"+packet.toXML());

		}
		
	}
	/**
	 * ֻ�������ѷ���
	 * @author pjy
	 *
	 */
	class GroupListListener implements PacketListener
	{
//˵���к��ѷ������
		@Override
		public void processPacket(Packet packet) {
			// TODO Auto-generated method stub
			//RosterPacket rosterPacket=(RosterPacket) packet;
			try {
				LogUtil.i("GroupListListener", "run");

//				ArrayList<RosterGroup> list=new ArrayList
//						(xmppConnection.getRoster().getGroups());
//				for(RosterGroup rosterGroup:list)
//				{
//					LogUtil.i("GroupListListener", "��������"+rosterGroup.getName());
//				}
				//���㲥
				
				Intent intent=new Intent(ACTION_GROUP_lIST_CHANGED);
				sendBroadcast(intent);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	class PrivateChatFilter implements PacketFilter
	{

		//xml <message> ��Ӧ����Message
		@Override
		public boolean accept(Packet packet) {
			// TODO Auto-generated method stub
			if (packet instanceof Message)
			{
				Message message=(Message) packet;
				org.jivesoftware.smack.packet.Message.Type type=message.getType();
				if (type==org.jivesoftware.smack.packet.Message.Type.chat)
				{
					return true;
				}
			}
			return false;
		}
		
	}
	
	class PrivateChatListener implements PacketListener
	{

		@Override
		public void processPacket(Packet packet) {
			// TODO Auto-generated method stub
			try {
				Message message=(Message) packet;
				//from�ĸ�ʽusername@servicename/serviceName
				String from=message.getFrom();
				String body=message.getBody();
				LogUtil.i("PrivateChatListener", from+":"+body);
				if (from.contains("/"))
				{
					String  friendUser=from.substring(0, from.indexOf("/"));
				//���浽ʵ����
				PrivateChatEntity.addMessage(friendUser, message);
				
				Intent intent=new Intent(ApplicationData.ACTION_SHOW_PRIVATE_MESSAGE);
				sendBroadcast(intent);
				}
			} catch (Exception e) {
				// TODO: handle exception
				ExceptionUtil.handle(e);
			}
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
