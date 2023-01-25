package io.acornsoft;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.acornsoft.server.SocketThreadServer;

public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	
	private static final int PORT_NUMBER = 4432;
	
	public static void main(String[] args) throws IOException{
		

		logger.info("* * * * * * * * * * * * * * * * * * *");
		logger.info("* Socket Application  Process Start");
		logger.info("* * * * * * * * * * * * * * * * * * *");
		
		try(ServerSocket server = new ServerSocket(PORT_NUMBER)){
			while(true){
				Socket connection = server.accept();
				Thread task = new SocketThreadServer(connection);
				task.start();
			}
		}catch(IOException e){
			System.out.println(e);
			logger.error(e.toString());
		}
	}

}
