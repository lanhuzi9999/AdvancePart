package com.tarena.xmpp.util;

import android.content.Context;
import android.widget.Toast;

public class Tools {
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str)
	{
		if (null==str||"".equals(str)||" ".equals(str)||"null".equals(str))
		{
			return true;
		}
		return false;
	}
	public static void showInfo(Context context,String info) {
		Toast.makeText(context, info, Toast.LENGTH_LONG).show();
	}
}
