package com.tarena.xmpp.model;

import android.util.Log;
import android.widget.Toast;

/**
 * 登录
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

					// 1，一般做法
					// socket.getOutputStream.write
					// socket.getInputStream.read
					// 2，用asmack
					// 判断是否连上
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
						Log.i("login", "登录结果" + ApplicationData.isLoginSuccess);
					} else {
						// 发广播
						Toast.makeText(ApplicationData.instance, "没连上", Toast.LENGTH_LONG).show();
						;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			};
		}.start();

	}

}
