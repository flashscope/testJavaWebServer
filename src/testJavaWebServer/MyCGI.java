package testJavaWebServer;

public class MyCGI {
	public static String makeJavaScriptAlert(String message) {
		String result = "<script type='text/javascript'>alert('" + message + "');</script>";
		return result;
	}
}
