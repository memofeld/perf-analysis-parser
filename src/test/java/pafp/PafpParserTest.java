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
}
