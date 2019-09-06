package com.yuyang.client.handler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientInit {
	
	private String url;
	
	private int port;
	
	private int bufferSize;

	private String nickName;
	
	public ClientInit(String url, int port , int bufferSize , String nickName) {
		this.url = url;
		this.port = port;
		this.bufferSize = bufferSize;
		this.nickName = nickName;
	}
	
	public void send(String data) throws IOException {
		//建立UDP通道
		DatagramChannel channel = DatagramChannel.open();
		//设为非阻塞
	    channel.configureBlocking(true);
		//建立连接
		channel.connect(new InetSocketAddress(url , port));
		//发送UDP数据
		//分配直接缓存
		String newData = nickName + ":" + data;// + System.currentTimeMillis()";
		ByteBuffer buffer = ByteBuffer.allocateDirect(this.bufferSize); //自定义 个字节
		buffer.put(newData.getBytes()); //[121, 117, 121, 97, 110, 103, 49]
		buffer.flip();
		//通道发送
		int bytesWritten = channel.write(buffer); //返回发送的byte数
		
		System.out.println("发送完成 " + bytesWritten);
		//读取
//		int bytesRead = channel.read(buf);

		
	}
	

}
