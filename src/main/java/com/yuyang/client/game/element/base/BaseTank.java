package com.yuyang.client.game.element.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Map;

import com.yuyang.client.game.WarMain;
import com.yuyang.client.game.common.Constants;
import com.yuyang.client.game.element.weapon.Mine;
import com.yuyang.client.game.element.weapon.NormalBullet;
import com.yuyang.client.game.factory.GameObjectFactory;
import com.yuyang.client.game.tools.ImagesMap;

public abstract class BaseTank extends MoveObject{
	
	protected BaseDirection team = BaseDirection.RED;
	
	protected int bloodAWidth = 30;
	protected int bloodAHeight = 5;
	
	protected int speedSeconds = 0;
	
	protected SpeedThread speedThread = null;
	
	protected boolean speedFlag = false;
	
	protected BaseDirection faceDir  = BaseDirection.D;
	
	protected int weaponType;
	
	protected static Map<String, Image> imgs = ImagesMap.getImgs();
	
	public BaseTank(int x, int y, int width, int height, int life,
			boolean visiable, boolean invincible, WarMain war, int speed,
			BaseDirection moveDir, boolean moving, BaseDirection team,
			int id, int weaponType) {
		super(x, y, width, height, life, visiable, invincible, war, speed,
				moveDir, moving, id);
		this.team = team;
		this.weaponType = weaponType;
	}
	
	public BaseTank(int x, int y, int width, int height, int life,
			boolean visiable, boolean invincible, WarMain war, int speed,
			BaseDirection moveDir, boolean moving, BaseDirection team
			) {
		super(x, y, width, height, life, visiable, invincible, war, speed,
				moveDir, moving);
		this.team = team;
		this.weaponType = 1;
	}


	@Override
	protected void onCollide(GameObject gameObject) {
		if(gameObject instanceof Weapon){
			stop();
			((Weapon) gameObject).attack(this);
		}else{
			stop();
		}
		
	}



	public void stopFire() {
		// TODO Auto-generated method stub
		
	}
	
	
	//显示tank，主要是通过判断moveDir运动的方向画出4个方向的tank图片,并且给开火方向赋值
	@Override
	public void show(Graphics g) {
		if(this.visiable) {
			super.show(g);
			showBlood(g);
		}
		
		//show 方法应该不需要判断生死，因为在 doAction里面已经处理了，
		//如果life是0了，那么moveObject里面会掉用onDie方法，把这个物体移出游戏Map
		
//		if(!isAlive()){
//			this.war.removeObject(this);
//			return ;
//		} 
//		
//		if(this.visiable) {
//			switch (moveDir) {
//			case U:
//				g.drawImage(imgs.get("tankU"), x, y, null);
//				faceDir = moveDir;
//				break;
//			case D:
//				g.drawImage(imgs.get("tankD"), x, y, null);
//				faceDir = moveDir;
//				break;
//			case L:
//				g.drawImage(imgs.get("tankL"), x, y, null);
//				faceDir = moveDir;
//				break;
//			case R:
//				g.drawImage(imgs.get("tankR"), x, y, null);
//				faceDir = moveDir;
//				break;	
//			case STOP:
//				showFace(g);
//				break;	
//			default:
//				break;	
//			}
//		}
		
		if(speedFlag){
			g.setFont(new Font("宋体", Font.PLAIN, 12));
			g.setColor(Color.WHITE);
			g.drawString("速度加快：" + speedSeconds + "秒", 10, 100);
		}
	}

	//开火炮筒的方向 
//	protected void showFace(Graphics g){
//		switch (faceDir) {
//		case U:
//			g.drawImage(imgs.get("tankU"), x, y, null);
//			break;
//		case D:
//			g.drawImage(imgs.get("tankD"), x, y, null);
//			break;
//		case L:
//			g.drawImage(imgs.get("tankL"), x, y, null);
//			break;
//		case R:
//			g.drawImage(imgs.get("tankR"), x, y, null);
//			break;	
//		default:
//			g.drawImage(imgs.get("tankU"), x, y, null);
//			break;	
//		}
//	}

	public void fire() {
		if(isAlive()){
			int x, y;
			switch (moveDir) {
			case U:
				x = this.x +(width/2);
				y = this.y - 20;		//子弹本身有20的长宽
				break;
			case D:
				x = this.x +(width/2);
				y = this.y + height;
				break;
			case L:
				x = this.x - 20;      //子弹本身有20的长宽
				y = this.y + (height/2);
				break;
			case R:
				x = this.x  + width;
				y = this.y + (height/2);
				break;
			default:
				throw new IllegalStateException("illegal moveDir: " + moveDir);
			}
			
			Weapon weapon = (Weapon) getWeapon(this.weaponType, x, y);
			war.addObject(weapon);
//			return new BaseBullet(this.x +(width-2) , this.y+(height/2), 21 , 60 , 1 , true, false, this.war, 15, this.moveDir, true, this.team);
		}
			
	}
	
	public Weapon getWeapon(int weaponType, int x, int y) {
		Weapon weapon = null;
		switch (weaponType) {
		case 1:
			weapon = (NormalBullet) GameObjectFactory.newGameObj(x, y, Constants.TYPE_NO_BULLET, this.moveDir, this.team, war);
			break;
		case 2:
			weapon = (Mine) GameObjectFactory.newGameObj(x, y, Constants.TYPE_MINE, this.moveDir, this.team, war);
			break;
		default:
			weapon = (NormalBullet) GameObjectFactory.newGameObj(x, y, Constants.TYPE_NO_BULLET, this.moveDir, this.team, war);
			break;
		}
		return weapon;
	}
	
//	public Weapon layMine() {
//		if(isAlive()){
//			return new Mine(this.x + 5, this.y+7, 5 , this.faceDir , this.team , this.war);
//		}else{
//			return null;
//		}
//			
//	}
	

	//画血条
	public void showBlood(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(x, y-8, bloodAWidth, bloodAHeight);
		g.setColor(Color.RED);
		int bloodWidth = bloodAWidth * life / 10;
		g.fillRect(x, y-8, bloodWidth, bloodAHeight);
	}
	
	private class SpeedThread implements Runnable{
	
		@Override
		public void run() {
			while(true){
				if(speedSeconds <= 0){
					speed = speed - 2;
					speedFlag = false;
					break;
				}else{
					speedFlag = true;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				speedSeconds--;
				
			}
		}

    }

	public void changeWeapon(int weaponType) {
		this.weaponType = weaponType;
	}
	
}
