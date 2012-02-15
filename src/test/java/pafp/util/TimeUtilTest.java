package pafp.util;

import org.junit.Test;

import junit.framework.Assert;

public class TimeUtilTest {

	@Test
	public void test() throws Exception {
		Assert.assertEquals(3600, TimeUtil.toSeconds("1:00:00"));
		Assert.assertEquals(3600, TimeUtil.toSeconds("01:00:00"));
		Assert.assertEquals(60, TimeUtil.toSeconds("0:01:00"));
		Assert.assertEquals(1, TimeUtil.toSeconds("0:00:01"));
		Assert.assertEquals(3661, TimeUtil.toSeconds("1:01:01"));
	}
	
}
