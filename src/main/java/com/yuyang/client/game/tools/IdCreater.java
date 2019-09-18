package com.yuyang.client.game.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *    服务器会事先生成所有id的映射，这个类先模拟一下生成 int型的id
 * 
 * */
public class IdCreater {
	
	private static int id = 0;
	
	private static Map<Integer, String> idAndNickNameMap = new HashMap<Integer, String>();
	
	private static IdCreater idCreater = new IdCreater();
	
	
	private IdCreater() {
	}
	
	public static IdCreater getInstance(){
		return idCreater;
	}
	
	public static int getId(String nickName){
		
		id++;
		
		if(null == nickName || "".equals(nickName)){
			nickName = UUID.randomUUID().toString();
		}
		idAndNickNameMap.put(id, UUID.randomUUID().toString());
		
		return id;
	}

}
