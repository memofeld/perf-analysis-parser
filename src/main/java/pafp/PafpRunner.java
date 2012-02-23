package pafp;

import java.io.File;

import pafp.model.FileModel;
import pafp.util.PerfFileFilter;

public class PafpRunner {

	public static void main(String[] args) {
		System.out
				.println("Iteration;Machine;Filiale;User;www.google.com_avg-time;www.heise.de_avg-time;www.die-pa.de_avg-time;intranet.die-pa.de_avg-time;www.prodyna.de_avg-time;support.prodyna.com_avg-time;speedtest_avg-download;speedtest_time-spent;www.die-pa.de_avg-download;www.die-pa.de_time-spent;hetzner_avg-download;hetzner_time-spent");
		for (File file : new File(args[0]).listFiles(new PerfFileFilter())) {
			FileModel model = new FileModel(file);
			System.out.print(model);
		}
	}

}
