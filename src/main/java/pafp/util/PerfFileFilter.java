package pafp.util;

import java.io.File;
import java.io.FileFilter;

public class PerfFileFilter implements FileFilter {

	@Override
	public boolean accept(File pathname) {
		if (pathname.getName().startsWith("perf")) {
			return true;
		} else {
			return false;
		}
	}
}
