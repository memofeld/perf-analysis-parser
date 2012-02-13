package pafp;

import java.io.File;

import pafp.util.FileLineIterable;

public class PafpParser {
	
	private FileLineIterable fli;
	private String machine;
	private String iteration;
	private String user;
	
	public PafpParser(String filename) {
		fli = new FileLineIterable(filename);
		setUser(filename);
	}
	
	public PafpParser(File file) {
		fli = new FileLineIterable(file);
	}
	
	public void setMachine(String line) {
		machine = line.replaceAll("^.+:\\s", "").replaceAll(",.*", "");
		System.out.println(machine);
	}
	
	public String getMachine() {
		return machine;
	}
	
	public void setIteration(String line) {
		iteration = line.replaceAll("@iteration:", "");
		System.out.println(iteration);
	}
	
	public String getIteration() {
		return iteration;
	}
	
	public void setUser(String filename) {
		user = filename.replaceAll("^.+[\\d- ]+ ", "").replaceAll(".txt", "");
		System.out.println(user);
	}
	
	public String getUser() {
		return user;
	}
	
	/**
	 * This method parses the file and returns a csv table as a String.
	 * 
	 * @return csv table as a String
	 */
	public String parse() {
		StringBuffer sb = new StringBuffer();
		String csv = null;
		
		sb.append("Iteration;Machine;User;www.google.com_avg-time;www.heise.de_avg-time;www.die-pa.de_avg-time;intranet.die-pa.de_avg-time;www.prodyna.de_avg-time;support.prodyna.com_avg-time;speedtest_avg-download;speedtest_time-spent;www.die-pa.de_avg-download;www.die-pa.de_time-spent;hetzner_avg-download;hetzner_time-spent\n");
		
		int lineNumber = 0;
		int avgPing = 0;
		for (String s : fli) {
			
			
			if (2 == lineNumber) {
				setMachine(s);
			}
			
			if (s.matches("^@iteration.+")) {
				setIteration(s);
			}
			
			lineNumber++;
		}
		System.out.println(sb.toString());
		return csv;
	}

	public static void main(String args[]) {
		new PafpParser("data1/perf-20120210-0804 Donald Duck.txt").parse();
	}
}
