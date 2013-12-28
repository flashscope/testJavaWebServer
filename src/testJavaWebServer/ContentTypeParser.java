package testJavaWebServer;

public class ContentTypeParser {

	public static String getContentTypeByExtension(String extension) {
		
		switch (extension) {
		
		case "html":
			return "text/html";
		case "htm":
			return "text/html";
		case "css":
			return "text/css";
		case "js":
			return "text/javascript";
		case "jpg":
			return "image/jpeg";
		case "jpeg":
			return "image/jpeg";
		case "gif":
			return "image/gif";
		case "bmp":
			return "image/bmp";
		case "zip":
			return "application/zip";
		case "mp3":
			return "audio/mpeg3";
		case "wav":
			return "audio/wav";
		case "avi":
			return "video/avi";
		case "mpg":
			return "video/mpeg";
		case "mpeg":
			return "video/mpeg";
		case "asf":
			return "video/x-ms-asf";
		case "rtf":
			return "application/rtf";
		case "doc":
			return "application/msword";
		case "xls":
			return "application/vnd.ms-excel";
		case "ppt":
			return "application/vnd.ms-PowerPoint";
		case "asp":
			return "text/asp";
		default:
			return "text/html";
		}

	}

}
