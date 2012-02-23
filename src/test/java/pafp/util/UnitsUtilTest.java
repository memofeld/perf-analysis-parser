package pafp.util;

import junit.framework.Assert;

import org.junit.Test;

public class UnitsUtilTest {

	@Test
	public void test1() throws Exception {
		Assert.assertEquals(3600, UnitsUtil.toSeconds("1:00:00"));
		Assert.assertEquals(3600, UnitsUtil.toSeconds("01:00:00"));
		Assert.assertEquals(60, UnitsUtil.toSeconds("0:01:00"));
		Assert.assertEquals(1, UnitsUtil.toSeconds("0:00:01"));
		Assert.assertEquals(3661, UnitsUtil.toSeconds("1:01:01"));
	}

	@Test
	public void test2() throws Exception {
		Assert.assertEquals(1024, UnitsUtil.convert("1k"));
		Assert.assertEquals(2048, UnitsUtil.convert("2k"));
		Assert.assertEquals(4096, UnitsUtil.convert("4k"));
	}

}
