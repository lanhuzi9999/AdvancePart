package com.tarena;

import org.junit.Assert;
import org.junit.Test;

/**
 * ��������Math�࣬ÿ���඼��һ��������
 * @author pjy
 *
 */
public class TestMath {
	//Ϊÿ��������дһ�����Է���
	//Text��Ӧһ��java�ļ�
	@Test
	public void testAdd()
	{
		Math math=new Math();
		int result=math.add(1, 1);
		//��ǰ�����Խ����console���
		//assert����
		Assert.assertEquals(2, result);
	}

}
