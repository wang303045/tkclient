package com.yuyang.client.game.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.yuyang.client.game.WarMain;
import com.yuyang.client.game.common.Constants;
import com.yuyang.client.game.element.base.BaseWall;

/**
 * 单张地图类，里面是存放了所有物体的map
 * 
 * **/
public class GameMap {
		
	//存放物体的list
	private List<ObjectAndLocation> objectList = new ArrayList<ObjectAndLocation>();

	

	public List<ObjectAndLocation> getObjectList() {
		return objectList;
	}



	//这里是把物体的坐标和类型都写入map
	public GameMap(int mapType) {
		switch (mapType) {
		case 1:
			objectList.add(new ObjectAndLocation(370,370,Constants.TYPE_E_TANK));
			for (int i = 0; i < 10; i++) {
				objectList.add(new ObjectAndLocation(100 + (i*72), 500,Constants.TYPE_WALL));
			}
			objectList.add(new ObjectAndLocation(20, 300, Constants.TYPE_WALL_DX));
			break;
		case 2:
			objectList.add(new ObjectAndLocation(100,100,Constants.TYPE_PLAY_TANK));
			objectList.add(new ObjectAndLocation(170,170,Constants.TYPE_E_TANK));
			objectList.add(new ObjectAndLocation(250,250,Constants.TYPE_E_TANK));
			break;
		case 3:
			objectList.add(new ObjectAndLocation(100,100,Constants.TYPE_PLAY_TANK));
			objectList.add(new ObjectAndLocation(170,170,Constants.TYPE_E_TANK));
			objectList.add(new ObjectAndLocation(250,250,Constants.TYPE_E_TANK));
			break;
		default:
			break;
		}
	}

	
	
}
