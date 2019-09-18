package com.yuyang.client.game.element.base;

import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import com.yuyang.client.game.WarMain;
import com.yuyang.client.game.tools.ImagesMap;

public class BaseBullet  extends Weapon{
	
	
	protected WarMain war;
	
	protected static Map<String, Image> imgs = new HashMap<String, Image>();
	
	private BaseDirection team = null;
	
	
	
	public BaseBullet(int x, int y, int width, int height, int life,
			boolean visiable, boolean invincible, WarMain war, int speed,
			BaseDirection moveDir, boolean moving, BaseDirection team) {
		super(x, y, width, height, life, visiable, invincible, war, speed, moveDir,
				moving);
		this.imgs = ImagesMap.getImgs();
		this.team = team;
	}


	@Override
	public void move() {
		switch (moveDir) {
		case U:
			y = y - speed;
			break;
		case D:
			y = y + speed;
			break;
		case L:
			x = x - speed;
			break;
		case R:
			x = x + speed;
			break;	
		case STOP:
			break;	
		default:
			break;
		}
		
		//判断是否出界
//		setIsOut(x , y , size);
		
	}


	@Override
	public void show(Graphics g) {
		if(!isAlive()){
			this.war.removeObject(this);
			return;
		}
//		Color color = g.getColor();
//		g.setColor(color.RED);
		
		switch (moveDir) {
		case U:
			g.drawImage(imgs.get("bulletU"), x, y, null);
			break;
		case D:
			g.drawImage(imgs.get("bulletD"), x, y, null);
			break;
		case L:
			g.drawImage(imgs.get("bulletL"), x, y, null);
			break;
		case R:
			g.drawImage(imgs.get("bulletR"), x, y, null);
			break;	
		default:
			break;	
		}
	}


	@Override
	protected void onCollide(GameObject gameObject) {
		// TODO Auto-generated method stub
		
	}



	@Override
	protected Image[] getMoveImages() {
		// TODO Auto-generated method stub
		return null;
	}

	
//	private void setIsOut(int x , int y , int size){
//		if(x + size > WarMain.windowW){
//			isLive = false;
//		}
//		if(y + size > WarMain.windowH){
//			isLive = false;
//		}
//		if(x <0){
//			isLive = false;
//		}
//		if(y <0){
//			isLive = false;
//		}
//	}
//	
//	
//	@Override
//	public boolean hit(Element element){
//		if(!super.hit(element)){
//			return false;
//		}
//		BaseTank tank = null;
//		if(element instanceof BaseTank){
//			tank = (BaseTank)element;
//		}else{
//			return true;
//		}
//		
//		if(this.team == tank.team){
//			return false;
//		}else{
//			return true;
//		} 
//	}
	

	
}
