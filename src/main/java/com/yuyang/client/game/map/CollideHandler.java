package com.yuyang.client.game.map;


import com.yuyang.client.game.element.base.BaseDirection;
import com.yuyang.client.game.element.base.BaseScene;
import com.yuyang.client.game.element.base.GameObject;
import com.yuyang.client.game.element.base.MoveObject;

/**
 * 
 * 碰撞检测核心类
 * **/
public class CollideHandler {
	
	//游戏地图数组和游戏物体
	private GameMapHandler gameMapHandler = null;  
	
	private int windowW = 0;  //窗体宽
	
	private int windowH = 0;  //窗体高
	
	public CollideHandler(GameMapHandler gameMapHandler, int windowW, int windowH) {
		this.gameMapHandler = gameMapHandler;
		this.windowW = windowW;
		this.windowH = windowH;
	}


	//碰撞检测传入运动物体，返回游戏物体
	public GameObject getCollidedGameObj(MoveObject moveObject) {
		BaseDirection moveDir = moveObject.getMoveDir();
		int speed = moveObject.getSpeed();
		switch (moveDir) {
			case U: {                        //向上只要改变 y 坐标
				int nowY = moveObject.getY();  //ey = 坦克当前坐标 y 坐标
				int nextY = nowY - speed;          //sy = 坦克的y坐标 往上移动 speed步，也就是坦克的速度（移动之后的新的 y 坐标）
	
				int nowX = moveObject.getX();	 //sx = 坦克x坐标，因为是向上运动，所以X坐标不变
				int nextX = nowX + moveObject.getWidth();  //  坦克当前x坐标 加上坦克的宽度 
				
				//***** 是判断坦克移动方向上的每一步的xy值上有没有和其他物体相交 ，来判断有没有和别的物体相交
				for (int i = nowY - 1; i >= nextY; i--) {
					if (i < 0) { //判断需要移动到的y坐标是否到达屏幕最上方
						//如果向上走x等于或小于0，那么返回屏幕上边界的 GameObject，相当于这个物体是x轴坐标线（一条线）
						//那么这个物体x=nowX ， y=0 ，长和宽都等于moveObject的长和宽
						return BaseScene.UP;  
					}
	
					for (int j = nowX; j < nextX; j++) {   //从当前x坐标 不断循环 ， 直到当前坐标等于坦克当前x坐标 加上坦克的宽度 
						if (gameMapHandler.getGameLand()[j][i] != 0) {    //估计这里是判断地图上这个坐标点上有没有障碍物
							return gameMapHandler.getObjectMap().get(gameMapHandler.getGameLand()[j][i]); //如果有障碍返回这个坐标上的障碍
						}
					}
				}
				break;
			}
			case D: {                        //向下只要改变 y 坐标
				int nowY = moveObject.getY() + moveObject.getHeight();  //ey = 坦克当前坐标 y 坐标
				int nextY = nowY + speed;          //sy = 坦克的y坐标 往下移动 speed步，也就是坦克的速度（移动之后的新的 y 坐标）
	
				int nowX = moveObject.getX();	 //sx = 坦克x坐标，因为是向上运动，所以X坐标不变
				int nextX = nowX + moveObject.getWidth();  //  坦克当前x坐标 加上坦克的宽度 
				
				//***** 是判断坦克移动方向上的每一步的xy值上有没有和其他物体相交 ，来判断有没有和别的物体相交
				for (int i = nowY+1 ; i < nextY; i++) {
					if (i >= windowH) {
						return BaseScene.DOWN;
					}
	
					for (int j = nowX; j < nextX; j++) {   //从当前x坐标 不断循环 ， 直到当前坐标等于坦克当前x坐标 加上坦克的宽度 
						if (gameMapHandler.getGameLand()[j][i] != 0) {    //估计这里是判断地图上这个坐标点上有没有障碍物
							return gameMapHandler.getObjectMap().get(gameMapHandler.getGameLand()[j][i]); //如果有障碍返回这个坐标上的障碍
						}
					}
				}
				break;
			}
			case L: {                        //向左只要改变X
				int nowX = moveObject.getX();
				int nextX = nowX - speed;  
				
				int nowY = moveObject.getY();  
				int nextY = nowY + moveObject.getHeight();          
				
				//***** 是判断坦克移动方向上的每一步的xy值上有没有和其他物体相交 ，来判断有没有和别的物体相交
				for (int i = nowX-1; i > nextX; i--) {
					if (i < 0) {
						return BaseScene.LEFT;
					}
	
					for (int j = nowY; j < nextY; j++) {   
						if (gameMapHandler.getGameLand()[i][j] != 0) {    // （数组前一个维度是宽，后一个是高
							return gameMapHandler.getObjectMap().get(gameMapHandler.getGameLand()[i][j]); 
						}
					}
				}
				break;
			}
			case R: {                        //向右只要改变X
				int nowX = moveObject.getX() + moveObject.getWidth();
				int nextX = nowX + speed;  
				
				int nowY = moveObject.getY();  
				int nextY = nowY + moveObject.getHeight();          
				
				//***** 是判断坦克移动方向上的每一步的xy值上有没有和其他物体相交 ，来判断有没有和别的物体相交
				for (int i = nowX+1; i < nextX; i++) {
					if (i >= windowW) {
						return BaseScene.RIGHT;
					}
	
					for (int j = nowY; j < nextY; j++) {   
						if (gameMapHandler.getGameLand()[i][j] != 0) {    // （数组前一个维度是宽，后一个是高
							return gameMapHandler.getObjectMap().get(gameMapHandler.getGameLand()[i][j]); 
						}
					}
				}
				break;
			}
			default:{
				throw new IllegalStateException("illegal direction: " + moveDir);
			}
		}
		return null;
	}



}
