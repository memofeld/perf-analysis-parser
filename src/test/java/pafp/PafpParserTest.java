package pafp;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PafpParserTest {

	@Test
	public void testMachine() throws Exception {
		PafpParser pafpParser = new PafpParser("data1/perf-20120210-0804 Donald Duck.txt");
		pafpParser.parse();
		
		String machine = pafpParser.getMachine();
		
		assertTrue(machine.equals("Entenhausen01"));
	}

	@Test
	public void testIteration() throws Exception {
		PafpParser pafpParser = new PafpParser("data1/perf-20120210-0804 Donald Duck.txt");
		pafpParser.parse();
		
		String machine = pafpParser.getIteration();
		
		assertTrue(machine.equals("23"));
	}

	@Test
	public void testUser() throws Exception {
		PafpParser pafpParser = new PafpParser("data1/perf-20120210-0804 Donald Duck.txt");
		pafpParser.parse();
		
		String machine = pafpParser.getUser();
		
		assertTrue(machine.equals("Donald Duck"));
	}

	@Test
	public void testAvgPing() throws Exception {
		PafpParser pafpParser = new PafpParser("data1/perf-20120210-0804 Donald Duck.txt");
		pafpParser.parse();
		
		String machine = pafpParser.getAvgPing();
		
		assertTrue(machine.equals("143ms"));
	}

	@Test
	public void testAvgDownload() throws Exception {
		PafpParser pafpParser = new PafpParser("data1/perf-20120210-0804 Donald Duck.txt");
		pafpParser.parse();
		
		String machine = pafpParser.getAvgDownload();
		
		assertTrue(machine.equals("149k"));
	}

	@Test
	public void testTimeSpent() throws Exception {
		PafpParser pafpParser = new PafpParser("data1/perf-20120210-0804 Donald Duck.txt");
		pafpParser.parse();
		
		String machine = pafpParser.getTimeSpent();
		
		assertTrue(machine.equals("0:01:15"));
	}
}
