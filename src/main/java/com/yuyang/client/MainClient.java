package com.yuyang.client;

import java.io.IOException;

import com.yuyang.client.handler.ClientInit;
import com.yuyang.client.util.ClientConfig;

public class MainClient {
	
	public static void main(String[] args) {
		
		/**
		 * server.url = 127.0.0.1
			server.port = 8888
			client.buffer.size = 64
			client.nickname = yuyang1
		 * **/
		String url = ClientConfig.getConfig("server.url");
		int port = Integer.valueOf(ClientConfig.getConfig("server.port"));
		int bufferSize = Integer.valueOf(ClientConfig.getConfig("client.buffer.size"));
		String nickName = ClientConfig.getConfig("client.nickname");
		
		ClientInit client = new ClientInit(url, port , bufferSize , nickName);
		
		for(int i=0;i<100;i++){
			try {
				client.send(i+"");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
