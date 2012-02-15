package pafp.model;

import java.io.File;
import java.util.ArrayList;

import pafp.util.FileLineIterable;
import pafp.util.FileLineIterator;
import pafp.util.KiloUtil;
import pafp.util.StringUtil;
import pafp.util.TimeUtil;

public class FileModel {

	private final File file;

	private String location;
	
	private String franchise;

	private String userInfo;

	private ArrayList<String> data;

	public FileModel(File file) {
		this.file = file;
		this.init();
	}

	private void init() {
		{
			String s = file.getName().substring("perf-20120210-0712 ".length());
			userInfo = s.substring(0, s.lastIndexOf('.'));
			data = new ArrayList<String>();
		}
		int count = 0;
		for (FileLineIterator it = new FileLineIterable(file).iterator(); it
				.hasNext();) {
			String s = it.next();
			if (++count == 3) {
				location = s.split(": ")[1].split(",")[0].trim();
				franchise = location.replaceAll("[\\d]+$", "").replaceAll("-.+$", "");
			}
			if (s.startsWith("@iteration") || s.startsWith("@split")) {
				String lastFilledLine = it.getLastFilledLine();
				lastFilledLine = StringUtil.normalize(lastFilledLine);
				String[] split = lastFilledLine.split(" ");
				// avg ping time
				if (split.length > 6 && split[6].equals("Mittelwert")) {
					// store avg ping time field
					data.add(split[8].replaceAll("ms", ""));
				}
				// if all packets lost set avg ping time to 0
				else if (lastFilledLine.contains("100% Verlust")) {
					data.add("0");
				}
				// download and time spent row
				else if (split.length == 12) {
					// store avg download and time spent fields
					int avgDownload = KiloUtil.convert(split[6]);
					int timeSpentSeconds = TimeUtil.toSeconds(split[9]);
					data.add("" + avgDownload);
					data.add("" + timeSpentSeconds);
				}
				// if command "curl" not found set download and time spent to 0
				else if (lastFilledLine.contains("konnte nicht gefunden werden.")) {
					data.add("0");
					data.add("0");
				}
			}
		}

	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();

		int dataIndex = 0;
		for (int i = 0; i < data.size() / 12; i++) {
			out.append(i);
			out.append(';');
			out.append(location);
			out.append(';');
			out.append(franchise);
			out.append(';');
			out.append(userInfo);
			out.append(';');

			for (int j = 0; j < 12; j++) {
				if (dataIndex < data.size()) {
					out.append(data.get(dataIndex));

					if (j < 11) {
						out.append(';');
					}
					dataIndex++;
				}
			}

			if (i < data.size() / 12 - 1) {
				out.append("\n");
			}
		}
		return out.toString();
	}

}
