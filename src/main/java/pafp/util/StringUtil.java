package pafp.util;

public class StringUtil {

	public static String normalize(String in) {
		if (in == null) {
			return null;
		}
		String tmp = in;
		String out = "";
		do {
			out = tmp;
			tmp = tmp.replaceAll(", ", " ");
			tmp = tmp.replaceAll("  ", " ");
		} while (!tmp.equals(out));
		return out;
	}

}
