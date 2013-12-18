package testJavaWebServer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

public class Utill {
	
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	public static String getDate() {
		Date currentTime = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy hh:mm:ss z", new Locale("en", "US"));
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		return sdf.format(currentTime);
	}
}
