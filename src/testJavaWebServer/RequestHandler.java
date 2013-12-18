package testJavaWebServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Calendar;

public class RequestHandler extends Thread {
	
	private static final String HTML_DIR = "./public_html";
	private Socket connection;
	
	public RequestHandler(Socket connection) {
		this.connection = connection;
	}

	public void run() {
		MyLogger.printLog("this.getId(): " + this.getId() + " UUID:" + Utill.getUUID());
		BufferedReader br = null;
		DataOutputStream dos = null;
		
		try {
			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			dos = new DataOutputStream(connection.getOutputStream());
			
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while ( line != null && !line.equals("") ) {
				sb.append(line + "\n");
				line = br.readLine();
			}
			
			String header = sb.toString();
			
			// NO HEADER ERROR
			if (header == null || header.equals("")) {
				return;
			}

			String requestUrl = HttpParser.parseRequestUrl(header);
			String requestPath = HttpParser.parseRequestPath(requestUrl);
			
			System.out.println("header : " + header);
			System.out.println("requestUrl : " + requestUrl);
			System.out.println("requestPath : " + requestPath);

			if ("/".equals(requestPath)) {
				if ( isFileExists("index.html") ) {
					requestPath = "index.html";
				} else if ( isFileExists("index.htm") ) {
					requestPath = "index.htm";
				} else {
					// 404 ERROR
				}
			}
			
			File file = new File(HTML_DIR + requestPath);
			if ( !file.exists() ) {
				file = new File(HTML_DIR + "/Error/404.html");
				responseNotFound(dos, file.length());
			} else {
				if ( HttpParser.getExtension(requestPath).equals("js") ) {
					responseJavascriptOk(dos, file.length());
				} else {
					responseHtmlOk(dos, file.length());
				}
			}
			
			fileWriter(dos, file);
			
			dos.flush();
			br.close();
			
			
			
			connection.close();
		} catch (Exception e) {
			MyLogger.printLog("connection ERROR:" + e.getMessage());
			try {
				responseServerError(dos, 0);
				connection.close();
			} catch (IOException e1) {
				e1.printStackTrace();
				MyLogger.printLog("Server Major ERROR:" + e1.getMessage());
			}
		}
	}

	private void responseHtmlOk(DataOutputStream dos, long contentsSize)
			throws IOException {
		writeResponseHeader(dos, "200 OK", contentsSize, "text/html");
	}

	private void responseJavascriptOk(DataOutputStream dos, long contentsSize)
			throws IOException {
		writeResponseHeader(dos, "200 OK", contentsSize, "text/javascript");
	}
	
	private void responseNotFound(DataOutputStream dos, long contentsSize)
			throws IOException {
		writeResponseHeader(dos, "404 Not Found", contentsSize, "text/html");
	}
	
	private void responseServerError(DataOutputStream dos, long contentsSize)
			throws IOException {
		writeResponseHeader(dos, "500 Internal Server Error", contentsSize, "text/html");
	}
	
	private static final String NEW_LINE = "\r\n";
	private void writeResponseHeader(DataOutputStream dos, String responseCode, long contentsSize, String contentType) throws IOException {
		
		dos.writeBytes("HTTP/1.1 " + responseCode + NEW_LINE);
		dos.writeBytes("Content-Type: " + contentType + ";charset=UTF-8" + NEW_LINE);
		dos.writeBytes("Content-Length: " + contentsSize + NEW_LINE);
		dos.writeBytes("Cache-Control:max-age=3600" + NEW_LINE);
		dos.writeBytes("Server: NextServer/1.0.0 (Java/1.6/2013-12-19)" + NEW_LINE);
		dos.writeBytes("Date: " + Utill.getDate() + NEW_LINE);
		dos.writeBytes(NEW_LINE);
	}
	
	private boolean isFileExists(String path) {
		File file = new File(HTML_DIR + path);
		if ( file.exists() ) {
			return true;
		}
		return false;
	}
	
	private void fileWriter( DataOutputStream dos, File file ) {

		try {
			FileInputStream fis = new FileInputStream(file);
			
			int bytesAvailable = fis.available();
			int maxBufferSize = 1024;
			int bufferSize = Math.min(bytesAvailable, maxBufferSize);
			byte[] buffer = new byte[bufferSize];
			int bytesRead = fis.read(buffer, 0, bufferSize);
			
			while (bytesRead > 0) {
				dos.write(buffer, 0, bufferSize);
				bytesAvailable = fis.available();
				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				bytesRead = fis.read(buffer, 0, bufferSize);
			}
			
			fis.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			MyLogger.printLog("File Write ERROR:" + e.getMessage());
		}
		

	}

}
