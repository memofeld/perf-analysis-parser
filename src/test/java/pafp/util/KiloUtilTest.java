package pafp.util;

import junit.framework.Assert;

import org.junit.Test;

public class KiloUtilTest {

	@Test
	public void test() throws Exception {
		Assert.assertEquals(1024, KiloUtil.convert("1k"));
		Assert.assertEquals(2048, KiloUtil.convert("2k"));
		Assert.assertEquals(4096, KiloUtil.convert("4k"));
	}
	
}
