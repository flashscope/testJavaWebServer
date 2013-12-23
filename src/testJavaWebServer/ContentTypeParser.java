package testJavaWebServer;

public class ContentTypeParser {

	public static String getContentTypeByExtension(String extension) {

		if (extension.equals("html")) {
			return "text/html";
		} else if (extension.equals("htm")) {
			return "text/html";
		} else if (extension.equals("css")) {
			return "text/css";
		} else if (extension.equals("js")) {
			return "text/javascript";
		} else if (extension.equals("jpg")) {
			return "image/jpeg";
		} else if (extension.equals("jpeg")) {
			return "image/jpeg";
		} else if (extension.equals("gif")) {
			return "image/gif";
		} else if (extension.equals("bmp")) {	
			return "image/bmp";
		} else if (extension.equals("zip")) {
			return "application/zip";
		} else if (extension.equals("mp3")) {
			return "audio/mpeg3";
		} else if (extension.equals("wav")) {
			return "audio/wav";
		} else if (extension.equals("avi")) {
			return "video/avi";
		} else if (extension.equals("mpg")) {
			return "video/mpeg";
		} else if (extension.equals("mpeg")) {
			return "video/mpeg";
		} else if (extension.equals("asf")) {
			return "video/x-ms-asf";
		} else if (extension.equals("rtf")) {
			return "application/rtf";
		} else if (extension.equals("doc")) {
			return "application/msword";
		} else if (extension.equals("xls")) {
			return "application/vnd.ms-excel";
		} else if (extension.equals("ppt")) {
			return "application/vnd.ms-PowerPoint";
		} else if (extension.equals("asp")) {
			return "text/asp";
		} else {
			return "application/octet-stream";
		}
		
	}

}
