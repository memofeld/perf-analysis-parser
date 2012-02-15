package pafp.util;

public class KiloUtil {

	public static int convert(String in) {
		return in.contains("k") ? Integer.parseInt(in.replaceAll("k", "")) * 1024 : Integer.parseInt(in);
	}
	
}
