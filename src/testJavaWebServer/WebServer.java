package testJavaWebServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
	
	private static final int port = 8080;
	public static void main(String[] args) {

		try {
			// backlog test
			ServerSocket serverSocket = new ServerSocket(port,2);
			//ServerSocket serverSocket = new ServerSocket(port);
			
			ContentTypeParser.initialize();
			MyLogger.printLog("Server START [" + port + "]");
			// 클라이언트가 연결될때까지 대기한다. listen
			Socket connection;
			while ((connection = serverSocket.accept()) != null) {
				RequestHandler requestHandler = new RequestHandler(connection);
				requestHandler.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			MyLogger.printLog("ServerSocket ERROR:" + e);
		}
		

	}

}
