package testJavaWebServer;

import java.util.HashMap;

public class HttpParser {
	
	
//	    POST /action.html HTTP/1.1
//		Host: 127.0.0.1:8080
//		Connection: keep-alive
//		Content-Length: 21
//		Cache-Control: max-age=0
//		Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
//		Origin: http://127.0.0.1:8080
//		User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36
//		Content-Type: application/x-www-form-urlencoded
//		Referer: http://127.0.0.1:8080/index3.html
//		Accept-Encoding: gzip,deflate,sdch
//		Accept-Language: ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4
	
	public static HashMap<String, String> HeaderParser(String header) {
		String lines[] = header.split("\\r?\\n");
		HashMap<String, String> headerMap = new HashMap<String, String>();

		String headLineSplit[] = lines[0].split(" ");
		headerMap.put("Method", headLineSplit[0]);
		headerMap.put("URL", headLineSplit[1]);
		headerMap.put("Version", headLineSplit[2]);

		for (int i = 1; i < lines.length; ++i) {
			
			if ( lines[i] == null || lines[i].equals("") ) {
				if ( headLineSplit[0].equals("POST") ) {
					headerMap.put("PostData", lines[i+1]);
				}
				break;
			}
			
			String lineSplit[] = lines[i].split(": ");
			headerMap.put(lineSplit[0], lineSplit[1]);
		}
		
		return headerMap;
	}
	
	// login=111&passwd=1234
	public static HashMap<String, String> parameterParser(String paraData) {
		HashMap<String, String> paraMap = new HashMap<String, String>();
		
		String splitDataSet[] = paraData.split("&");
		for (int i = 0; i < splitDataSet.length; ++i) {
			String splitData[] = splitDataSet[i].split("=");
			paraMap.put(splitData[0], splitData[1]);
		}
		return paraMap;
	}
	
	
	public static String parseRequestPath(String requestUrl) {
		int index = requestUrl.indexOf('?');
		if (index == -1) {
			return requestUrl;
		}
		return requestUrl.substring(0, index);
	}
	
	public static String parseRequestDirectory(String requestPath) {
		int slash = requestPath.lastIndexOf('/');
		return requestPath.substring(0, slash);
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
