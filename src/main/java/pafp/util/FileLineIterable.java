package pafp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Iterator;

public class FileLineIterable implements Iterable<String> {

	private File file;

	public FileLineIterable(File file) {
		this.file = file;
	}

	public FileLineIterable(String file) {
		this.file = new File(file);
	}

	@Override
	public Iterator<String> iterator() {
		return new FileLineIterator(file);
	}

	static class FileLineIterator implements Iterator<String> {

		private BufferedReader br;

		private String s;

		FileLineIterator(File file) {
			try {
				FileInputStream fis = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(fis, Charset.forName("ISO-8859-1"));
				br = new BufferedReader(isr);
				s = br.readLine();
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public boolean hasNext() {
			return s != null;
		}

		@Override
		public String next() {
			if (s == null) {
				return null;
			}
			String t = s.trim();
			try {
				s = br.readLine();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return t;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}
