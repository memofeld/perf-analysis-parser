package pafp;

import java.io.File;

import pafp.model.FileModel;
import pafp.util.PerfFileFilter;

public class PafpRunner {

	public static void main(String[] args) {
		for (File file : new File(args[0]).listFiles(new PerfFileFilter())) {
			FileModel model = new FileModel(file);
			System.out.println(model);
			
			
		}
	}

}
