package io.acornsoft.server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketThreadServer extends Thread {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Socket socket;

	public SocketThreadServer(Socket socket){
		this.socket=socket;
	}
	
	/**
	 * 단순 문자열 Thread server
	 */
	public void run(){
		//System.out.println("Socket thread server start.");
		BufferedReader br = null;
		PrintWriter pw = null;
		try{
			/*
			 * 접근한 소켓 계정의 ip를 체크한다.
			 */
			String connIp = socket.getInetAddress().getHostAddress();
			//System.out.println("");
						
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			pw = new PrintWriter(socket.getOutputStream());
			
			// 클라이언트에서 보낸 문자열 출력
			logger.info("[From "+connIp + "]"+br.readLine());
			
			// 클라이언트에 응답 문자열 전송
			pw.println("[To "+connIp + "]Received. OK.");
			pw.flush();		
		}catch(IOException e){
			System.out.println(e);
			logger.error(e.toString());
		}finally{
			try{
				if(pw != null) { pw.close();}
				if(br != null) { br.close();}
				if(socket != null){socket.close();}
			}catch(IOException e){
				System.out.println(e);
				logger.error(e.toString());
			}
		}
	}
}
