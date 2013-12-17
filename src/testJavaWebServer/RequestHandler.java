package testJavaWebServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
		//log.log(Level.INFO, "WebServer Thread Created!");
		BufferedReader br = null;
		DataOutputStream dos = null;
		
		try {
			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			dos = new DataOutputStream(connection.getOutputStream());
			
			
			String header = br.readLine();
			
			
			// NO HEADER ERROR
			if (header == null || header.equals("")) {
				return;
			}

			String requestUrl = HttpParser.parseRequestUrl(header);
			String requestPath = HttpParser.parseRequestPath(requestUrl);
			
			System.out.println("this.getId(): " + this.getId() + " UUID:" + Utill.getUUID());
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
			if (file.exists()) {
				
				
				if ( HttpParser.getExtension(requestPath).equals("js") ) {
					responseJavascriptOk(dos, file.length());
				} else {
					responseHtmlOk(dos, file.length());
				}
				
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
				dos.flush();
			}
			
			
			connection.close();
		} catch (IOException e) {
			MyLogger.printLog("connectionIO ERROR:" + e.getMessage());
		} catch (Exception e) {
			MyLogger.printLog("connection ERROR:" + e.getMessage());
		}
	}

	private void responseHtmlOk(DataOutputStream dos, long contentsSize)
			throws IOException {
		writeResponseHeader(dos, contentsSize, "text/html");
	}

	private void responseJavascriptOk(DataOutputStream dos, long contentsSize)
			throws IOException {
		writeResponseHeader(dos, contentsSize, "text/javascript");
	}
	
	private static final String NEW_LINE = "\r\n";
	private void writeResponseHeader(DataOutputStream dos, long contentsSize, String contentType) throws IOException {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		
		dos.writeBytes("HTTP/1.1 200 Document Follows " + NEW_LINE);
		dos.writeBytes("Content-Type: " + contentType + ";charset=UTF-8" + NEW_LINE);
		dos.writeBytes("Content-Length: " + contentsSize + NEW_LINE);
		dos.writeBytes("Cache-Control:max-age=3600" + NEW_LINE);
		dos.writeBytes(NEW_LINE);
	}
	
	private boolean isFileExists(String path) {
		File file = new File(HTML_DIR + path);
		if ( file.exists() ) {
			return true;
		}
		return false;
	}

}
