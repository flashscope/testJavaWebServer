package testJavaWebServer;

import java.util.HashMap;

public class ContentTypeParser {
	private static HashMap<String,String> typeMap = new HashMap<String, String>();
	
	public static void initialize() {
		typeMap.clear();
		
		typeMap.put("html","text/html");
		typeMap.put("htm","text/html");
		typeMap.put("css","text/css");
		typeMap.put("js","text/javascript");
		typeMap.put("jpg","image/jpeg");
		typeMap.put("jpeg","image/jpeg");
		typeMap.put("gif","image/gif");
		typeMap.put("bmp","image/bmp");
		typeMap.put("zip","application/zip");
		typeMap.put("mp3","audio/mpeg3");
		typeMap.put("wav","audio/wav");
		typeMap.put("avi","video/avi");
		typeMap.put("mpg","video/mpeg");
		typeMap.put("mpeg","video/mpeg");
		typeMap.put("asf","video/x-ms-asf");
		typeMap.put("rtf","application/rtf");
		typeMap.put("doc","application/msword");
		typeMap.put("xls","application/vnd.ms-excel");
		typeMap.put("ppt","application/vnd.ms-PowerPoint");
		typeMap.put("asp","text/asp");

	}
	public static String getContentTypeByExtension(String extension) {
		String result = typeMap.get(extension);
		if ( null == result ) {
			result = "text/html"; // or octstream
		}
		return result;
	}

}
