package com.tarena;

import org.junit.Assert;
import org.junit.Test;

public class TestHttpConnect {
	@Test
	public void testOpen() {
		HttpConnect httpConnect = new HttpConnect();
		byte[] array = httpConnect.open("http://www.tarena.com.cn");
		boolean flag = false;
		if (array == null) {
			flag = false;
			Assert.assertEquals(true, flag);
		} else {
			flag = array.length >= 4 ? true : false;
			Assert.assertEquals(true, flag);
		}
	}

}
