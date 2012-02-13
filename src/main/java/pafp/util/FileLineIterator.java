package pafp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Iterator;

public class FileLineIterator implements Iterator<String> {

	private BufferedReader br;

	private String next;

	private String current = "";

	private String lastFilledLine = null;

	FileLineIterator(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, Charset.forName("ISO-8859-1"));
			br = new BufferedReader(isr);
			next = br.readLine();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public String next() {
		if (next == null) {
			return null;
		}
		String out = next.trim();
		if (!current.isEmpty()) {
			lastFilledLine = current;
		}
		current = out;
		try {
			next = br.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return out;
	}

	public String getLastFilledLine() {
		return lastFilledLine;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	public static boolean isEmpty(String s) {
		if (s == null) {
			return true;
		} else {
			return s.trim().isEmpty();
		}
	}

}