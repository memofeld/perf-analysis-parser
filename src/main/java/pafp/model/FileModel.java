package pafp.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pafp.util.FileLineIterable;
import pafp.util.FileLineIterator;
import pafp.util.StringUtil;
import pafp.util.UnitsUtil;

public class FileModel {

	private final File file;

	private String location;

	private String branchOffice;

	private String userInfo;

	private static class Iteration {
		List<String> data = new ArrayList<String>();
	}

	private List<Iteration> iterations = new ArrayList<FileModel.Iteration>();

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
		Iteration iteration = new Iteration();
		for (FileLineIterator it = new FileLineIterable(file).iterator(); it.hasNext();) {
			String s = it.next();
			if (++count == 3) {
				location = s.split(": ")[1].split(",")[0].trim();
				branchOffice = location.replaceAll("[\\d]+$", "").replaceAll("-.+$", "");
				if (branchOffice.equals("MD")) {
					branchOffice = "@PRODYNA";
				}
			}
			if (s.startsWith("@iteration") || s.startsWith("@split")) {
				String lastFilledLine = it.getLastFilledLine();
				lastFilledLine = StringUtil.normalize(lastFilledLine);
				String[] split = lastFilledLine.split(" ");
				// avg ping time
				if (split.length > 6 && split[6].equals("Mittelwert")) {
					// store avg ping time field
					iteration.data.add(split[8].replaceAll("ms", ""));
				}
				// if all packets lost set avg ping time to 0
				else if (lastFilledLine.contains("100% Verlust")) {
					iteration.data.add("0");
				}
				// download and time spent row
				else if (split.length == 12) {
					// store avg download and time spent fields
					int avgDownload = UnitsUtil.convert(split[6]);
					int timeSpentSeconds = UnitsUtil.toSeconds(split[9]);
					iteration.data.add("" + avgDownload);
					iteration.data.add("" + timeSpentSeconds);
				}
				// if command "curl" not found set download and time spent to 0
				else if (lastFilledLine.contains("konnte nicht gefunden werden.")) {
					iteration.data.add("0");
					iteration.data.add("0");
				}
			}
			if (s.startsWith("@iteration")) {
				iteration = new Iteration();
				iterations.add(iteration);
			}
		}
		// remove last iteration if not complete
		if (iterations.get(iterations.size() - 1).data.size() < 12) {
			iterations.remove(iterations.size() - 1);
		}
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < iterations.size(); i++) {
			out.append(i);
			out.append(';');
			out.append(location);
			out.append(';');
			out.append(branchOffice);
			out.append(';');
			out.append(userInfo);
			for (int j = 0; j < iterations.get(i).data.size(); j++) {
				out.append(';');
				out.append(iterations.get(i).data.get(j));
			}
			out.append('\n');
		}
		return out.toString();
	}

}
