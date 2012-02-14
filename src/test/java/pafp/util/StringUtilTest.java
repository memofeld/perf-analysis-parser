package pafp.util;

import junit.framework.Assert;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void test() throws Exception {
		Assert.assertEquals("1 1", StringUtil.normalize("1  1"));
		Assert.assertEquals("1 1 4", StringUtil.normalize("1  1         4"));
		Assert.assertEquals(null, StringUtil.normalize(null));
		Assert.assertEquals("Hallo", StringUtil.normalize("Hallo"));
	}

}
