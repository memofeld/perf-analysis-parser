package pafp.model;

import java.io.File;

import org.junit.Test;

import pafp.model.FileModel;
import pafp.util.PerfFileFilter;

public class FileModelTest {

	@Test
	public void test() throws Exception {
		for (File file : new File("N:/perfdata").listFiles(new PerfFileFilter())) {
			FileModel model = new FileModel(file);
			System.out.println(model);
		}
	}

}
