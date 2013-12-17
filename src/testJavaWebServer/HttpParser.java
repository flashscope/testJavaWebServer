package testJavaWebServer;

public class HttpParser {
	public static String parseRequestUrl(String header) {
		String[] values = header.split(" ");
		return values[1];
	}
	
	public static String parseRequestPath(String requestUrl) {
		int index = requestUrl.indexOf('?');
		if (index == -1) {
			return requestUrl;
		}
		return requestUrl.substring(0, index);
	}
	
	public static String getExtension(String filePath) {
	    int dot = filePath.lastIndexOf('.');
	    return filePath.substring(dot + 1);
	}
}
