package pafp;

import java.io.File;

import pafp.util.FileLineIterable;

public class PafpParser {
	
	private FileLineIterable fli;
	private String machine;
	private String iteration;
	private String user;
	private String avgPing;
	private String avgDownload;
	private String timeSpent;
	
	public PafpParser(String filename) {
		fli = new FileLineIterable(filename);
		setUser(filename);
	}
	
	public PafpParser(File file) {
		fli = new FileLineIterable(file);
		setUser(file.getName());
	}
	
	public void setMachine(String line) {
		machine = line.replaceAll("^.+:\\s", "").replaceAll(",.*", "");
	}
	
	public String getMachine() {
		return machine;
	}
	
	public void setIteration(String line) {
		iteration = line.replaceAll("@iteration:", "");
	}
	
	public String getIteration() {
		return iteration;
	}
	
	public void setUser(String filename) {
		user = filename.replaceAll("^.+[\\d- ]+ ", "").replaceAll(".txt", "");
	}
	
	public String getUser() {
		return user;
	}
	
	public void setAvgPing(String line) {
		avgPing = line.replaceAll("^.+Mittelwert = ", "");
	}
	
	public String getAvgPing() {
		return avgPing;
	}
	
	public void setAvgDownload(String line) {
		avgDownload = line.replaceAll("^100 11.0M  100 11.0M    0     0\\s+", "").split(" ")[0];
	}
	
	public String getAvgDownload() {
		return avgDownload;
	}
	
	public void setTimeSpent(String line) {
		timeSpent = line.replaceAll("^100 11.0M  100 11.0M    0     0\\s+", "").replaceFirst("[\\dkM]+", "").trim().replaceFirst("0", "").trim().split(" ")[0];
	}
	
	public String getTimeSpent() {
		return timeSpent;
	}
	
	/**
	 * This method parses the file and returns a csv table as a String.
	 * 
	 * @return csv table as a String
	 */
	public String parse() {
		StringBuffer sb = new StringBuffer();
		String csv = null;
		
		sb.append("Iteration;Machine;User;www.google.com_avg-time;www.heise.de_avg-time;www.die-pa.de_avg-time;intranet.die-pa.de_avg-time;www.prodyna.de_avg-time;support.prodyna.com_avg-time;speedtest_avg-download;speedtest_time-spent;www.die-pa.de_avg-download;www.die-pa.de_time-spent;hetzner_avg-download;hetzner_time-spent;\n");
		
		int lineNumber = 1;
		int lastTimeSpentLineNumber = 0;
		int timeSpentCounter = 0;
		for (String s : fli) {
			
			
			if (3 == lineNumber) {
				setMachine(s);
			}
			
			if (s.matches("^@iteration.+")) {
				setIteration(s);
				sb.append(getIteration()).append(";").append(getMachine()).append(";").append(getUser()).append(";");
			}
			
			if (s.matches(".*Mittelwert = .+")) {
				setAvgPing(s);
				sb.append(getAvgPing()).append(";");
			}
			
			if (s.matches("100 11.0M  100 11.0M.+")) {
				setAvgDownload(s);
				setTimeSpent(s);
				
				if ((lineNumber - lastTimeSpentLineNumber) > 10) {
					sb.append(getAvgDownload()).append(";");
					sb.append(getTimeSpent()).append(";");
					
					if (++timeSpentCounter % 3 == 0) {
						sb.append("\n");
					}
					
					lastTimeSpentLineNumber = lineNumber;
				}
			}
			
			lineNumber++;
		}
		System.out.println(sb.toString());
		return csv;
	}
}
