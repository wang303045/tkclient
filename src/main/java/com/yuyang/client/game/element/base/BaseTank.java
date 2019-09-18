package com.yuyang.client.game.element.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Map;

import com.yuyang.client.game.WarMain;
import com.yuyang.client.game.tools.ImagesMap;

public abstract class BaseTank extends MoveObject{
	
	protected BaseDirection team = BaseDirection.RED;
	
	protected int bloodAWidth = 30;
	protected int bloodAHeight = 5;
	
	protected int speedSeconds = 0;
	
	protected SpeedThread speedThread = null;
	
	protected boolean speedFlag = false;
	
	protected BaseDirection faceDir  = BaseDirection.D;
	
	protected static Map<String, Image> imgs = ImagesMap.getImgs();
	
	public BaseTank(int x, int y, int width, int height, int life,
			boolean visiable, boolean invincible, WarMain war, int speed,
			BaseDirection moveDir, boolean moving, BaseDirection team,
			int id) {
		super(x, y, width, height, life, visiable, invincible, war, speed,
				moveDir, moving, id);
		this.team = team;
	}
	
	public BaseTank(int x, int y, int width, int height, int life,
			boolean visiable, boolean invincible, WarMain war, int speed,
			BaseDirection moveDir, boolean moving, BaseDirection team
			) {
		super(x, y, width, height, life, visiable, invincible, war, speed,
				moveDir, moving);
		this.team = team;
	}

	//按键改变是否按键的 boolean的值，按键之后改变表示按键的boolean值
//	public void keyPressed(KeyEvent e) {
//		int knum = e.getKeyCode();
//		switch (knum) {
//		case KeyEvent.VK_W:
//			bU = true;
//			break;
//		case KeyEvent.VK_S:
//			bD = true;
//			break;
//		case KeyEvent.VK_A:
//			bL = true;
//			break;
//		case KeyEvent.VK_D:
//			bR = true;
//			break;	
//		case KeyEvent.VK_SPACE:
//			this.war.getWeaponlist().add(fire());
//			break;	
//		case KeyEvent.VK_CONTROL:
//			this.war.getWeaponlist().add(layMine());
//			break;	
//			
//		default:
//			break;
//		}
//		changeDir();
//	}

	
//	public void keyReleased(KeyEvent e) {
//		int knum = e.getKeyCode();
//		switch (knum) {
//		case KeyEvent.VK_W:
//			bU = false;
//			break;
//		case KeyEvent.VK_S:
//			bD = false;
//			break;
//		case KeyEvent.VK_A:
//			bL = false;
//			break;
//		case KeyEvent.VK_D:
//			bR = false;
//			break;	
//		default:
//			break;
//		}
//		changeDir();
//	}
	

	//显示tank，主要是通过判断moveDir运动的方向画出4个方向的tank图片,并且给开火方向赋值
	@Override
	public void show(Graphics g) {
		if(!isAlive()) return ;
		
		if(this.visiable) {
			super.show(g);
			showBlood(g);
		}
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
//			Font f = new Font("Arial", Font.BOLD, 10);
			g.setFont(new Font("宋体", Font.PLAIN, 12));
			g.setColor(Color.WHITE);
//			g.setFont(f);
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

	public Weapon fire() {
		if(isAlive()){
			return new BaseBullet(this.x +(width-2) , this.y+(height/2), 21 , 60 , 1 , true, false, this.war, 15, this.moveDir, true, this.team);
		}else{
			return null;
		}
			
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

	@Override
	protected void onCollide(GameObject gameObject) {
		if(gameObject instanceof Weapon){
			
		}else{
			stop();
		}
		
	}


	public void changeWeapon(int weaponType) {
		// TODO Auto-generated method stub
		
	}

	public void stopFire() {
		// TODO Auto-generated method stub
		
	}
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
}
