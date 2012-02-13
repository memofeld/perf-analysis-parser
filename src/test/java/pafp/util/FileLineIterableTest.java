package pafp.util;

import org.junit.Test;

public class FileLineIterableTest {

	@Test
	public void test() throws Exception {
		for (String s : new FileLineIterable("data1/perf-20120210-0804 Donald Duck.txt")) {
			System.out.println(s);
		}
	}

}
