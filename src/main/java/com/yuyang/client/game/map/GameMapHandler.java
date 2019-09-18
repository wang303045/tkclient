package com.yuyang.client.game.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.yuyang.client.game.WarMain;
import com.yuyang.client.game.element.base.Effect;
import com.yuyang.client.game.element.base.GameObject;
import com.yuyang.client.game.factory.GameObjectFactory;


/***
 *    操作地图所有地图上的操作都在这里，也可以移除增加地图上的物体
 * */
public class GameMapHandler {
	
		//游戏地图数组
		private int[][] gameLand = null;  
		
		//单张游戏地图类
		private GameMap gameMap = null;
		
		//存放物体的map
		private static ConcurrentMap<Integer, GameObject> objectMap = new ConcurrentHashMap<Integer, GameObject>();
				
		private WarMain war = null;
		
		//初始化所有地图上的物体
		public GameMapHandler(int width, int height, WarMain war) {
			this.gameLand = new int[width][height];
			this.gameMap = new GameMap(1);  //第一个地图只有一个敌人
			this.war = war;
			initObjectMap();
		}
		
		//初始化游戏物体map
		private void initObjectMap(){
			for (ObjectAndLocation o : this.gameMap.getObjectList()) {
				addToObjectMap(GameObjectFactory.newGameObj(o.getX(), o.getY(), o.getType(), this.war));
			}
		}
		

		/***
		 *    操作地图数组留下或擦去占位 标识
		 * */
		//留下痕迹，标记占位 x，y
		public void leftFootprint(GameObject gameObj) {
			markFootprint(gameObj, true);
		}

		//擦除痕迹，去掉现在占位x，y
		public void eraseFootprint(GameObject gameObj) {
			markFootprint(gameObj, false);
		}

		//把物体这个小方块的范围做一个标识，或者删除标识，0 标识没有占用
		private void markFootprint(GameObject gameObj, boolean left) {  //left为true是 mark都为物体id ， 为false时mark都为0
			int sx = gameObj.getX();
			int sy = gameObj.getY();
			int ex = sx + gameObj.getWidth();
			int ey = sy + gameObj.getHeight();
			int mark = left ? gameObj.getId() : 0;     //是否要mark

			for (int i = sx; i < ex; i++) {
				for (int j = sy; j < ey; j++) {
					if (left && gameLand[i][j] != 0) {
						throw new IllegalStateException(
								"gameland exception footprint appear overlapping! " + gameObj + ", " + 
										"[i=" + i  + " , j=" + j + "] ==> "   + 
										"gameLand[i][j] ==> " + gameLand[i][j]+ "," +
										"objectMap ===> " + objectMap.get(gameLand[i][j])
										);
					}
					gameLand[i][j] = mark;
				}
			}
		}
		
		
		//还需要写add to objectMap 和 remove from objectMap方法
		public void removeFromObjectMap(GameObject gameObj){
			this.objectMap.remove(gameObj.getId());
			if(!(gameObj instanceof Effect)) eraseFootprint(gameObj);
		}
		
		
		//还需要写add to objectMap 和 remove from objectMap方法
		public void addToObjectMap(GameObject gameObj){
			this.objectMap.put(gameObj.getId(), gameObj);
			if(!(gameObj instanceof Effect)) leftFootprint(gameObj);
		}
		
		public int[][] getGameLand() {
			return gameLand;
		}

		public void setGameLand(int[][] gameLand) {
			this.gameLand = gameLand;
		}

		public static ConcurrentMap<Integer, GameObject> getObjectMap() {
			return objectMap;
		}

		public static void setObjectMap(ConcurrentMap<Integer, GameObject> objectMap) {
			GameMapHandler.objectMap = objectMap;
		}

		
}
