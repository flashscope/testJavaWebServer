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
	
	public static boolean isFile(String filePath) {
	    int slash = filePath.lastIndexOf('/');
	    String lastPath = filePath.substring(slash);
	    if ( lastPath.lastIndexOf('.') > 0 ) {
	    	return true;
	    }
	    return false;
	}
}
