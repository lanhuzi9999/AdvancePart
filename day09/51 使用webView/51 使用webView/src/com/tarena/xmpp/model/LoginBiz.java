package com.tarena.xmpp.model;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * ��¼
 * 
 * @author pjy
 * 
 */
public class LoginBiz {

	public void login(final String username, final String password) {

		new Thread() {
			public void run() {
				try {
					
					Log.i("login", "login threadid="
							+ Thread.currentThread().getId());

					// 1��һ������
					// socket.getOutputStream.write
					// socket.getInputStream.read
					// 2����asmack
					// �ж��Ƿ�����
					int count = 0;
					while (count < 20
							& ApplicationData.xmppConnection.isConnected() == false) {
						count++;
						sleep(1000);
					}
					if (ApplicationData.xmppConnection.isConnected()) {
						ApplicationData.xmppConnection
								.login(username, password);
						ApplicationData.isLoginSuccess = ApplicationData.xmppConnection
								.isAuthenticated();
						Log.i("login", "��¼���" + ApplicationData.isLoginSuccess);

						if (ApplicationData.isLoginSuccess) {
							ApplicationData.currentUser=username+"@"+ApplicationData.serviceName;
							Intent intent = new Intent(
									ApplicationData.ACTION_LOGIN);
							intent.putExtra(ApplicationData.KEY_ISSUCCESS, true);
							// ���㲥��context������
							ApplicationData.instance.sendBroadcast(intent);
						}else
						{
						}
						
					} else {

						// ���㲥
						Toast.makeText(ApplicationData.instance, "û����",
								Toast.LENGTH_LONG).show();
						;
						Intent intent = new Intent(ApplicationData.ACTION_LOGIN);
						intent.putExtra(ApplicationData.KEY_ISSUCCESS, false);
						// ���㲥��context������
						ApplicationData.instance.sendBroadcast(intent);

					}
				} catch (Exception e) {

					Intent intent = new Intent(ApplicationData.ACTION_LOGIN);
					intent.putExtra(ApplicationData.KEY_ISSUCCESS, false);
					// ���㲥��context������
					ApplicationData.instance.sendBroadcast(intent);
					e.printStackTrace();
					// TODO: handle exception
				}
			};
		}.start();

	}

}
