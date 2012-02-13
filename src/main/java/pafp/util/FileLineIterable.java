package pafp.util;

import java.io.File;

public class FileLineIterable implements Iterable<String> {

	private File file;

	public FileLineIterable(File file) {
		this.file = file;
	}

	public FileLineIterable(String file) {
		this.file = new File(file);
	}

	@Override
	public FileLineIterator iterator() {
		return new FileLineIterator(file);
	}

}
