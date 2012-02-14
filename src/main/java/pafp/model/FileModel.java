package pafp.model;

import java.io.File;
import java.util.Arrays;

import pafp.util.FileLineIterable;
import pafp.util.FileLineIterator;
import pafp.util.StringUtil;

public class FileModel {

	private final File file;

	private String location;

	private String userInfo;

	private int iterations;

	public FileModel(File file) {
		this.file = file;
		this.init();
	}

	private void init() {
		{
			String s = file.getName().substring("perf-20120210-0712 ".length());
			userInfo = s.substring(0, s.lastIndexOf('.'));
		}
		int count = 0;
		for (FileLineIterator it = new FileLineIterable(file).iterator(); it.hasNext();) {
			String s = it.next();
			if (++count == 3) {
				location = s.split(": ")[1].split(",")[0].trim();
			}
			if (s.startsWith("@iteration") || s.startsWith("@split")) {
				String lastFilledLine = it.getLastFilledLine();
				lastFilledLine = StringUtil.normalize(lastFilledLine);
				String[] split = lastFilledLine.split(" ");
				System.out.println(Arrays.asList(split));	
			}
		}

	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append(location);
		out.append(';');
		out.append(userInfo);
		out.append(';');
		out.append(iterations);
		return out.toString();
	}

}
