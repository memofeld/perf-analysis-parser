package pafp.util;

public class TimeUtil {

	public static int toSeconds(String in) {
		String[] timeAsArray = in.split(":");
		if (timeAsArray.length != 3) {
			return 0;
		}
		Integer timeHours = Integer.parseInt(timeAsArray[0]);
		Integer timeMinutes = Integer.parseInt(timeAsArray[1]);
		Integer timeSeconds = Integer.parseInt(timeAsArray[2]);
		return timeHours * 3600 + timeMinutes * 60 + timeSeconds;
	}
	
}
