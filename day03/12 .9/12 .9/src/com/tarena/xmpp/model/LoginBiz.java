package com.tarena.xmpp.model;

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
					} else {
						// ���㲥
						Toast.makeText(ApplicationData.instance, "û����", Toast.LENGTH_LONG).show();
						;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			};
		}.start();

	}

}
