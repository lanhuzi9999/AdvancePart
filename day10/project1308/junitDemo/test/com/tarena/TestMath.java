package com.tarena;

import org.junit.Assert;
import org.junit.Test;

/**
 * 用来测试Math类，每个类都有一个测试类
 * @author pjy
 *
 */
public class TestMath {
	//为每个方法再写一个测试方法
	//Text对应一个java文件
	@Test
	public void testAdd()
	{
		Math math=new Math();
		int result=math.add(1, 1);
		//以前，测试结果在console输出
		//assert断言
		Assert.assertEquals(2, result);
	}

}
